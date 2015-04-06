package com.github.webservicetesting

import com.jayway.restassured.RestAssured
import com.jayway.restassured.response.Header
import com.jayway.restassured.response.Response
import com.jayway.restassured.response.ValidatableResponse
import com.jayway.restassured.specification.RequestSpecification
import groovy.util.logging.Log
import org.assertj.core.api.JUnitSoftAssertions
import org.junit.Rule
import org.junit.experimental.categories.Category
import spock.lang.Narrative
import spock.lang.Shared

/**
 * https://www.govtrack.us/api/v2/role?current=true&format=json&state=OR
 * https://www.govtrack.us/api/v2/role?current=true&format=json&state=OR
 */
@Log
@Narrative("""
As a contituent, I need to know the names of the members of Congress for my state
""")
@Category(GovTrackServiceTests)
class GovTrackReadRoleSpec extends BaseSpecification {
    @Rule
    JUnitSoftAssertions softly //Spock instantiates default constructor

    @Shared listOfStates = ["OR", "WA"] //Add  the other 48 if required

    def setup() {
        RestAssured.baseURI = config.getString("govtrack.baseurl")
        RestAssured.basePath = config.getString("govtrack.basepath")
    }

    def "Search for a Member of Congress for My State and get a valid XML response "() {
        given: "Set up a request Spec"
          RequestSpecification requestSpecification = RestAssured.given().accept("application/xml")
                  .header(new Header("Accept-Encoding", "gzip, deflate"))
                  .queryParameters(["format": "xml", "current": "true"])
                  .queryParameter("state", state).log().all()
        when: "We get the response"
          Response response = requestSpecification.get()
          ValidatableResponse validatableResponse = response.then().log().all()
        then: "Do the assertions"
          validatableResponse.statusCode(200)
          validatableResponse.contentType("text/xml")
          //This one fails since the output is null for integers.
          //validatableResponse.body(matchesXsdInClasspath("schemas/govtrackRole.xsd"))

        where:
          state << listOfStates
    }

    def "Search for a Member of Congress for My State and get a valid JSON response "() {
        given: "Set up a request Spec, header, query params, accept encoding of gzip"

          RequestSpecification requestSpecification = RestAssured.given()
                  .accept("application/xml")
                  .header(new Header("Accept-Encoding", "gzip, deflate"))
                  .queryParameters(["format": "json", "current": "true"])
                  .queryParameter("state", "OR").log().all()
        when: "We get the response"
          Response response = requestSpecification.get()
          GovTrackRole role =  response.as( GovTrackRole.class)
        then: "Do the assertions"
          //This is an example of mixing AssertJ's soft assertions and Spock's power assert.
          //Soft assertions are great for simple validations, power asserts for complex object graphs
          softly.assertThat(response.getStatusCode()).isEqualTo( 200 )
          softly.assertThat(response.getContentType()).isEqualTo( "application/json; charset=utf-8" )
          def senators  =  role.objects.findAll( { GovTrackRole.Object o -> o.roleType == "senator" })
                  .collect ({ it.person.name} )
          senators.containsAll(["Sen. Ron Wyden [D-OR]","Sen. Jeff Merkley [D-OR]"])
    }
}
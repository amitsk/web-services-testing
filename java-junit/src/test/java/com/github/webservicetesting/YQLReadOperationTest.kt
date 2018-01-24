package com.github.webservicetesting

import com.github.webservicetesting.model.QueryResult
import io.restassured.RestAssured
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import io.restassured.http.Header
import org.apache.commons.lang3.StringEscapeUtils
import org.apache.commons.lang3.text.StrSubstitutor
import org.assertj.core.api.SoftAssertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import java.util.stream.Stream


class YQLReadOperationTest : BaseAcceptanceTest() {

  object ZipCodeArgsProvider : ArgumentsProvider {

    override fun provideArguments(context: ExtensionContext): Stream<out Arguments> {
      return Stream.of(
          Arguments.of("97229", "pizza", "Pizza Hut"),
          Arguments.of("97006", "burger", "Burger King"),
          Arguments.of("97223", "coffee", "Well & Good Coffee House")
      )
    }
  }


  @DisplayName("Search for Results to Find")
  @ParameterizedTest(name = "{index} ==> Search {0}, for  second={1} to find {2}")
  @ArgumentsSource(ZipCodeArgsProvider::class)
  fun localSearchByZipCodeReturnsExpectedResults(zipCode: String, query: String, businessToTest: String) {
    val response = given().header(Header("Accept-Encoding", "gzip, deflate")).log().all()
        .queryParam("q", generateSearchQuery(zipCode, query))
        .queryParam("format", "json")
        .accept(ContentType.JSON).get()
    response.then().log().all()
    //http://joel-costigliola.github.io/assertj/assertj-core-features-highlight.html#soft-assertions
    // We use soft assertions to assert all at the end.
    val softly = SoftAssertions()
    softly.assertThat(response.statusCode).isEqualTo(200)
    softly.assertThat(response.getHeader("Content-Type")).contains("application/json")

    val customerNames = response.jsonPath().getList<String>("query.results.Result.Title")
    softly.assertThat(customerNames).contains(businessToTest)
  }

  @Test
  fun localSearchByZipCodeReturnsCompleteResponse() {
    val zipCode = "97006"
    val query = "pizza"
    val businessToTest = "Bellagios Pizza"

    val response = given().header(Header("Accept-Encoding", "gzip, deflate")).log().all()
        .queryParam("q", generateSearchQuery(zipCode, query))
        .queryParam("format", "json")
        .accept(ContentType.JSON).get()
    //http://joel-costigliola.github.io/assertj/assertj-core-features-highlight.html#soft-assertions
    // We use soft assertions to assert all at the end.
    val softly = SoftAssertions()
    softly.assertThat(response.statusCode).isEqualTo(200)
    softly.assertThat(response.getHeader("Content-Type")).contains("application/json")
    val queryResult = response.then().log().all().extract().`as`(QueryResult::class.java)
    val queryResultQuery = queryResult.query
    softly.assertThat(queryResultQuery.count).isEqualTo(10)
    softly.assertThat(queryResultQuery.lang).isEqualToIgnoringCase("en-us")
    //Java8's concise Lambda syntax. Only those results with > 0 reviews
    val resultsWithReviews = queryResultQuery.results.result
        .filter { s -> s.rating.totalReviews.compareTo("0") != 0 }
    //Now get the names of the business
    softly.assertThat(resultsWithReviews.map { it.getTitle() }).contains(businessToTest)
  }

  protected fun generateSearchQuery(zip: String, query: String): String? {
    val valuesMap = mapOf("zip" to zip, "query" to query)
    val templateString = "select * from local.search where zip=\${zip} and query='\${query}'"
    val sub = StrSubstitutor(valuesMap)
    val resolvedString = sub.replace(templateString)
    return StringEscapeUtils.escapeHtml4(resolvedString)
  }

  companion object {
    @BeforeAll
    @JvmStatic
    internal fun initBaseUrl() {
      RestAssured.baseURI = BaseAcceptanceTest.config.getString("yql.baseUrl")
      RestAssured.basePath = BaseAcceptanceTest.config.getString("yql.basePath")
    }
  }
}

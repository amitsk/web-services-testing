package com.github.webservicestesting.restifytodo

import com.github.webservicestesting.model.QueryResult
import com.jayway.restassured.RestAssured
import com.jayway.restassured.RestAssured._
import com.jayway.restassured.http.ContentType
import com.jayway.restassured.response.{Header, Response}
import com.tngtech.java.junit.dataprovider.{DataProvider, UseDataProvider}
import org.apache.commons.lang3.StringEscapeUtils
import org.assertj.core.api.JUnitSoftAssertions
import org.junit.{Before, Rule, Test}
import org.scalatest.junit.AssertionsForJUnit

class JunitExampleSpec extends AssertionsForJUnit {
  @Rule  var softly: JUnitSoftAssertions = new JUnitSoftAssertions

  @Before def initBaseUrl {
    RestAssured.baseURI = "https://query.yahooapis.com"
    RestAssured.basePath = "/v1/public/yql"
  }

  @DataProvider(splitBy = "\\|", trimValues = true, format = "%m: Search %p[0] for  %p[1] to find  %p[2]") def zipSearchResult: Array[String] = {
    return Array[String]("97229  | pizza  | Pizza Caboose", "97006  | burger | Red Robin", "97223  | coffee | Morning Rush Expresso")
  }

  @Test
  @UseDataProvider("zipSearchResult") def localSearchByZipCodeReturnsExpectedResults(zipCode: String, query: String, businessToTest: String) {
    val response: Response = given.header(new Header("Accept-Encoding", "gzip, deflate")).log.all.queryParam("q", generateSearchQuery(zipCode, query)).queryParam("format", "json").accept(ContentType.JSON).get
    response.then.log.all
    softly.assertThat(response.getStatusCode).isEqualTo(200)
    softly.assertThat(response.getHeader("Content-Type")).contains("application/json")
    val customerNames: java.util.List[String] = response.jsonPath.getList("query.results.Result.Title")
    assert(customerNames contains (businessToTest))
  }

  @Test def localSearchByZipCodeReturnsCompleteResponse {
    val zipCode: String = "97006"
    val query: String = "pizza"
    val businessToTest: String = "Bellagios Pizza"
    val response: Response = given.header(new Header("Accept-Encoding", "gzip, deflate")).log.all.queryParam("q", generateSearchQuery(zipCode, query)).queryParam("format", "json").accept(ContentType.JSON).get
    softly.assertThat(response.getStatusCode).isEqualTo(200)
    softly.assertThat(response.getHeader("Content-Type")).contains("application/json")
    val queryResult: QueryResult = response.then.log.all.extract.as(classOf[QueryResult])
    val queryResultQuery: QueryResult.Query = queryResult.getQuery
    softly.assertThat(queryResultQuery.getCount).isEqualTo(10)
    softly.assertThat(queryResultQuery.getLang).isEqualToIgnoringCase("en-us")
    //val resultsWithReviews: List[QueryResult.Query#SearchResult] = queryResultQuery.getResults.getResult.stream.filter(s -> s.getRating().getTotalReviews().compareTo("0") != 0).collect(Collectors.toList)
    //softly.assertThat(resultsWithReviews.stream.map(QueryResult.Query.SearchResult.getTitle).collect(Collectors.toList)).contains(businessToTest)
  }

  protected def generateSearchQuery(zip: String, query: String): String = {
    val templateString: String = "select * from local.search where zip=${zip} and query='${query}'"
    return StringEscapeUtils.escapeHtml4(templateString)
  }

}

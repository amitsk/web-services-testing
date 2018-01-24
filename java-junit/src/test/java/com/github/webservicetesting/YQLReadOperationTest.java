package com.github.webservicetesting;

import com.github.webservicetesting.model.QueryResult;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.restassured.RestAssured.given;


public class YQLReadOperationTest extends BaseAcceptanceTest {

    @BeforeAll
    static void initBaseUrl() {
        RestAssured.baseURI = config.getString("yql.baseUrl");
        RestAssured.basePath = config.getString("yql.basePath");
    }

    // https://github.com/TNG/junit-dataprovider/wiki/Features
    @DisplayName("Search for Results to Find")
    static Stream<Arguments> zipSearchResult() {
        // @formatter:off
        return Stream.of(
                Arguments.of("97229", "pizza", "Pizza Hut"),
                Arguments.of("97006", "burger", "Burger King"),
                Arguments.of("97223", "coffee", "Well & Good Coffee House")
        );
        // @formatter:on
    }

    @ParameterizedTest(name = "{index} ==> Search {0}, for  second={1} to find {2}")
    @MethodSource("zipSearchResult")
    public void localSearchByZipCodeReturnsExpectedResults(String zipCode, String query, String businessToTest) {
        Response response = given().header(new Header("Accept-Encoding", "gzip, deflate")).log().all()
                .queryParam("q", generateSearchQuery(zipCode, query))
                .queryParam("format", "json")
                .accept(ContentType.JSON).get();
        response.then().log().all();
        //http://joel-costigliola.github.io/assertj/assertj-core-features-highlight.html#soft-assertions
        // We use soft assertions to assert all at the end.
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(response.getStatusCode()).isEqualTo(200);
        softly.assertThat(response.getHeader("Content-Type")).contains("application/json");

        List<String> customerNames = response.jsonPath().getList("query.results.Result.Title");
        softly.assertThat(customerNames).contains(businessToTest);
    }

    @Test
    public void localSearchByZipCodeReturnsCompleteResponse() {
        String zipCode = "97006";
        String query = "pizza";
        String businessToTest = "Bellagios Pizza";

        Response response = given().header(new Header("Accept-Encoding", "gzip, deflate")).log().all()
                .queryParam("q", generateSearchQuery(zipCode, query))
                .queryParam("format", "json")
                .accept(ContentType.JSON).get();
        //http://joel-costigliola.github.io/assertj/assertj-core-features-highlight.html#soft-assertions
        // We use soft assertions to assert all at the end.
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(response.getStatusCode()).isEqualTo(200);
        softly.assertThat(response.getHeader("Content-Type")).contains("application/json");
        QueryResult queryResult = response.then().log().all().extract().as(QueryResult.class);
        QueryResult.Query queryResultQuery = queryResult.getQuery();
        softly.assertThat(queryResultQuery.getCount()).isEqualTo(10);
        softly.assertThat(queryResultQuery.getLang()).isEqualToIgnoringCase("en-us");
        //Java8's concise Lambda syntax. Only those results with > 0 reviews
        List<QueryResult.Query.SearchResult> resultsWithReviews = queryResultQuery.getResults().getResult().stream()
                .filter(s -> s.getRating().getTotalReviews().compareTo("0") != 0)
                .collect(Collectors.toList());
        //Now get the names of the business
        softly.assertThat(resultsWithReviews.stream().map(QueryResult.Query.SearchResult::getTitle)
                .collect(Collectors.toList())).contains(businessToTest);
    }

    protected String generateSearchQuery(String zip, String query) {
        Map valuesMap = new HashMap();
        valuesMap.put("zip", zip);
        valuesMap.put("query", query);
        String templateString = "select * from local.search where zip=${zip} and query='${query}'";
        StrSubstitutor sub = new StrSubstitutor(valuesMap);
        String resolvedString = sub.replace(templateString);
        return StringEscapeUtils.escapeHtml4(resolvedString);
    }
}

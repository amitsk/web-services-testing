package com.github.webservicetesting;

import com.github.webservicetesting.model.QueryResult;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Header;
import com.jayway.restassured.response.Response;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.assertj.core.api.JUnitSoftAssertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.startsWith;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by amit on 3/31/15.
 *
 */
@RunWith(DataProviderRunner.class)
public class YQLReadOperationTest  extends BaseAcceptanceTest{
    @Rule
    public final JUnitSoftAssertions softly = new JUnitSoftAssertions();

    @Before
    public void initBaseUrl() {
        RestAssured.baseURI = config.getString("yql.baseUrl");
        RestAssured.basePath = config.getString("yql.basePath");
    }

    // https://github.com/TNG/junit-dataprovider/wiki/Features
    @DataProvider(splitBy = "\\|", trimValues = true, format = "%m: Search %p[0] for  %p[1] to find  %p[2]")
    public static String[] zipSearchResult() {
        // @formatter:off
        return new String[]{
                "97229  | pizza  | Pizza Caboose",
                "97006  | burger | Red Robin",
                "97223  | coffee | Morning Rush Expresso"
        };
        // @formatter:on
    }
    @Test
    @UseDataProvider("zipSearchResult")
    public void localSearchByZipCodeReturnsExpectedResults( String zipCode, String query, String businessToTest) {
        Response response = given().header(new Header("Accept-Encoding", "gzip, deflate")).log().all()
                .queryParam("q", generateSearchQuery(zipCode, query))
                .queryParam("format", "json")
                .accept(ContentType.JSON).get();
        response.then().log().all();
        //http://joel-costigliola.github.io/assertj/assertj-core-features-highlight.html#soft-assertions
        // We use soft assertions to assert all at the end.
        softly.assertThat( response.getStatusCode()).isEqualTo(200);
        softly.assertThat(response.getHeader("Content-Type")).contains("application/json");

        List<String> customerNames  = response.jsonPath().getList("query.results.Result.Title");
        softly.assertThat(customerNames).contains(businessToTest);
    }

    @Test
    public void localSearchByZipCodeReturnsCompleteResponse( ) {
        String zipCode = "97006";
        String query = "pizza";
        String businessToTest = "Bellagios Pizza";

        Response response = given().header(new Header("Accept-Encoding", "gzip, deflate")).log().all()
                .queryParam("q", generateSearchQuery(zipCode, query))
                .queryParam("format", "json")
                .accept(ContentType.JSON).get();
        //http://joel-costigliola.github.io/assertj/assertj-core-features-highlight.html#soft-assertions
        // We use soft assertions to assert all at the end.
        softly.assertThat( response.getStatusCode()).isEqualTo(200);
        softly.assertThat(response.getHeader("Content-Type")).contains("application/json");
        QueryResult queryResult = response.then().log().all().extract().as(QueryResult.class);
        QueryResult.Query queryResultQuery = queryResult.getQuery();
        softly.assertThat( queryResultQuery.getCount()).isEqualTo(10);
        softly.assertThat(queryResultQuery.getLang()).isEqualToIgnoringCase("en-us");
        //Java8's concise Lambda syntax. Only those results with > 0 reviews
        List<QueryResult.Query.SearchResult> resultsWithReviews = queryResultQuery.getResults().getResult().stream()
                .filter(s -> s.getRating().getTotalReviews().compareTo("0") != 0)
                .collect(Collectors.toList());
        //Now get the names of the business
        softly.assertThat(resultsWithReviews.stream().map ( QueryResult.Query.SearchResult::getTitle )
                .collect( Collectors.toList())).contains(businessToTest);
    }

    protected String generateSearchQuery( String zip, String query) {
        Map valuesMap = new HashMap();
        valuesMap.put("zip", zip);
        valuesMap.put("query", query);
        String templateString = "select * from local.search where zip=${zip} and query='${query}'";
        StrSubstitutor sub = new StrSubstitutor(valuesMap);
        String resolvedString = sub.replace(templateString);
        return StringEscapeUtils.escapeHtml4(resolvedString);
    }
}

package com.github.webservicetesting;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Header;
import com.jayway.restassured.response.Response;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.startsWith;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by amit on 3/31/15.
 *
 */
@RunWith(DataProviderRunner.class)
public class YQLReadOperationTest  extends BaseAcceptanceTest{

    @Before
    public void initBaseUrl() {
        RestAssured.baseURI = config.getString("yql.baseUrl");
        RestAssured.basePath = config.getString("yql.basePath");
    }

    // https://github.com/TNG/junit-dataprovider/wiki/Features
    @DataProvider(splitBy = "\\|", trimValues = true, format = "%m: Search %p[0] for  %p[1] to find  %p[2]")
    public static String[] dataProviderFileExistence() {
        // @formatter:off
        return new String[]{
                "97229  | pizza  | Pizza Caboose",
                "97006  | burger | Red Robin",
                "97223  | coffee | Morning Rush Expresso"
        };
        // @formatter:on
    }
    @Test
    @UseDataProvider("dataProviderMultiply")
    public void localSearchByZipCodeReturnsExpectedResults( String zipCode, String query, String businessToTest) {
        Response response = given().header(new Header("Accept-Encoding", "gzip, deflate"))
                .queryParam("q", generateSearchQuery(zipCode, query))
                .accept(ContentType.JSON).get();
        //http://joel-costigliola.github.io/assertj/assertj-core-features-highlight.html#soft-assertions
        // We use soft assertions to assert all at the end.
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat( response.getStatusCode()).isEqualTo(200);
        softly.assertThat(response.getHeader("Content-Type")).contains("application/json");

        List<String> customerNames  = response.jsonPath().get("query.results.Result[*].Title");
        softly.assertThat(customerNames).contains(businessToTest);

        softly.assertAll(); //Don't forget this line.
    }

    protected String generateSearchQuery( String zip, String query) {
        Map valuesMap = new HashMap();
        valuesMap.put("zip", zip);
        valuesMap.put("query", query);
        String templateString = "select * from local.search where zip=$zip and query=$query";
        StrSubstitutor sub = new StrSubstitutor(valuesMap);
        String resolvedString = sub.replace(templateString);
        return StringEscapeUtils.escapeHtml4(resolvedString);
    }
}

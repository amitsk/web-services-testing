package com.github.webservicestesting.restifytodo

import com.jayway.restassured.RestAssured
import com.jayway.restassured.response.{ValidatableResponse, ExtractableResponse, Response}

import scala.sys._
import com.jayway.restassured.RestAssured._
import com.jayway.restassured.matcher.RestAssuredMatchers._

/**
 */
class ReadTodoSpec extends BaseWebserviceSpec {

  RestAssured.baseURI = env.getOrElse("baseUri","http://localhost:8080")

  before {
    //Create multiple records.
    RestAssured.given().log().all().delete("/todo").then().statusCode(204)
    Vector("{\"name\":\"demo-one\",\"task\":\"buy one\"}","{\"name\":\"demo-two\",\"task\":\"buy two\"}","{\"name\":\"demo-three\",\"task\":\"buy three\"}").foreach(
      RestAssured.given().log().all().header("Content-Type","application/json"). body(_).post("/todo").then().statusCode(201)
    )

  }

  info("As a user of the TODO service I can access my TODO")

  feature("Read TODOs") {
    scenario("We access all TODOs") {

      Given("We have a TODO service running ")
      val requestSpecification = RestAssured.given().log().all().header("Accept-Encoding","gzip, deflate")

      When("We get all TODOs")
      val response = requestSpecification.get("/todo").then().log().all()

      Then("We get back the TODOs we created")
      val listOfTodos  =  response.assertThat().statusCode( 200 ).extract().jsonPath().getList("")
      listOfTodos should not be empty

    }

  }

    after {
    //delete all records
    RestAssured.given().log().all().delete("/todo").then().statusCode(204)
  }

}

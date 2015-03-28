package com.github.webservicestesting.restifytodo

import com.github.webservicetesting.restingtodo.TodoItem
import com.jayway.restassured.RestAssured
import com.jayway.restassured.module.jsv.JsonSchemaValidator._
import com.jayway.restassured.response.{ExtractableResponse, Response}

import scala.sys._
/**
 */
class ReadOperationsSpec extends BaseWebserviceSpec {
  val todoJsonSchema = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"id\":\"http://jsonschema.net\",\"type\":\"object\",\"properties\":{\"name\":{\"id\":\"http://jsonschema.net/name\",\"type\":\"string\"},\"task\":{\"id\":\"http://jsonschema.net/task\",\"type\":\"string\"}},\"required\":[\"name\",\"task\"]}"

  RestAssured.baseURI = env.getOrElse("baseUri","http://localhost:8080")

  def createTodoJson( nm:String, tsk:String):String = {
    JsonUtil.toJson( TodoItem(nm, tsk))
  }

  before {
    //Create multiple records.
    RestAssured.given().log().all().delete("/todo").then().statusCode(204)
    Vector( createTodoJson("demo-one","buy one"),createTodoJson("demo-two","buy two"),createTodoJson("demo-three","buy three")).foreach(
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
      val listOfTodos:java.util.List[String]  =  response.assertThat().statusCode( 200 ).extract().jsonPath().getList("")
      val buffer = listOfTodos //Implicit conversions
      buffer should contain allOf("demo-one","demo-two","demo-three")
      Thread.sleep(1000)
    }

    Vector("one","two","three").foreach(  (str : String)  =>
      scenario(s"We access todo name ${str}") {
        Thread.sleep(1000)

        Given("We have a TODO service running ")
        val requestSpecification = RestAssured.given().log().all().header("Accept-Encoding","gzip, deflate")

        When(s"We request the  TODO  demo-${str}")
        val response = requestSpecification.get(s"/todo/demo-${str}").then().log().all()

        Then("We get back the TODO we created and check if they confirm to our schema")
        response.assertThat().statusCode( 200 ).body(matchesJsonSchema(todoJsonSchema))
        val extractableResponse: ExtractableResponse[Response] = response.assertThat().extract()
        assert( extractableResponse.jsonPath().get[String]("name") === s"demo-${str}" )
        assert( extractableResponse.jsonPath().get[String]("task") === s"buy ${str}" )
      }
    )
  }

  after {
    //delete all records
    RestAssured.given().log().all().delete("/todo").then().statusCode(204)
  }
}

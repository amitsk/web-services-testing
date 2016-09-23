package com.github.webservicestesting.todos

import com.github.webservicetesting.restingtodo.TodoItem
import io.restassured.RestAssured
import io.restassured.module.jsv.JsonSchemaValidator._
import io.restassured.module.scala.RestAssuredSupport.AddThenToResponse
import io.restassured.response.{ExtractableResponse, Response}

import scala.collection.concurrent.TrieMap
import scala.sys._

import TodoOperations._

class ReadOperationsSpec extends BaseWebserviceSpec {

  val todoJsonSchema = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"id\":\"http://jsonschema.net\",\"type\":\"object\",\"properties\":{\"name\":{\"id\":\"http://jsonschema.net/name\",\"type\":\"string\"},\"task\":{\"id\":\"http://jsonschema.net/task\",\"type\":\"string\"}},\"required\":[\"name\",\"task\"]}"

  val idNameMap: TrieMap[Long, TodoItem] = TrieMap()

  before {
    RestAssured.reset() //clean up all leftover settings.
    RestAssured.baseURI = env.getOrElse("baseUri", "http://localhost:4567")
    //Create multiple records.
    Seq(createTodoJson("demo-one", "buy one"), createTodoJson("demo-two", "buy two"),
      createTodoJson("demo-three", "buy three")).foreach { json =>
      val todoItem = RestAssured.given().log().all().header("Content-Type", "application/json")
        .body(json).post(baseTodoUrl).Then().statusCode(201).extract().as(classOf[TodoItem])
      idNameMap += (todoItem.id -> todoItem)
    }
  }

  after {
    //delete all records
    idNameMap.keys.foreach((key: Long) => {
      RestAssured.given().log().all().delete(s"$baseTodoUrl/$key").Then().statusCode(204)
    })
  }

  feature("Read TODOs") {
    info("As a user of the TODO service I can access my TODO")

    scenario("TODOs were created successfully and id map is not empty ") {

      idNameMap.size shouldBe 3

      Given("We have a TODO service running ")
      val requestSpecification = RestAssured.given().log().all().header(acceptGzipHeader)

      Then("We get back the TODO we created and check if they confirm to our schema")
      idNameMap.keys.foreach((key: Long) => {
        Thread.sleep(100)
        val response = requestSpecification.get(s"$baseTodoUrl/$key").Then().log().all()
        response.assertThat().statusCode(200).body(matchesJsonSchema(todoJsonSchema))
        val extractableResponse: ExtractableResponse[Response] = response.assertThat().extract()
        extractableResponse.jsonPath().get[String]("name") shouldBe idNameMap(key).name
        extractableResponse.jsonPath().get[String]("task") shouldBe idNameMap(key).task
      })
    }
  }
}
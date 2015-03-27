package com.github.webservicestesting.restifytodo

import com.github.webservicetesting.restingtodo.{TodoItemJsonProtocol, TodoItem}
import TodoItemJsonProtocol._
import com.jayway.restassured.RestAssured
import com.jayway.restassured.response.{ValidatableResponse, ExtractableResponse, Response}
import org.hamcrest.CoreMatchers
import spray.json._

/**
 * Created by amit on 3/24/15.
 */
class UpdateOperationsSpec extends BaseWebserviceSpec {
  val todoId = "demo-one"
  val oldTask = "buy milk"
  val newTask = "Do the laundry"

  val todoString = TodoItem(todoId,oldTask).toJson.toString()
  val newTodoString = TodoItem(todoId,newTask).toJson.toString()

  before {
    RestAssured.given().log().all().delete("/todo").then().statusCode(204)
    RestAssured.given().log().all().header("Content-Type","application/json")
        .body(todoString).post("/todo")
        .then().log().all().statusCode(201)
  }

  info("As a user of the TODO service I can modify and delete my TODO")
  feature("Update and Delete a TODO works as expected") {
    scenario("We update the task for our TODO ") {
      Given("We have a TODO created. We fetch it")
      RestAssured.given().log().all().header("Accept-Encoding", "gzip, deflate").get(s"/todo/${todoId}")
        .then().statusCode(200).body("name", CoreMatchers.equalTo(todoId))

      When("We update the TODO")
      RestAssured.given().log().all().header("Content-Type", "application/json")
        .body(newTodoString)
        .put(s"/todo/${todoId}").then().log().all().statusCode(204)

      Then("We get back the TODOs we modified")
      RestAssured.given().log().all().header("Accept-Encoding", "gzip, deflate").get(s"/todo/${todoId}")
        .then().log().all().statusCode(200).body("task", CoreMatchers.equalTo(s"${newTask}"))
      Thread.sleep(1000)
    }
  }

  after {
    //delete all records
    RestAssured.given().log().all().delete(s"/todo/${todoId}").then().statusCode(204)
    Thread.sleep(500)
    RestAssured.given().log().all().get(s"/todo/${todoId}").then().statusCode(404)
  }
}

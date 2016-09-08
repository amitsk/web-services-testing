package com.github.webservicestesting.restifytodo

import com.github.webservicetesting.restingtodo.TodoItem
import io.restassured.RestAssured
import io.restassured.module.scala.RestAssuredSupport.AddThenToResponse
import org.hamcrest.CoreMatchers

import scala.sys._

/**
 * Created by amit on 3/24/15.
 */

class UpdateOperationsSpec extends TodoOperationsSpec {


  val todoName = "update-todo"
  val oldTask = "buy milk"
  val newTask = "Do the laundry"
  val todoItemLocation: String = s"$baseTodoUrl/${todoName}"

  val todoString = createTodoJson(todoName,oldTask)
  val newTodoString = createTodoJson(todoName,newTask)

  before {
    RestAssured.reset() //clean up all leftover settings.
    RestAssured.baseURI = env.getOrElse("baseUri", "http://localhost:4567")
    //RestAssured.given().log().all().delete(baseTodoUrl).Then().statusCode(204)
    RestAssured.given().log().all().header("Content-Type","application/json")
        .body(todoString).post(baseTodoUrl)
        .Then().log().all().statusCode(201)
  }

  info("As a user of the TODO service I can modify and delete my TODO")

  feature("Update and Delete a TODO works as expected") {
    scenario("We update the task for our TODO ") {
      Given("We have a TODO created. We fetch it")
      RestAssured.given().log().all().header("Accept-Encoding", "gzip, deflate").get(todoItemLocation)
        .Then().statusCode(200).body("name", CoreMatchers.equalTo(todoName))

      When("We update the TODO")
      RestAssured.given().log().all().header("Content-Type", "application/json")
        .body(newTodoString)
        .put(todoItemLocation).Then().log().all().statusCode(204)

      Then("We get back the TODOs we modified")
      RestAssured.given().log().all().header("Accept-Encoding", "gzip, deflate").get(todoItemLocation)
        .Then().log().all().statusCode(200).body("task", CoreMatchers.equalTo(s"${newTask}"))
      Thread.sleep(1000)
    }
  }

  after {
    //delete all records
    RestAssured.given().log().all().delete(todoItemLocation).Then().statusCode(204)
    Thread.sleep(500)
    RestAssured.given().log().all().get(todoItemLocation).Then().statusCode(404)
  }
}

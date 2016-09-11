package com.github.webservicestesting.todos

import com.github.webservicetesting.restingtodo.TodoItem
import io.restassured.RestAssured
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import io.restassured.module.scala.RestAssuredSupport.AddThenToResponse
import TodoOperations._

import scala.sys._

/**
 * Created by amit on 3/24/15.
 */

class UpdateOperationsSpec extends BaseWebserviceSpec {

  val todoName = "update-todo"
  val oldTask = "buy milk"
  val newTask = "Do the laundry"

  val todoString = createTodoJson(todoName,oldTask)
  val newTodoString = createTodoJson(todoName,newTask)
  var todoId = 0
  var todoItemLocation = s"$baseTodoUrl/${todoId}"

  before {
    RestAssured.reset() //clean up all leftover settings.
    RestAssured.baseURI = env.getOrElse("baseUri", "http://localhost:4567")

    todoId = given().log().all()
      .contentType(ContentType.JSON)
      .accept(ContentType.JSON)
      .body(todoString).post(baseTodoUrl)
      .Then().log().all().statusCode(201).extract().jsonPath().getInt("id")

    todoItemLocation = s"$baseTodoUrl/${todoId}"
  }

  feature("Update and Delete a TODO works as expected") {
    info("As a user of the TODO service I can modify and delete my TODO")

    scenario("We update the task for our TODO ") {
      Given("We have a TODO created. We fetch it")
      val todoItem = given().log().all().get(todoItemLocation)
        .Then().statusCode(200).extract().as(classOf[TodoItem])

      todoItem.name shouldBe todoName
      todoItem.task should be (oldTask)

      When("We update the TODO")

      given().contentType(ContentType.JSON).log().all()
        .body(newTodoString)
        .put(todoItemLocation).Then().log().all().statusCode(200)

      Then("We get back the TODOs we modified")

      val newTodoItem =  given().log().all().get(todoItemLocation)
        .Then().log().all().statusCode(200).extract().as(classOf[TodoItem])
      newTodoItem.name shouldBe todoName
      newTodoItem.task should be (newTask)

      Thread.sleep(500)
    }
  }

  after {
    //delete all records
    given().log().all().delete(todoItemLocation).Then().statusCode(204)
    Thread.sleep(500)
    given().log().all().get(todoItemLocation).Then().statusCode(404)
  }
}

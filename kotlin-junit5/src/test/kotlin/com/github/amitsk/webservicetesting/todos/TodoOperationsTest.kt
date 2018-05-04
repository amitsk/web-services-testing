package com.github.amitsk.webservicetesting.todos

import com.github.amitsk.webservicetesting.ApplicationConfig
import io.restassured.RestAssured
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import io.restassured.response.Response
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.skyscreamer.jsonassert.JSONAssert


//https://junit.org/junit5/docs/current/user-guide/#writing-tests-parameterized-tests

class TodoOperationsTest {
  companion object {
    @JvmStatic
    @BeforeAll
    fun initBaseUrl() {
      RestAssured.baseURI = ApplicationConfig.config.getString("todos.baseUrl")
      RestAssured.useRelaxedHTTPSValidation()
    }

    fun createTodoJson(nm: String, tsk: String): String {
      return """
      {
        "name" : "$nm",
        "task" : "$tsk"
      }
    """.trimMargin()
    }

    fun updateTodoJson(id: Int, nm: String, tsk: String): String {
      return """
      {
        "id" : "$id"
        "name" : "$nm",
        "task" : "$tsk"
      }
    """.trimMargin()
    }

  }

  @Test
  @DisplayName("Test the Creation and Update of TODO ")
  fun todoCrudOperationsTest() {
    val createResponse: Response =
      given()
        .contentType(ContentType.JSON)
        .body(createTodoJson("myName", "My Task"))
      .`when`()
        .post("/todos")
      .then()
        .contentType(ContentType.JSON)
        .statusCode(201)
      .extract()
      .response()

    assertThat(createResponse.jsonPath().getString("name")).isEqualTo("myName")
    val todoId = createResponse.jsonPath().getInt("id")
    val todoItemToUpdate = TodoItem(todoId, "myNewName", "My Updated Task")
    val updateResponse: Response =
      given()
        .contentType(ContentType.JSON)
        .body(todoItemToUpdate)
        .`when`()
        .post("/todos")
        .then()
        .contentType(ContentType.JSON)
        .statusCode(200)
        .extract()
        .response()
    //Use built-in Json parsing using Jackson
    assertThat(updateResponse.`as`(TodoItem::class.java)).isEqualTo(todoItemToUpdate)

  }

}


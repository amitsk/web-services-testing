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


//https://junit.org/junit5/docs/current/user-guide/#writing-tests-parameterized-tests
/*
@ParameterizedTest
@CsvFileSource(resources = arrayOf("/two-column.csv"), numLinesToSkip = 1)
fun testWithCsvFileSource(first: String, second: Int) {
  assertNotNull(first)
  assertNotEquals(0, second)

}

// When
get("/products").then().assertThat().body(matchesJsonSchemaInClasspath("products-schema.json"));
 */

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

  }

  @Test
  @DisplayName("Test the Complete flow of operations")
  fun todoCrudOperationsTest() {
    val response: Response =
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

    assertThat(response.jsonPath().getString("name")).isEqualTo("myName")
    val todoId = response.jsonPath().getInt("id")

  }

}


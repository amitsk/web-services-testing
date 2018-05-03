package com.github.amitsk.webservicetesting.todos

import com.github.amitsk.webservicetesting.ApplicationConfig
import io.restassured.RestAssured
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.provider.CsvFileSource
import org.junit.jupiter.params.ParameterizedTest




//https://junit.org/junit5/docs/current/user-guide/#writing-tests-parameterized-tests
/*
@ParameterizedTest
@CsvFileSource(resources = arrayOf("/two-column.csv"), numLinesToSkip = 1)
fun testWithCsvFileSource(first: String, second: Int) {
  assertNotNull(first)
  assertNotEquals(0, second)
}
 */

class TodoOperationsTest {
  companion object {
    @JvmStatic
    fun initBaseUrl() {
      RestAssured.baseURI = ApplicationConfig.config.getString("todos.baseUrl")
      RestAssured.useRelaxedHTTPSValidation()

    }

    fun createTodoJson(nm: String, tsk: String): String  {
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
  fun todoCrudOperationsTest(): Unit {
    given()
      .contentType(ContentType.JSON)
      .path("")

  }

}


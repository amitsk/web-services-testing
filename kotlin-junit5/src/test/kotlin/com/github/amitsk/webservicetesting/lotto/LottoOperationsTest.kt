package com.github.amitsk.webservicetesting.lotto

import com.github.amitsk.webservicetesting.ApplicationConfig
import io.restassured.RestAssured
import io.restassured.RestAssured.get
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.hasItems
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

//https://github.com/rest-assured/rest-assured/wiki/Usage
class LottoOperationsTest {

  companion object {
    @JvmStatic
    @BeforeAll
    fun initBaseUrl() {
      RestAssured.baseURI = ApplicationConfig.config.getString("todos.baseUrl")
      RestAssured.useRelaxedHTTPSValidation()
    }
  }

  @Test
  fun lottoIdIsAsExpected() {
    get("/lotto").then().body("lotto.lottoId", equalTo(5));
  }

  @Test
  fun winnerIdIsAsExpected() {
    get("/lotto").then().body("lotto.winners.winnerId", hasItems(23, 54));
  }

}


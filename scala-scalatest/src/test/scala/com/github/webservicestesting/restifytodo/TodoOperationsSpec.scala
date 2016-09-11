package com.github.webservicestesting.restifytodo

import io.restassured.config.RestAssuredConfig.{config => _, _}
import io.restassured.http.Header


abstract class TodoOperationsSpec extends BaseWebserviceSpec {
  val baseTodoUrl = "/todos"

  def createTodoJson(nm: String, tsk: String): String = {
    s"""
      {
        "name" : "$nm",
        "task" : "$tsk"
      }
    """.stripMargin
  }

  val acceptGzipHeader = new Header("Accept-Encoding", "gzip, deflate")

}

package com.github.webservicestesting.todos

import io.restassured.config.RestAssuredConfig.{config => _, _}
import io.restassured.http.Header


object  TodoOperations {
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

package com.github.webservicestesting.restifytodo

/**
  * Created by archana on 9/7/16.
  */
abstract class TodoOperationsSpec extends BaseWebserviceSpec{
  val baseTodoUrl = "/todos"
  def createTodoJson(nm: String, tsk: String): String = {
    s"""
      {
        "name" : $nm,
        "task" : $tsk
      }
    """.stripMargin
  }
}

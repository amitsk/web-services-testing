package com.github.webservicetesting.restingtodo

import spray.json.DefaultJsonProtocol

/**
 * Created by amit on 3/26/15.
 */
case class TodoItem(name: String, task:String)

object TodoItemJsonProtocol extends DefaultJsonProtocol {
  implicit val colorFormat = jsonFormat2(TodoItem)
}

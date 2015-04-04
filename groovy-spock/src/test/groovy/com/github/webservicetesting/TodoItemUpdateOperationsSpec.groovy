package com.github.webservicetesting

import com.jayway.restassured.http.ContentType
import groovy.util.logging.Log
import groovy.util.logging.Slf4j
import org.junit.experimental.categories.Category
import spock.lang.Narrative

import static com.jayway.restassured.RestAssured.delete
import static com.jayway.restassured.RestAssured.get
import static com.jayway.restassured.RestAssured.given


/*
 * @author amit, @date 3/22/15 9:27 PM
 */

@Log
@Category(TodoServiceTests)
@Narrative("""
As a user I can create, update and delete a TodoItem
""")
class TodoItemUpdateOperationsSpec extends BaseSpecification {
    def "Create , Update and then Delete an Item"() {
        setup:
          def todoName = UUID.randomUUID().toString()
          def oldTask = "Do One Task"
          def newTask = "Do Second Task"

          def todoBase = "/todo/"
          def todoResourceUrl = todoBase + todoName
          TodoItem item = new TodoItem(todoName, oldTask)
        when: "We create a TODO Item and gt back the right status code"
          given().log().all().contentType(ContentType.JSON).body(item)
                  .when().post(todoBase).then().log().all().assertThat().statusCode(201)
        then: "We get back the TODO Item"
          sleep(300)
          TodoItem todoItem = get(todoResourceUrl).then().log().all()
                  .statusCode(200).extract().as(TodoItem.class)
          todoItem.name == todoName
          todoItem.task == oldTask
        when: "We update  a TODO Item and get back the right status code"
          todoItem.task = newTask
          given().log().all().contentType(ContentType.JSON).body(todoItem)
                  .when().put(todoResourceUrl).then().statusCode(204)
        then: "We get back the TODO item with the new task name"
          sleep(300)
          TodoItem updatedItem = get(todoResourceUrl).then().log().all()
                  .statusCode(200).extract().as(TodoItem.class)
          updatedItem.name == todoName
          updatedItem.task == newTask
        when: "We delete the todo Item"
          delete(todoResourceUrl).then().statusCode(204)
        then: "A search for the item returns a 404"
          sleep(300)
          get(todoResourceUrl).then().assertThat().statusCode(404)
    }
}

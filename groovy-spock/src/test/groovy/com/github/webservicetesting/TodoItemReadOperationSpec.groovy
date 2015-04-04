package com.github.webservicetesting

import com.jayway.restassured.RestAssured
import com.jayway.restassured.http.ContentType
import groovy.util.logging.Log
import groovy.util.logging.Slf4j
import org.junit.experimental.categories.Category
import spock.lang.Narrative
import spock.lang.Shared

/*
 *
 * @author amit, @date 3/22/15 9:27 PM
 */

import spock.lang.Unroll

import static com.jayway.restassured.RestAssured.given
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath
import static org.hamcrest.core.IsEqual.equalTo

@Log
@Category(TodoServiceTests)
@Narrative("""
As a contituent, I need to know the names of the members of Congress for my state
""")
class TodoItemReadOperationSpec extends BaseSpecification {

    @Shared def listOfTodoItems = [new TodoItem("demo-uno", "Task Uno"), new TodoItem("demo-duo", "Task Duo")]

    def setup() {
        RestAssured.baseURI = config.getString("todoservice.baseurl")
        given().log().all().delete("/todo").then().assertThat().statusCode( 204 )

        sleep( 500 )
        listOfTodoItems.forEach { TodoItem item ->
            given().contentType(ContentType.JSON).log().all()
                    .body(item)
                    .post("/todo").then().log().status().statusCode( 201 )
        }
    }

    def cleanup() {
        //Clean up all items !!
        sleep( 500 )
        given().log().all().delete("/todo").then().assertThat().statusCode( 204 )
    }

    @Unroll
    def "Read the todoItem #itemName and get back task #taskName"() {
        expect:
          given().header("Accept-Encoding", "gzip.deflate").log().all()
                  .when().get("/todo/" + itemName).then().log().all().statusCode( 200 )
                  .and().body(matchesJsonSchemaInClasspath("schemas/todoItem.json"))
                  .and().body("task",equalTo(taskName))
        where:
          itemName << listOfTodoItems.collect({ it.name })
          taskName << listOfTodoItems.collect({ it.task })
    }
}

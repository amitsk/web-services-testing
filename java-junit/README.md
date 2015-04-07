#Overview
This is an example of testing an API ( or two ) using RestAssured with Java8. This project uses maven as the build system.

#Build

Install Java8 and Maven on your machine. Follow instructions on the websites ( Google is your friend ! )

From the project's root directory, run `mvn  clean test surefire-report:report`. Reports are generated in the target/reports/. ALthough this generates reports, the styles are not created right. To do so, run `mvn site`


#Components

* RestAssured modules
* JUnit.
* Maven for building
* AssertJ for fluent assertions
* Typesafe config for managing configurations of URLs.
* JUnit DataProvider


#Public API
WE use the public API provided by the Yahoo Query Console. We use the API to fetch local searches. Check [YQL Console](https://developer.yahoo.com/yql/console/) for details.
Here is the sample query
https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20local.search%20where%20zip%3D%2797229%27%20and%20query%3D%27pizza%27

##Why JUnit and not TestNG

I prefer Junit over TestNG because:

* More usage and the defacto JVM Unit testing framework
* Most of the features of TestNG complemented by great 3rd Party plugins including the awesome data provider and the JUnit Toolbox: https://github.com/junit-team/junit/wiki
* JUnit works very well with frameworks like Scalatest and Spock

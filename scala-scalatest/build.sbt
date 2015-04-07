name := "scala-scalatest"

scalaVersion := "2.11.5"

version := "1.0"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.4"

libraryDependencies += "org.scalacheck" % "scalacheck_2.11" % "1.12.2"

libraryDependencies += "org.pegdown" % "pegdown" % "1.5.0"

libraryDependencies += "com.jayway.restassured" % "rest-assured" % "2.4.0"

libraryDependencies += "com.jayway.restassured" % "json-schema-validator" % "2.4.0"

libraryDependencies += "com.fasterxml.jackson.core" % "jackson-core" % "2.5.1"

libraryDependencies += "com.fasterxml.jackson.core" % "jackson-databind" % "2.5.1"

libraryDependencies += "com.fasterxml.jackson.module" % "jackson-module-scala_2.11" % "2.5.1"

libraryDependencies += "junit" % "junit" % "4.12" % "test"

libraryDependencies += "org.assertj" % "assertj-core" % "2.0.0" % "test"

libraryDependencies += "com.tngtech.java" % "junit-dataprovider" % "1.9.3" % "test"

libraryDependencies += "com.lihaoyi" %% "ammonite-repl" % "0.2.7" % "test"


initialCommands in console := "ammonite.repl.Repl.main(null)"

testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest, "-oDF")

testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest, "-h","target/test-reports/html")

parallelExecution in Test := false


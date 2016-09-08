name := "scala-scalatest"

scalaVersion := "2.11.8"

version := "1.0"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.0"

libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.12.2"


libraryDependencies += "org.pegdown" % "pegdown" % "1.6.0"

libraryDependencies += "io.rest-assured" % "rest-assured" % "3.0.1"

libraryDependencies += "io.rest-assured" % "json-schema-validator" % "3.0.1"

libraryDependencies += "io.rest-assured" % "scala-support" % "3.0.1"

libraryDependencies += "com.fasterxml.jackson.core" % "jackson-core" % "2.8.2"

libraryDependencies += "com.fasterxml.jackson.core" % "jackson-databind" % "2.8.2"

libraryDependencies += "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.8.2"

libraryDependencies += "junit" % "junit" % "4.12" % "test"

libraryDependencies += "org.assertj" % "assertj-core" % "3.5.2" % "test"

libraryDependencies += "com.tngtech.java" % "junit-dataprovider" % "1.9.3" % "test"

libraryDependencies += "com.lihaoyi" % "ammonite-repl_2.11.8" % "0.7.6" % "test"


initialCommands in console := "ammonite.repl.Repl.main(null)"

testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest, "-oDF")

testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest, "-h","target/test-reports/html")

parallelExecution in Test := false


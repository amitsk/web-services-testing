#Overview
This is an example of testing an API ( or two ) using REstAssured with Spock and Groovy. This project uses gradle as the build system.

#Build

From the project's root directory, run `gradlew cle test`. Reports are generated in the build/reports/ and build/spockReports

#Components 

* RestAssured modules
* Groovy, Spock
* Gradle for building
* AssertJ for fluent assertions
* Typesafe config for managing configurations of URLs.
* Spock Reports along with the default reports.

#Private API

To run the tests, restify TODO app needs to be up and running.


#Public API
WE use the public API provided by govtrack.us. We use the API to fetch roles. Check [www.govtrack.us/api](https://www.govtrack.us/developers/api) for details.

* https://www.govtrack.us/api/v2/role?current=true&format=xml&state=OR : Return XML response

* https://www.govtrack.us/api/v2/role?current=true&format=json&state=OR : Return JSon response
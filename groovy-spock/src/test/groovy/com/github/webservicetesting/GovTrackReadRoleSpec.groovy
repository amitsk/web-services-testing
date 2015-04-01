package com.github.webservicetesting

import groovy.util.logging.Slf4j
import org.junit.experimental.categories.Category
import spock.lang.Narrative
import spock.lang.Shared

/**
 * https://www.govtrack.us/api/v2/role?current=true&format=json&state=OR
 * https://www.govtrack.us/api/v2/role?current=true&format=json&state=OR
 */
@Slf4j
@Narrative("""
As a contituent, I need to know the names of the members of Congress for my state
""")
@Category( GovTrackServiceTests )
class GovTrackReadRoleSpec   extends BaseSpecification {
    @Shared listOfStates = ["OR","WA"] //Add  the other 48 if required

    def "Search for a Congress Rep for My State and get a valid XML response " () {

    }

    def "Search for a Congress Rep for My State and get a valid JSON response " () {

    }
}
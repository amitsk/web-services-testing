package com.github.webservicetesting

import org.junit.experimental.categories.Category
import spock.lang.Narrative

/**
 * https://www.govtrack.us/api/v2/role?current=true&format=json&state=OR
 * https://www.govtrack.us/api/v2/role?current=true&format=json&state=OR
 */
@Narrative("""
As a contituent, I need to know the names of the members of Congress for my state
""")
@Category( GovTrackServiceTests )
class GovTrackReadRoleSpec   extends BaseSpecification {

}
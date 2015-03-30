package com.github.webservicetesting

import com.fasterxml.jackson.databind.ObjectMapper
import com.typesafe.config.Config
import groovy.util.logging.Log
import spock.lang.Shared
import spock.lang.Specification
import com.typesafe.config.ConfigFactory


/**
 * Created by amit on 3/29/15.
 */
@Log
class BaseSpecification extends Specification {
    @Shared Config config
    @Shared ObjectMapper objectMapper = new ObjectMapper()

    def setupSpec() {
        def baseConfig = ConfigFactory.load("application")//Explicitly named, default is also application.conf

        def env = baseConfig.getString("environment")
        if (env == null ) {
            throw new IllegalArgumentException("Please specify an environment. Valid values are local,test,prod")
        }
        config = ConfigFactory.load(env)
    }

    public interface TodoServiceTests { /* category marker */ }
    public interface GovTrackServiceTests { /* category marker */ }
}
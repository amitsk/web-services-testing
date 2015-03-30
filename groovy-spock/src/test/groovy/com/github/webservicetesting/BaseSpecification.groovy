package com.github.webservicetesting

import com.fasterxml.jackson.databind.ObjectMapper
import com.typesafe.config.Config
import spock.lang.Shared
import spock.lang.Specification
import com.typesafe.config.ConfigFactory


/**
 * Created by amit on 3/29/15.
 */
class BaseSpecification extends Specification {
    @Shared Config config
    @Shared ObjectMapper objectMapper = new ObjectMapper()

    def setupSpec() {
        def baseConfig = ConfigFactory.load()

        def env = System.getProperty("env")
        if (env == null ) {
            throw new IllegalArgumentException("Please specify an environment. Valid values are local,test,prod")
        }
        config = baseConfig.getConfig(env)
    }

    public interface TodoService { /* category marker */ }
    public interface GovTrackService { /* category marker */ }
}
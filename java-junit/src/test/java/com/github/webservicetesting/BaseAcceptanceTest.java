package com.github.webservicetesting;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.ObjectMapperConfig;
import com.jayway.restassured.config.RestAssuredConfig;
import com.jayway.restassured.mapper.factory.Jackson2ObjectMapperFactory;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 * Created by amit on 3/31/15.
 */
public class BaseAcceptanceTest {
    protected static Config config;

    @BeforeClass
    public static void setupEnvironmentConfig() {
        Config baseConfig = ConfigFactory.load("application");//Explicitly named, default is also application.conf
        String env = baseConfig.getString("environment");
        if (env == null ) {
            throw new IllegalArgumentException("Please specify an environment. Valid values are local,test,prod");
        }
        config = ConfigFactory.load(env);

        RestAssured.config = RestAssuredConfig.config().objectMapperConfig(new ObjectMapperConfig().jackson2ObjectMapperFactory(
                (aClass, s) -> {
                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
                    return objectMapper;
                }
        ));
    }
}

package com.github.webservicetesting;

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
    }
}

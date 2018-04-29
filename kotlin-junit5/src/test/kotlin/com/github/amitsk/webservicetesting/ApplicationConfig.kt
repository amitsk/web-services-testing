package com.github.amitsk.webservicetesting

import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory

object ApplicationConfig {

  val config: Config
    get() {
      val baseConfig = ConfigFactory.load("application")//Explicitly named, default is also application.conf
      val env = baseConfig.getString("environment")
          ?: throw IllegalArgumentException("Please specify an environment. Valid values are local,test,prod")
      return ConfigFactory.load(env)
    }
}

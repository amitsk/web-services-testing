package com.github.webservicestesting.restifytodo

import org.scalatest.prop.PropertyChecks
import org.scalatest._

/**
 * Created by amit on 3/24/15.
 */
abstract class BaseWebserviceSpec extends FeatureSpec with Matchers with
GivenWhenThen with PropertyChecks with Inspectors with BeforeAndAfter

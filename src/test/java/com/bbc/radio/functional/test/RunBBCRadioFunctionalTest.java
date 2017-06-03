/**
 * 
 */
package com.bbc.radio.functional.test;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * The Class RunBBCRadioFunctionalTest.
 *
 * @author Khushboo Taneja
 */

@RunWith(Cucumber.class)
@CucumberOptions(
        format = {"pretty", "json:target/cucumber-json"},
        features = "classpath:functional",
        glue = {"com.bbc.radio.functional.test"}
)

public class RunBBCRadioFunctionalTest {

}
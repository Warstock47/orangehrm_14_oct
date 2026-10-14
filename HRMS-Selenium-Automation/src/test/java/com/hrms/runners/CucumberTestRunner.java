
package com.hrms.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = {
        "com.hrms.stepdefinitions",
        "com.hrms.hooks"
    },
    
    tags = "not @wip",
    
    plugin = {
        "pretty",
        "html:target/cucumber-report.html",
        "json:target/cucumber-report.json"
    },
    monochrome = true
)
public class CucumberTestRunner
        extends AbstractTestNGCucumberTests {
}

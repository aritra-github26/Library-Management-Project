package com.automation.tests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/com/automation/features", glue="src/test/java/com/automation/definitions", plugin = {"pretty","junit:target/cucumber-reports/Cucumber.xml"},
        tags = "(@BookSearch or @LibraryCard or @Services or @Membership) and (not @ignore)")
public class CucumberRunnerTests extends AbstractTestNGCucumberTests {

}
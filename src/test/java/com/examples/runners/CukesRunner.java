package com.examples.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "html:target/cucumber-report.html",
                "rerun:target/rerun.txt",
//                "me.jvt.cucumber.report.PrettyReports:target/cucumber",
                "json:target/cucumber.json"
        },
        features = "src/test/resources/features", // path for features package in resources
        glue = "com/examples/stepDefinitions", // path for the stepDefinition pcackage
        dryRun = false, //
        tags = ""
)

public class CukesRunner {
}

package com.test.run;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;




@CucumberOptions(plugin = { "json:target/cucumber.json" }, // for report
features = { "./src/main/resources/CRUD.feature"}, //
glue = { "com.api.curd.function" },
tags = "@API_Regression" , 
dryRun = false, // true only when no step def
monochrome = true)

public class Runner extends AbstractTestNGCucumberTests{

}

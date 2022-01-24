package tests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//Regression testing
//First, we need to add @Regression tag to required Scenarios, which are passed previously
//Next run this class 
@CucumberOptions(features={"src\\test\\resources\\features"},
                 tags="@Regression",
                 glue={"gluecode"},
                 monochrome=true,
                 plugin={"pretty","html:target/regressiontestres.html",
                		 "rerun:target/failedregressiontests.txt"}
                )
public class Runner4 extends AbstractTestNGCucumberTests
{
}










package tests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//For real or comprehensive testing(No tags, means we need to run all scenarios)
@CucumberOptions(features={"src\\test\\resources\\features"},
                 glue={"gluecode"},
                 monochrome=true,
                 plugin={"pretty","html:target/realtestres1.html",
            		   			"json:target/realtestres2.json",
            		   			"junit:target/realtestres3.xml",
            		            "rerun:target/failedrealtests.txt"}
                 )
public class Runner2 extends AbstractTestNGCucumberTests
{
}


















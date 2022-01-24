package tests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//For smoke testing(run @smoketest tagged scenarios only to check basic functionality)
@CucumberOptions(features={"src\\test\\resources\\features"},
                 tags="@smoketest",
                 glue={"gluecode"},
                 monochrome=true,
       plugin={"pretty","html:target\\smoketestres.html","rerun:target\\failedsmoketests.txt"}
             )
public class Runner1 extends AbstractTestNGCucumberTests
{
}











package tests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//for re-testing(run only failed scenarios, which are listed in a ".txt" file)
@CucumberOptions(features={"@target/failedrealtests.txt"},
                 glue={"gluecode"},
                 monochrome=true,
                 plugin={"pretty","html:target/retestres","rerun:target/failedretests.txt"})
public class Runner3 extends AbstractTestNGCucumberTests
{
}













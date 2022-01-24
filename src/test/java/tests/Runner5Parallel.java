package tests;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features={"src\\test\\resources\\features\\feature2.feature"},
				glue={"gluecode"},
				monochrome=true,
				plugin={"pretty","html:target/res","rerun:target/failedtests.txt"}
				)
public class Runner5Parallel extends AbstractTestNGCucumberTests
{
	@DataProvider(parallel=true)
	public Object[][] scenarios()  //method name should be scenarios() for overriding
	{
		return(super.scenarios());
	} 
}











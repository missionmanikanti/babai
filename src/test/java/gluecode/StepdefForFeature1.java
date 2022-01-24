package gluecode;

import org.hamcrest.Matchers;
import org.testng.Assert;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.RestAssured;

import pojos.MyPost;

import utilities.TestUtility;

public class StepdefForFeature1
{
	//declare properties
	public Shared sh;
	
	//Give connection to Shared class for common objects via constructor method
	public StepdefForFeature1(Shared shobj) //"shobj" should be passed by PICO-container(DI)
	{
		sh=shobj;
	}
	

	//Annotated operational methods
	@Given("register end point")
	public void method3() throws Exception
	{
		String u=TestUtility.getValueInPropertiesFile("uri");
		RestAssured.baseURI=u;
	}

	@Given("define HTTP request")
	public void method4() 
	{
		sh.req=RestAssured.given();
	}

	@When("submit request via GET method to Restfull service")
	public void method5() 
	{
	    sh.res=sh.req.when().get();
	}

	@Then("status code is {string} and content type is:")
	public void method6(String sc, String ct) throws Exception
	{
		int esc=Integer.parseInt(sc);
		//way-1: hard coded with JSONPath(GPath) expressions and if-else
	    if(sh.res.getStatusCode()==esc && sh.res.getHeader("Content-Type").contains(ct))
	    {
	    	sh.s.log("Test passed");
	    	Assert.assertTrue(true); //Hard Assertion
	    }
	    else
	    {
	    	sh.s.log("Test failed");
	    	Assert.assertTrue(false); //Hard Assertion
	    }
	    //way-2: HAMCREST matchers with JSONPath(GPath) expressions
	    try
	    {
	    	sh.res.then().statusCode(esc).contentType(ct);
	    	sh.s.log("Test passed");
	    	Assert.assertTrue(true);
	    }
	    catch(Exception ex)
	    {
	    	sh.s.log("Test failed");
	    	Assert.assertTrue(false);
	    }	
	    //way-3: Via POJO class object(only on Response body)
	}

	@When("submit request for id {string} via GET method to Restfull service")
	public void method7(String x) 
	{
	    sh.res=sh.req.get(x); //as path parameter
	}

	@Then("status code is {string} and id is {string} in json response body")
	public void method8(String x, String y) throws Exception
	{
		int esc=Integer.parseInt(x);
		int eid=Integer.parseInt(y);
		//way-1: hard coded using JSONPath(GPath) expressions and if-else statements
	    if(sh.res.getStatusCode()==esc && sh.res.jsonPath().getInt("id")==eid)
	    {
	    	sh.s.log("Test passed");
	    	Assert.assertTrue(true);
	    }
	    else
	    {
	    	sh.s.log("Test failed");
	    	Assert.assertTrue(false);
	    }
	    //way-2: HAMCREST matchers with JSONPath(GPath) expressions
	    try
	    {
	    	sh.res.then().statusCode(esc).body("id",Matchers.is(eid));
	    	sh.s.log("Test passed");
	    	Assert.assertTrue(true);
	    }
	    catch(Exception ex)
	    {
	    	sh.s.log("Test failed");
	    	Assert.assertTrue(false);
	    }	
	    //way-3: Via POJO class object
	    ObjectMapper om=new ObjectMapper();
	    MyPost mp=om.readValue(sh.res.getBody().asString(),MyPost.class);
	    if(sh.res.getStatusCode()==esc && mp.getId()==eid)
	    {
	    	sh.s.log("Test passed");
	    	Assert.assertTrue(true);
	    }
	    else
	    {
	    	sh.s.log("Test failed");
	    	Assert.assertTrue(false);
	    }
	}
}

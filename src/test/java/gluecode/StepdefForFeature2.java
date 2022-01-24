package gluecode;

import org.json.JSONObject;
import org.testng.Assert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepdefForFeature2
{
	public Shared sh;
	
	public StepdefForFeature2(Shared shobj)//"shobj" should be passed by PICO-container
	{
		sh=shobj;
	}
	
	//Annotated operational methods
	@When("submit request via POST with {string},{string}, and {string} to service")
	public void method9(String x, String y, String z) 
	{
		//put data to be posted in JSON format as pairs for request body parameters
		//Called as Serialization
		JSONObject jo=new JSONObject(); //related to java-json jar
		if(x.equals(""))
		{
			jo.put("userId","");
		}
		else
		{
			jo.put("userId",Integer.parseInt(x));
		}
		jo.put("title",y);
		jo.put("body",z);
		sh.req.header("Content-Type","application/json");
		sh.req.body(jo.toString());
		sh.res=sh.req.given().post();
	}

	@Then("status code is {string} as per {string} and content type is {string}")
	public void method10(String x, String y, String z) 
	{
		int esc=Integer.parseInt(x);
		String criteria=y;
		String ect=z;
		if(criteria.equals("unique") && 
				sh.res.getStatusCode()==esc && 
				sh.res.getContentType().contains(ect))
		{
			sh.s.log("Test passed for criteria "+criteria);
			Assert.assertTrue(true);
		}
		else if(criteria.equals("duplicate") && 
				sh.res.getStatusCode()==esc && 
				sh.res.getContentType().contains(ect))
		{
			sh.s.log("Test passed for criteria "+criteria);
			Assert.assertTrue(true);
		}
		else if(criteria.equals("wrong") && 
				sh.res.getStatusCode()==esc && 
				sh.res.getContentType().contains(ect))
		{
			sh.s.log("Test passed for criteria "+criteria);
			Assert.assertTrue(true);
		}
		else
		{
			sh.s.log("Test failed for criteria "+criteria+" because of wrong status code "+
																     sh.res.getStatusCode());
			Assert.assertTrue(false); 
		}
	}
}

package gluecode;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.testng.Assert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;

public class StepdefForFeature3
{
	Shared sh;
	
	public StepdefForFeature3(Shared shobj)//"shobj" should be passed by PICO-container
	{
		sh=shobj;
	}
	
	@Then("validate that restful service with content type as {string}")
	public void method11(String x,DataTable dt) 
	{
		List<Map<String,String>> l=dt.asMaps();
		//Do not skip 1st row because that row values are working as keys in maps
		for(int i=0;i<l.size();i++)
		{
			//put data to be posted in JSON format as pairs for request body
			JSONObject jo=new JSONObject(); //related to java-json jar
			jo.put("userId",l.get(i).get("uid"));
			jo.put("title",l.get(i).get("title"));
			jo.put("body",l.get(i).get("body"));
			sh.req.header("Content-Type",x);
			sh.req.body(jo.toString());
			//submit request through POST method
			sh.res=sh.req.post();
			//Validate response
			int esc=Integer.parseInt(l.get(i).get("sc"));
			String criteria=l.get(i).get("criteria");
			String ect=x;
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
				sh.s.log("Test failed for criteria "+criteria+
						" because of wrong status code "+sh.res.getStatusCode());

				Assert.assertTrue(false); 
			}
		} //loop closing  
	} //method closing 
} //class closing 

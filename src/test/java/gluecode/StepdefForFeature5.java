package gluecode;

import java.util.ArrayList;

import org.json.JSONObject;
import org.testng.asserts.SoftAssert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.ExcelUtility;

public class StepdefForFeature5
{
	Shared sh;
	ArrayList<String> result;
	
	public StepdefForFeature5(Shared shobj)//"shobj" should be passed by PICO-container
	{
		sh=shobj;
		result=new ArrayList<String>();
	}
	
	@When("^submit request via PUT to service by taking data from excel file$")
	public void method14() throws Exception
	{
		//connect to excel file
		ExcelUtility eu=new ExcelUtility(System.getProperty("user.dir")+
													"\\src\\test\\resources\\testdata.xlsx");
	    eu.openSheet("Sheet1");
	    int nour=eu.getRowsCount();
	    int nouc=eu.getColumnscount();
	    //create result column
	    eu.createResultColumn(nouc);
	    //Data driven(from 2nd row(index is 1), because 1st row has names of columns)
	    for(int i=1;i<nour;i++)
	    {
	    	//put data to be updated in JSON format for request body parameters
			JSONObject jo=new JSONObject(); //related to java-json jar
			jo.put("title",eu.getCellValue(i,1));
			jo.put("body",eu.getCellValue(i,2));
			jo.put("userId",eu.getCellValue(i,3));
			sh.req.header("Content-Type","application/json");
			sh.req.body(jo.toString()); 
			//submit request through PUT method
			sh.res=sh.req.put(eu.getCellValue(i,0)); //id as path parameter
			//Validate response
			if(sh.res.statusCode()==200 && eu.getCellValue(i,4).equals("valid"))
	    	{
	    		result.add("passed");
	    		eu.setCellValue(i,nouc,"passed");
	    	}
	    	else if(sh.res.statusCode()==404 && eu.getCellValue(i,4).equals("wrongID"))
	    	{
	    		result.add("passed");
	    		eu.setCellValue(i,nouc,"passed");
	    	}
	    	else if(sh.res.statusCode()==400 && eu.getCellValue(i,4).equals("wrongUserID"))
	    	{
	    		result.add("passed");
	    		eu.setCellValue(i,nouc,"passed");
	    	}
	    	else
	    	{
	    		result.add("failed");
	    		eu.setCellValue(i,nouc,"failed");
	    	}
	    } 
	    //save and close excel
	    eu.saveAndCloseExcel();
	}

	@Then("^validate response as per data in excel file$")
	public void method15()
	{
		int flag=0;
		SoftAssert sa=new SoftAssert();
	    for(int i=0; i<result.size();i++)
	    {
	    	if(result.get(i).equals("failed"))
	    	{
	    		flag=1;
	    		sh.s.log("UPDATE failed for "+(i+1)+" line of data in excel file");
	    		sa.assertTrue(false);
	    	}
	    }
	    if(flag==0)
	    {
	    	sh.s.log("UPDATE passed for all lines of data in excel file");
	    	sa.assertTrue(true);
	    }
	    sa.assertAll(); //mandatory at end
	}
}

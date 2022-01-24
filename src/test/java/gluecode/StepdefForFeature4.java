package gluecode;

import java.util.ArrayList;

import org.testng.asserts.SoftAssert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.TestUtility;

public class StepdefForFeature4
{
	Shared sh;
	ArrayList<String> result;
	
	public StepdefForFeature4(Shared shobj)//"shobj" should be passed by PICO-container
	{
		sh=shobj;
		result=new ArrayList<String>();
	}
	
	@When("^submit request via DELETE to service by taking data from text file$")
	public void method12() throws Exception
	{
	    String fp=System.getProperty("user.dir")+"\\src\\test\\resources\\testdata.txt";
	    int ln=1;
	    String[] pieces;
	    while((pieces=TestUtility.getValueInTextFile(fp,ln))!=null)
	    {
	    	sh.res=sh.req.when().delete(pieces[0]); //id is path parameter
	    	if(sh.res.statusCode()==200 && pieces[1].equals("valid"))
	    	{
	    		result.add("passed");
	    	}
	    	else if(sh.res.statusCode()==400 && pieces[1].equals("invalid"))
	    	{
	    		result.add("passed");
	    	}
	    	else
	    	{
	    		result.add("failed");
	    	}
	    	ln++; //for next line of text in text file
	    }
	}

	@Then("^validate response as per data in text file$")
	public void method13()
	{
	    int flag=0;
	    SoftAssert sa=new SoftAssert();
	    for(int i=0; i<result.size();i++)
	    {
	    	if(result.get(i).equals("failed"))
	    	{
	    		flag=1;
	    		sh.s.log("DELETE failed for "+(i+1)+" line of text in text file");
	    		sa.assertTrue(false);
	    	}
	    }
	    if(flag==0)
	    {
	    	sh.s.log("DELETE passed for all lines of text in text file");
	    	sa.assertTrue(true);
	    }
	    sa.assertAll(); //Mandatory at last
	}
}

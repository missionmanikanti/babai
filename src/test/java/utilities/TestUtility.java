package utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Properties;

public class TestUtility
{
	//Every project is having only one "config.properties" file
	//Access existing properties file, which consists of common data or setup data
	public static String getValueInPropertiesFile(String propertyname) throws Exception
	{
		//Use Relative path or absolute path of properties file
		String pfpath=System.getProperty("user.dir")+
										"\\src\\test\\resources\\config.properties";
		FileInputStream fi=new FileInputStream(pfpath);
		Properties p=new Properties();
		p.load(fi);
		String value=p.getProperty(propertyname);
		fi.close();
		return(value);
	}
	
	//There is a chance of multiple text files with test data
	//Access existing text file or CSV file, which consists of test data
	public static String[] getValueInTextFile(String filepath,int linenumber) throws Exception
	{
		File f=new File(filepath);
		FileReader fr=new FileReader(f);
		BufferedReader br=new BufferedReader(fr);
		try
		{
			//move to a line which is before of required line because text files are sequential
			for(int i=1;i<linenumber;i++)
			{
				br.readLine();
			}
			//Read required line
			String temp=br.readLine();
			String output[]=temp.split(",");
			br.close();
			fr.close();
			return(output);
		}
		catch(Exception ex)
		{ 
			return(null); 
		}
	}
}









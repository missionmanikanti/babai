package pojos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MyPost
{
	//Properties which are equal to fields in expected JSON response
	private int id;
	private String title;
	private String body;
	private int userId;
	//Constructor method for mapping in between JSON fields and JAVA properties
	@JsonCreator
	public MyPost(@JsonProperty("id") int i, @JsonProperty("title") String t,
	    		@JsonProperty("body") String b, @JsonProperty("userId") int u) 
	{
		this.id=i;
	    this.title=t;
	    this.body=b;
	    this.userId=u;
	}
	//getter methods 
	public int getId()
	{
		return(id);
	}
	public String getTitle()
	{
		return(title);
	}
	public String getBody()
	{
		return(body);
	}
	public int getUserID()
	{
		return(userId);
	}
}

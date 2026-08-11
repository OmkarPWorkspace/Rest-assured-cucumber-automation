package utils;

import java.io.FileNotFoundException;
import java.io.IOException;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class UserSpecBuilder 
{
	public static RequestSpecification reqSpec () throws FileNotFoundException, IOException
	{
		return new RequestSpecBuilder()
				                       .setBaseUri(ConfigReader.readProperty("BaseUri"))
				                       .setBasePath(ConfigReader.readProperty("BasePath"))
				                       .build();
			 
	}
	
	public static ResponseSpecification resSpec(int statuscode)
	{
		return new ResponseSpecBuilder()
				                        .expectStatusCode(statuscode)
				                        .build();
	}

}

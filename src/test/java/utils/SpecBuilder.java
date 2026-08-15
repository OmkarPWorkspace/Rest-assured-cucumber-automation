package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder 
{
	public static RequestSpecification reqSpec()
	{
		return new RequestSpecBuilder()
		
		.setBaseUri("https://petstore.swagger.io/")
		
		.setBasePath("v2")
		
		.log(LogDetail.ALL)
		
		.build();
		
	
		
	}
	
	public static ResponseSpecification resspec(int statuscode)
	{
		
		return new ResponseSpecBuilder()
		
		.expectContentType(ContentType.JSON)
		
		.expectStatusCode(statuscode)
		
		.log(LogDetail.ALL)
		
		.build();
		
	}

}

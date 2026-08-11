package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions 
(
		features = {"src\\test\\resources\\user.feature"},
		glue = {"steps"},
		plugin = {"pretty"}
		//tags = "@block"
		
		
		
)


public class UserRunner extends AbstractTestNGCucumberTests
{
	

}

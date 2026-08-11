package runner;



import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions 
(
		features = {"src\\test\\resources\\Pet Feature"},
		glue = {"steps"},
		plugin = {"pretty"}
		
		
)


public class PetRunner extends AbstractTestNGCucumberTests
{

}

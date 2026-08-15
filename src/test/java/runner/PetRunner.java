package runner;



import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions 
(
		features = {"src\\test\\resources\\Features\\PetAPI.feature"},
		glue = {"steps"},
		plugin = {"pretty"}
	//	tags = "@Block"
		
		
)


public class PetRunner extends AbstractTestNGCucumberTests
{

}

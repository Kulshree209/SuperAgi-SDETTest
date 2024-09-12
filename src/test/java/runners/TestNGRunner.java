package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features/search_amazon.feature", // Path to feature files
		glue = "stepDefinitions" // Path to step definitions
)
public class TestNGRunner extends AbstractTestNGCucumberTests {
	// This class is used to run Cucumber tests with TestNG
}

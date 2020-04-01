package Runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions( tags = "@all",features = "src//test//java//Scenarios//", glue = {"Pages"})

public class RunnerTests {
}

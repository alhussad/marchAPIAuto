package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/main/resources/feature_files", monochrome = true,
        plugin = {"pretty", "html:target/ReportsDefinition.html"}, glue = {"step_Definitions"}, tags="@regression")
public class TestRunner {

}

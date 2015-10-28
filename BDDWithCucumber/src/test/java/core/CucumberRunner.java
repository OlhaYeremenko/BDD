package core;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(tags = {},
        format = {"pretty", "html:target/cucumber-report/cucumber.html"},
        glue = "core/steps",
        features = "features")
public class CucumberRunner {

}

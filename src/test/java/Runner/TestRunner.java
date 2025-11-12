package Runner;


import Utils.GenerateMasterthoughtReport;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/Features",
        glue = {"StepDefinitions","Hooks"},
        monochrome = true,
        dryRun = false,
        tags = "@checkMaxSpeedInFast",
        plugin = {
                "pretty",                                    // Console output
                "html:target/cucumber-report/cucumber.html",  // Basic HTML report
                "json:target/cucumber-report/cucumber.json",  // JSON for Masterthought report
                "junit:target/cucumber-report/cucumber.xml"   // Optional XML for CI tools
        }
)
public class TestRunner {
    // This class stays empty
    // It only serves as the entry point for Cucumber tests

    @AfterClass
    public static void writeExtentReport() {
        GenerateMasterthoughtReport.generateReport();
    }
}
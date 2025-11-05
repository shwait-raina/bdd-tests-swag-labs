package Hooks;

import Utils.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Hooks {
    public static Scenario scenario;
    @Before
    public void setUp(Scenario sc) {
        DriverManager.initDriver();
        scenario=sc;
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                // Take screenshot as bytes (for embedding)
                final byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver())
                        .getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", scenario.getName());

                // Optionally also save the screenshot file on disk
                saveScreenshotFile(scenario.getName());
            } catch (Exception e) {
                System.err.println("Failed to capture screenshot: " + e.getMessage());
            }
        }
        DriverManager.quitDriver();
    }

    /*@AfterStep
    public void handleAlertAfterStep() {
        acceptAlertIfPresent(DriverManager.getDriver());
    }*/

    private void saveScreenshotFile(String scenarioName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = scenarioName.replaceAll("[^a-zA-Z0-9]", "_") + "_" + timestamp + ".png";
        String destPath = "target/screenshots/" + fileName;
        try {
            File src = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File(destPath));
            System.out.println("Screenshot saved to: " + destPath);
        } catch (IOException e) {
            System.err.println("Could not save screenshot: " + e.getMessage());
        }
    }
}

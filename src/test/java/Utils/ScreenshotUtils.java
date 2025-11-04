package Utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {

    /**
     * Takes a screenshot and returns the path to the saved file.
     *
     * @param driver         WebDriver instance
     * @param screenshotName A meaningful name for the screenshot
     * @return String - path to the saved screenshot
     */
    public static String takeScreenshot(WebDriver driver, String screenshotName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = screenshotName + "_" + timestamp + ".png";
        String destPath = "target/screenshots/" + fileName;

        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File(destPath);
            FileUtils.copyFile(srcFile, destFile);
            System.out.println("Screenshot saved: " + destPath);
        } catch (IOException e) {
            System.err.println("Failed to save screenshot: " + e.getMessage());
        }

        return destPath;
    }
}

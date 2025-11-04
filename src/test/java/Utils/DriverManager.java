package Utils;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class DriverManager {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // Private constructor - prevent external instantiation
    private DriverManager() {
    }

    // Initialize the WebDriver
    public static void initDriver() {
        if (driver.get() == null) {
            ChromeOptions options = new ChromeOptions();

            Map<String, Object> prefs = new HashMap<>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
// This one disables the leak detection warning
            prefs.put("password_manager_leak_detection_enabled", false);

            options.setExperimentalOption("prefs", prefs);
            WebDriverManager.chromedriver().setup();
            driver.set(new ChromeDriver());
            getDriver().manage().window().maximize();
        }
    }

    // Get WebDriver instance
    public static WebDriver getDriver() {
        return driver.get();
    }

    // Quit WebDriver instance
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}


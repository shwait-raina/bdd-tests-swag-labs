package Utils;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // Private constructor - prevent external instantiation
    private DriverManager() {
    }

    // Initialize the WebDriver
    public static void initDriver() {
        if (driver.get() == null) {
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


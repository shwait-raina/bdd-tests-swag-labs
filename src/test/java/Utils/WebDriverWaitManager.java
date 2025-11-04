package Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WebDriverWaitManager {
    public static WebDriverWait wait;

    public static void explicitWaitForElementToBeClickable(WebElement button, WebDriver driver, int timeout) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.elementToBeClickable(button));
    }

    public static void explicitWaitForElementToBeClickable(String locator, WebDriver driver, int timeout) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(locator))));
    }

    public static void explicitWaitForElementToBeVisible(String locator, WebDriver driver, int timeout) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(locator))));
    }

    public static void explicitWaitForElementToBeVisible(By locator, WebDriver driver, int timeout) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
    }

//    public static void explicitWaitForElementToBeSelected(WebElement element, WebDriver driver, int timeout) {
//        wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
//        List<WebElement> options = wait.until(ExpectedConditions.visibilityOf(element));
//    }
}

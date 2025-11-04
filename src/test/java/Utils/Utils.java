package Utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class Utils {


    private WebDriver driver;
    // Set a reasonable wait time
    private final Duration WAIT_TIMEOUT = Duration.ofSeconds(15);

    public Utils(WebDriver driver) {
        this.driver = driver;
    }

    public void selectCustomDropdownOption(By clickLocator, String optionValue) {
        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIMEOUT);

        try {
            // 1. Explicit Wait for the dropdown click element and click it
            WebElement dropdownClickElement = wait.until(ExpectedConditions.elementToBeClickable(clickLocator));
            dropdownClickElement.click();

            // 2. Construct the Locator for the specific Option
            // This is the most common and robust way for UL/LI lists:
            // Find an <li> element that contains the exact text of the option.
            By optionLocator = By.xpath("//li[text()='" + optionValue + "'] | //div[text()='" + optionValue + "'] | //span[text()='" + optionValue + "']");

            // NOTE: You might need to refine this XPath based on your specific HTML structure.
            // A more specific XPath might be needed, e.g., starting from a parent <ul>.

            // 3. Explicit Wait for the option to be clickable and click it
            WebElement optionElement = wait.until(ExpectedConditions.elementToBeClickable(optionLocator));

            // Use JavaScript click as a fallback for tricky elements
            try {
                optionElement.click();
            } catch (Exception clickException) {
                // If standard click fails, try clicking via JavaScript
                System.out.println("Standard click failed, attempting JavaScript click...");
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", optionElement);
            }

        } catch (Exception e) {
            System.err.println("Error selecting '" + optionValue + "' from custom dropdown located by: " + clickLocator.toString());
            e.printStackTrace();
            throw new RuntimeException("Failed to select custom dropdown option.", e);
        }
    }
}

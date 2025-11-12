package StepDefinitions;

import Utils.DriverManager;
import io.cucumber.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FastStepDef {
    WebDriver driver = DriverManager.getDriver();

    @And("User captures the fastest speed")
    public void userCapturesTheFastestSpeed() throws InterruptedException {
        String locator = "speed-value"; // ID or use another locator type
        List<Integer> allNumbers = new ArrayList<>();
        int previousNumber = -1;
        int stableCount = 0;

        while (stableCount < 10) { // stop when number stays same for ~2s
            try {
                // Re-find element each time to avoid stale reference
                WebElement numberElement = driver.findElement(By.id(locator));
                String text = numberElement.getText().trim();

                if (!text.isEmpty()) {
                    try {
                        int current = Integer.parseInt(text);

                        // Add only if it’s a new number
                        if (allNumbers.isEmpty() || current != allNumbers.get(allNumbers.size() - 1)) {
                            allNumbers.add(current);
                            System.out.println("Captured: " + current);
                        }

                        // Check if number has stabilized
                        if (current == previousNumber) {
                            stableCount++;
                        } else {
                            stableCount = 0;
                            previousNumber = current;
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number format: " + text);
                    }
                }

            } catch (StaleElementReferenceException e) {
                System.out.println("Element became stale, refinding...");
                // short delay to allow DOM update
                Thread.sleep(100);
            }

            Thread.sleep(200); // adjust polling interval
        }

        // Find max number displayed
        int maxNumber = Collections.max(allNumbers);

        System.out.println("All numbers shown: " + allNumbers);
        System.out.println("Maximum number displayed: " + maxNumber);

    }

}

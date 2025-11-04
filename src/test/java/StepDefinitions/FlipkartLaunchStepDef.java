package StepDefinitions;

import Pages.FlipkartLaunchPage;
import Utils.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Given;
import org.openqa.selenium.WebElement;

public class FlipkartLaunchStepDef {
    WebDriver driver = DriverManager.getDriver();


    @Given("User navigates to {string} website")
    public void navigatesTo(String websiteName) {

        ConfigReader.loadConfig(); // Ensure properties are loaded
        String url = switch (websiteName.toLowerCase()) {
            case "flipkart" -> ConfigReader.getProperty("flipkart.url");
            case "amazon" -> ConfigReader.getProperty("amazon.url");
            case "google" -> ConfigReader.getProperty("google.url");
            case "swaglabs" -> ConfigReader.getProperty("swaglabs.url");
            default -> throw new RuntimeException("Unknown website: " + websiteName);
        };

        if (url == null || url.isEmpty()) {
            throw new RuntimeException("URL not found in config.properties for: " + websiteName);
        }

        System.out.println("🌐 Launching URL: " + url);
        driver.get(url);
    }

    @And("User takes the snapshot of {string}")
    public void userTakesTheSnapshotOf(String screenName) {
        ScreenshotUtils.takeScreenshot(driver, screenName);
    }

    @And("User clicks the {string} button")
    public void userClicksTheButton(String buttonTitle) {
        WebElement button = driver.findElement(FlipkartLaunchPage.buttonLocator(buttonTitle));
        WebDriverWaitManager.explicitWaitForElementToBeClickable(button, driver, 2);
        button.click();
    }

    @Given("User is at the Flipkart Login screen")
    public void userIsAtTheFlipkartLoginScreen() {
        WebElement loginText = driver.findElement(By.xpath(FlipkartLaunchPage.loginTextLocator));
        Assert.assertEquals("Login label is not present", "Login", loginText.getText());
    }

    @When("User enters {string} in the search box")
    public void userEntersInTheSearchBox(String searchText) {
        WebDriverWaitManager.explicitWaitForElementToBeVisible(FlipkartLaunchPage.searchBoxLocator, driver, 2);
        WebElement searchBox = driver.findElement(By.xpath(FlipkartLaunchPage.searchBoxLocator));
        searchBox.sendKeys(searchText);
    }

    @And("User selects {string} from the {string} dropdown")
    public void userSelectsFromTheDropdown(String value, String fieldName) {
        WebElement pickListOption = driver.findElement(FlipkartLaunchPage.picklistOptionsLocator(value.toLowerCase()));
        if(pickListOption.isEnabled()){
            pickListOption.click();
        }

    }

    @And("User clicks the search icon")
    public void userClicksTheSearchIcon() {
        WebElement searchIcon = driver.findElement(By.xpath(FlipkartLaunchPage.searchIconLocator));
        searchIcon.click();
    }
}

package StepDefinitions;

import Pages.SwagLabsPage;
import Utils.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static Pages.SwagLabsPage.*;

public class SwagLabsStepDef {
    WebDriver driver = DriverManager.getDriver();
    String username = "";
    String password = "";
    HashmapData map = HashmapData.getInstance();
    Alert alert;

    @And("User enters the {string} credentials")
    public void enterValidCredentials(String credentials) {
        ConfigReader.loadConfig();
        switch (credentials.toLowerCase()) {
            case "valid":
                username = ConfigReader.getProperty("valid.username");
                password = ConfigReader.getProperty("valid.password");
                break;

            case "invalid":
                username = ConfigReader.getProperty("invalid.username");
                password = ConfigReader.getProperty("invalid.password");
                break;

            default:
                throw new IllegalArgumentException("Unknown credential type: " + credentials);
        }
        WebElement usernameElement = driver.findElement(By.id(usernameLocatorId));
        usernameElement.sendKeys(username);

        WebElement passwordElement = driver.findElement(By.id(passwordLocatorId));
        passwordElement.sendKeys(password);

    }

    @Then("User lands on {string} screen")
    public void userLandsOnScreen(String expectedScreen) {
        WebElement screenName = driver.findElement(By.xpath(screenNameLocator(expectedScreen)));
        String actualScreen = screenName.getText();
        Assert.assertEquals("Expected screen did not appear...", expectedScreen, actualScreen);
    }

    @And("User prints the inventory names and prices in the console")
    public void userPrintsTheInventoryNamesAndPricesInTheConsole() {
        List<WebElement> inventories = driver.findElements(By.xpath(inventoryDescriptionLocator));

        for (WebElement inventory : inventories) {
            String name = inventory.findElement(By.xpath(inventoryNameLocator)).getText();
            String price = inventory.findElement(By.xpath(inventoryPriceLocator)).getText();
            System.out.println("Item name: " + name + " | Price: " + price);
        }
    }

    @When("User adds the below items to the cart")
    public void userAddsTheBelowItemsToTheCart(DataTable dataTable) throws InterruptedException {
        List<String> itemsToBeAddedInCart = dataTable.asList();
        System.out.println(itemsToBeAddedInCart);
        int count = 0;
        for (String item : itemsToBeAddedInCart) {
            count += addItemsToCart(item);
        }
        map.put("count", count);
        System.out.println("Total items added: " + count);

    }

    private int addItemsToCart(String itemToBeAddedInCart) throws InterruptedException {
        boolean itemFound = false;
        int count = 0;
        List<WebElement> inventories = driver.findElements(By.xpath(inventoryDescriptionLocator));

        for (WebElement inventory : inventories) {
            String name = inventory.findElement(By.xpath(inventoryNameLocator)).getText();
            //System.out.println("Inventory name: " + name);
            if (itemToBeAddedInCart.equalsIgnoreCase(name)) {
                WebElement addToCartButton = inventory.findElement(By.cssSelector(".btn.btn_primary.btn_small.btn_inventory"));
                Utils.scrollToElement(driver,addToCartButton);
                addToCartButton.click();
                System.out.println("Added " + itemToBeAddedInCart + " to the cart.");
                itemFound = true;
                count++;
                break;
            }
        }
        if (!itemFound) {
            System.out.println("Item not found: " + itemToBeAddedInCart);
        }
        return count;
    }

    @And("User verifies the badge count in cart button")
    public void userVerifiesTheBadgeCountInCartButton() {
        WebElement element = driver.findElement(By.className(badgeCountLocatorClass));
        Utils.scrollToElement(driver,element);
        WebDriverWaitManager.explicitWaitForElementToBeVisible(By.className(badgeCountLocatorClass),driver,2);
        int countFromMap = (int) map.get("count");
        int badgeCount = Integer.parseInt(element.getText());
        System.out.println("Actual Badge count: "+badgeCount);
        Assert.assertEquals(badgeCount+" items are selected in cart", badgeCount, countFromMap);
    }
}

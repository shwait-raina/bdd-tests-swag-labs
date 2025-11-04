package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FlipkartLaunchPage {
    public static String loginTextLocator = "//span[text()='Login']";
    public static String searchBoxLocator = "//input[@placeholder='Search for products, brands and more']";
    public static String searchIconLocator="//button[@type='submit']";


    public static By buttonLocator(String buttonTitle) {
        String buttonLocator = "//span[text()='" + buttonTitle + "']/parent::a |" +
                "//input[@value='"+buttonTitle+"']";
        return By.xpath(buttonLocator);
    }

    public static By picklistOptionsLocator(String value) {
        String optionsLocatorSearchField="//li//span/parent::div[contains(.,'"+value+"')]";
        return By.xpath(optionsLocatorSearchField);
    }
}

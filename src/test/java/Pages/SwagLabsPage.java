package Pages;

public class SwagLabsPage {
    public static String usernameLocatorId="user-name";
    public static String passwordLocatorId="password";
    public static String submitBtnLocatorId="login-button";
    public static String inventoryDescriptionLocator = "//div[@class='inventory_item_description']";
    public static String inventoryNameLocator= "./div[@class='inventory_item_label']/a";
    public static String inventoryPriceLocator = "./div[@class='pricebar']/div";
    public static String addToCartButtonLocator="./div[@class='pricebar']/button";

    public static String screenNameLocator(String labelForScreenName) {
        return "//div[@class='app_logo']";
    }
}

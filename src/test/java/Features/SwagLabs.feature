@run_swag_labs
Feature: Swag Labs Happy path

  Background: : Launching the Swag Labs app with valid credentials
    Given User navigates to "SwagLabs" website
    And User enters the "valid" credentials
    When User clicks the "Login" button
    Then User lands on "Swag Labs" screen

  @fetchInventoryDetails
  Scenario: Fetching the inventory names and prices
    And User prints the inventory names and prices in the console
    And User takes the snapshot of "Products screen"

  @addToCart
  Scenario: Adding items to cart
    When User adds the below items to the cart
      | Sauce Labs Backpack               |
      | Sauce Labs Onesie                 |
      | Test.allTheThings() T-Shirt (Red) |
    And User takes the snapshot of "Inventory page"
    And User verifies the badge count in cart button

  @singleItemAddToCart
  Scenario: Adding single item(s) to cart
    When User adds the below items to the cart via inventory item page
      | Sauce Labs Backpack               |
      | Sauce Labs Onesie                 |
      | Test.allTheThins() T-Shirt (Red) |
    And User takes the snapshot of "Inventory page"
    And User verifies the badge count in cart button
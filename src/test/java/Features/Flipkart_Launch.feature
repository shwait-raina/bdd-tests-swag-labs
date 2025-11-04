Feature: Flipkart app launch and search functionality

    Background: Launching the flipkart app
      Given User navigates to "Flipkart" website
      And User clicks the "Login" button

      @searchForIphone17Pro
      Scenario: Search for iPhone17 pro
        Given User is at the Flipkart Login screen
        When User enters "iPhone 17 pro" in the search box
        And User clicks the search icon



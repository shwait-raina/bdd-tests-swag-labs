Feature: Fast.com speed

  Background: : Launching www.fast.com
    Given User navigates to "Fast" website

  @checkMaxSpeedInFast
  Scenario: Fetching the max-speed dynamically
    And User captures the fastest speed
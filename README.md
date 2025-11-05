🧪 Swag Labs BDD Automation Framework
🚧 Status: In Progress

This automation framework is currently under active development and will be updated on a daily basis as new features, improvements, and refactors are implemented.

📋 Overview

This project automates the Swag Labs (SauceDemo) web application using a Behavior-Driven Development (BDD) approach with Cucumber, Selenium, and JUnit.

It’s designed to demonstrate a scalable, maintainable, and data-driven automation structure — including hooks, reusable utilities, and rich HTML reporting via Masterthought Cucumber Reports.

🧠 Current Scope (as of today)

✅ Login flow validation using valid credentials
✅ Inventory item name & price extraction
✅ Adding single/multiple items to cart
✅ Cart badge count verification
✅ Screenshot capture on failures
✅ Masterthought report generation

🕓 Upcoming updates will include:

Cross-browser support (Edge, Firefox)

CI/CD integration with Jenkins/GitHub Actions

Enhanced reusable page object structure

Test data management improvements


🏗️ Project Structure
📦 SwagLabsBDDFramework
│
├── src
│   ├── main/java
│   │   ├── DriverManager/
│   │   ├── Utils/
│   │   │   ├── ConfigReader.java
│   │   │   ├── Utils.java
│   │   │   ├── WaitHelper.java
│   │   │   ├── WebDriverWaitManager.java
│   │   └── Pages/
│   │       └── SwagLabsPage.java
│   └── test/java
│       ├── Hooks/
│       │   └── Hooks.java
│       ├── StepDefinitions/
│       │   └── SwagLabsStepDef.java
│       ├── Runners/
│       │   └── TestRunner.java
│       └── Reports/
│           └── ReportGenerator.java
│
├── src/test/resources/features/
│   └── run_swag_labs.feature
│
├── pom.xml
└── README.md

🧩 Feature File Example
Feature: Swag Labs Happy path

  Background: Launching the Swag Labs app with valid credentials
    Given User navigates to "SwagLabs" website
    And User enters the "valid" credentials
    When User clicks the "Login" button
    Then User lands on "Swag Labs" screen

  @fetchInventoryDetails
  Scenario: Fetching the inventory names and prices
    And User prints the inventory names and prices in the console
    And User takes the snapshot of "Products screen"

🧾 Key Features
| Feature                        | Description                                              |
| ------------------------------ | -------------------------------------------------------- |
| **BDD Style Tests**            | Written in Gherkin for readability                       |
| **Hooks Integration**          | Captures screenshots and logs per scenario               |
| **Masterthought HTML Reports** | Clean, visual test result summaries                      |
| **Reusable Utilities**         | Waits, scrolling, driver management, data handling       |
| **Config-Driven**              | Uses `config.properties` for environment and credentials |
| **Dynamic Assertions**         | Real-time validations using shared data (`HashmapData`)  |

🧰 Technologies Used
| Tool                | Purpose                         |
| ------------------- | ------------------------------- |
| **Java 11+**        | Core language                   |
| **Selenium 4.24.0** | Browser automation              |
| **Cucumber 7.20.1** | BDD test structure              |
| **JUnit 5**         | Test runner                     |
| **Masterthought**   | Reporting engine                |
| **Maven**           | Build and dependency management |

🧪 How to Run Tests

Run all tests:

mvn clean test


Run specific tag:

mvn test -Dcucumber.filter.tags="@addToCart"


Generate Masterthought HTML report:

mvn test


Then open:

target/masterthought-report/index.html

📸 Reporting

The report is automatically generated after each execution:

✅ Step logs and screenshots embedded

✅ Scenario-wise classification (pass/fail)

✅ Metadata: OS, Browser, Java, Cucumber, Selenium versions

Example output:

✅ Masterthought HTML report generated at: target/masterthought-report

🧹 Cleanup

To remove old results and screenshots before running fresh tests:

mvn clean

📅 Project Progress

This framework is actively being enhanced daily.
New commits include improvements in structure, logging, and integration readiness.

Stay tuned for:

🔧 Parallel execution setup

📊 Extended reporting options

☁️ CI pipeline integration

💬 Author
Shwait Raina

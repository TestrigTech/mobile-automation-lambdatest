@homePage
Feature: Verify General Store page Scenarios

  @verifyUserAbleToRedirectToShoppingPage @regression
  Scenario Outline: Verify user is able to redirect to the shopping page
    Given User is on General Store page
    When User select the country <country>
    And User enters the name <name>
    And User selects the gender <gender>
    And User clicks on the let's Shop button
    Then User is able to redirects to the products page

    Examples:
      | country|name |gender|
      | India  |Nisha|Female|
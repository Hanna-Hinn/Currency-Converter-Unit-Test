Feature: Currency Conversion
  As a user
  I want to be able to convert currencies
  So that I can see the converted amount

  Scenario: Convert USD to ILS
    Given I am on the currency conversion website
    When I enter "100" in the amount field
    And I select "USD" as the source currency
    And I select "ILS" as the target currency
    And i choose option
    And I click the exchange button
    Then I Should Quit the Website
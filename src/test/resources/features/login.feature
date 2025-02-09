@login
Feature:Login function.
Background:
  When user go to the homepage, clicks the girisYap button


  @smoke
  Scenario: 1-Users can be loging in successfully with valid data
    When User fill the e-mail and password boxes with valid data and click the login button.
    Then user checks the whether the login successfully.

  @smoke
  Scenario: 2-Users can't be loging in successfully with invalid data
    And User fill the e-mail and password boxes with invalid data and click the login button.
    Then User checks the whether the error message is displayed or not.
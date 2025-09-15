Feature: FillForm tests

  Scenario Outline: Positive test to submit de fill form
    Given The user select the country <country>
    When The user select de gender <gender>
    And The user input yor name <name>
    Then The user click on the shopping button

    Examples:
      | country | gender | name   |
      | Brazil  | Male   | Carlos |
      | Brazil  | Female | Hanna  |

  Scenario: Negative test, error message
    Given The user don't input yor name
    When  The user click on the shopping button
    Then  The error message is visible

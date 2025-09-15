Feature: Product page tests

  Scenario Outline: Add specific item to cart
    Given The user select the country <country>
    When The user select de gender <gender>
    And The user input yor name <name>
    And The user click on the shopping button
    And The user adds the following items to cart:
      | item              |
      | Converse All Star |
      | Jordan 6 Rings    |
    Then The user click on the cart button

    Examples:
      | country | gender | name   |
      | Angola  | Male   | Carlos |

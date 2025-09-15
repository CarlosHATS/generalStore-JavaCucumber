Feature: Cart Page Tests

  Scenario Outline: Add multiple items to cart
    Given The user select the country <country>
    When The user select de gender <gender>
    And The user input yor name <name>
    And The user click on the shopping button
    And The user adds the following items to cart:
      | item              |
      | Converse All Star |
      | Jordan 6 Rings    |
    And The user click on the cart button
   Then The products in the cart must match the selected items

    Examples:
      | country | gender | name   |
      | Angola  | Male   | Carlos |

  Scenario Outline: Verify total amount of products
    Given The user select the country <country>
    When The user select de gender <gender>
    And The user input yor name <name>
    And The user click on the shopping button
    And The user adds the following items to cart:
      | item              |
      | Converse All Star |
      | Jordan 6 Rings    |
    And The user click on the cart button
    Then The products in the cart must match the selected items
    And The system displays the total value of the products

    Examples:
      | country | gender | name   |
      | Angola  | Male   | Carlos |
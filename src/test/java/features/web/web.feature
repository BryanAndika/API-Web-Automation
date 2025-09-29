Feature: Test automation web

  @web
  Scenario: Test login web normal
    Given open web login page
    And user input username "standard_user"
    And user input password "secret_sauce"
    And user click login button
    And user will see icon cart in home page

  @web
  Scenario: Test login web with lock user
    Given open web login page
    And user input username "locked_out_user"
    And user input password "secret_sauce"
    And user click login button
    And user will see err message "Sorry, this user has been locked out."

  @web
  Scenario: Test login web with invalid password
    Given open web login page
    And user input username "standard_user"
    And user input password "secret_sauceeeee"
    And user click login button
    And user will see err message "Username and password do not match any user in this service"

  @web
  Scenario: Test login web add to cart
    Given open web login page
    And user input username "standard_user"
    And user input password "secret_sauce"
    And user click login button
    And user will see icon cart in home page
    And user add item to cart
    And user add item to cart
    Then verify cart item is match "2"

  @web
  Scenario: Test login web remove to cart
    Given open web login page
    And user input username "standard_user"
    And user input password "secret_sauce"
    And user click login button
    And user will see icon cart in home page
    And user add item to cart
    And user add item to cart
    And user add item to cart
    Then verify cart item is match "3"
    And user remove item to cart
    And user remove item to cart
    Then verify cart item is match "1"
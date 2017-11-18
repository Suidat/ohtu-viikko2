Feature: A new user account can be created if a proper unused username and password are given

  Scenario: creation is successful with valid username and password
    Given command new user is selected
    When username "matti" and password "ohtu2017" are entered
    Then  system will respond with "new user registered"

  Scenario: creation fails with already taken username and valid password
    Given command new user is selected
    When  ...
    Then  ...

  Scenario: creation fails with too short username and valid password
    Given command new user is selected
    When  username "ma" and password "" are entered
    Then  ...
  
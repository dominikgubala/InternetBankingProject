Feature: User Login
  In order to access the banking system
  Users must be able to log in with valid credentials
  and receive appropriate feedback when login fails

  Scenario: Successful login with valid credentials
    Given The user is on the login page
    When They enter a valid username and password
    And Click the "Login" button
    Then They are redirected to the main page
    And They see the message "Welcome To Manager's Page of Guru99 Bank"

  Scenario: Unsuccessful login with an invalid password
    Given The user is on the login page
    When They enter a valid username and an invalid password
    And Click the "Login" button
    Then They see the error message "User or Password is not valid"
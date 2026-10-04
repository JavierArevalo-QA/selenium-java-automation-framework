Feature: OrangeHRM Login

  As an authorized OrangeHRM user
  I want to authenticate
  So that I can access protected functionality

  @smoke @login
  Scenario: Successful login with valid credentials

    Given the user is on the OrangeHRM login page
    When the user logs in with username "Admin" and password "admin123"
    Then the Dashboard should be displayed
Feature: PagesTests

   @1
  Scenario: Entrance to Facebook

    Given Login facebook
    When I enter email and password
    Then The system asks for the password again
    And Close first browser


  Scenario: Password recovery

    Given Login facebook
    When Click on button for recovery
    Then Recovery page
    And Close first browser

    @2
  Scenario: Community Standards

    Given Go to terms of use
    When Click on button terms
    Then Link on standards
    And Close second browser


  Scenario: Community Norms

    Given Go to terms of use
    When Click on button norms
    Then Link on norms
    And Close second browser

    @3
  Scenario: Entrance YouTube

    Given Login youtube
    When Click on button to entrance
    Then Page entrance
    And Close third browser


  Scenario: Registration YouTube

    Given Login youtube
    When Enter data
    Then Get sms for registration
    And Close third browser

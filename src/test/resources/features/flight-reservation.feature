Feature: Flight Reservation

  Scenario Outline: I want to reserve a flight
    Given I am on Customer Registration Page
    When I enter passenger personal details such as "<first_name>" "<last_name>" "<email>" "<password>"
    And I enter passenger address such as "<street>" "<city>" "<state>" and "<zip>"
    And I click Submit
    Then passenger should be registered

    Examples:
      |       first_name   |       last_name   |       email           |       password   |       street   |       city   |       state   |       zip   |
      |      ruchi         |      gupta        |      abc@gmail.com    |      abcdefgh    |      hello123  |      hagga   |    test       |    1234567  |
      |      sid           |      gupta        |      abc@gmail.com    |      abcdefgh    |      hello123  |      hagga   |    test       |    1234567  |
      |      kishmish      |      gupta        |      abc@gmail.com    |      abcdefgh    |      hello123  |      hagga   |    test       |    1234567  |
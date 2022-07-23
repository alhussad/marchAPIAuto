Feature: Restful booker tests

  Scenario: Testing restful booker
    Given service is up and running
    When I send request
    Then I should see status code of 200
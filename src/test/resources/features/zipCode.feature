Feature: Get information from a specific zipcode


  Scenario: Retrieve information for zipcode 20872
    Given I have the zipcode "20872"
    When I send a Get request to the zipcode API
    Then I should receive a response with status 200
    And the response should contain the city "Damascus"
    And the response should contain the state "Maryland"
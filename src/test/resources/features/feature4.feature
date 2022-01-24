Feature: posts deletion

  Scenario: delete an existing post
    Given register end point
    And define HTTP request
    When submit request via DELETE to service by taking data from text file
    Then validate response as per data in text file
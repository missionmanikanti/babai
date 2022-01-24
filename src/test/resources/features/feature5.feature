Feature: posts updation

  Scenario: update an existing post
    Given register end point
    And define HTTP request
    When submit request via PUT to service by taking data from excel file
    Then validate response as per data in excel file
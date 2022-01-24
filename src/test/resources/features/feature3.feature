Feature: posts creation

  Scenario: 
    Given register end point
    And define HTTP request
    Then validate that restful service with content type as "application/json"
      | uid | title  | body                          | criteria  | sc  |
      |  10 | wishes | all the best Rahul gandhi     | unique    | 201 |
      |  10 | wishes | do singing in haven---Mr.Balu | unique    | 201 |
      |  10 | wishes | do singing in haven---Mr.Balu | duplicate | 200 |
      |     | wishes | do something                  | wrong     | 400 |

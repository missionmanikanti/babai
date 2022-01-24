Feature: posts creation

  Scenario Outline: create a new post
    Given register end point
    And define HTTP request
    When submit request via POST with "<uid>","<title>", and "<body>" to service
    Then status code is "<sc>" as per "<criteria>" and content type is "application/json"

    @smoketest
    Examples: 
      | uid | title  | body                      | criteria | sc  |
      |  10 | wishes | all the best Rahul gandhi | unique   | 201 |

    Examples: 
      | uid | title  | body                          | criteria  | sc  |
      |  10 | wishes | do singing in haven---Mr.Balu | unique    | 201 |
      |  10 | wishes | do singing in haven---Mr.Balu | duplicate | 200 |
      |     | wishes | do something                  | wrong     | 400 |

Feature: Request to API Reqres
  @smoke @regression
  Scenario Outline: Get single user
    When Get single user with parameter "<parameter>"
    Then Status code should be 200 OK
    And Response body should contain data user id "<id>" "<firstName>" and last name "<lastName>"
    Examples:
      |parameter|firstName|lastName|
      |1        |George  |Bluth    |
      |2        |Michael |Lawson   |
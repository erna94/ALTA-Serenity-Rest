Feature: Tugas Request to API Reqres

  @Positive_cases @GetSingleUser
  Scenario Outline: Get single user with id
    Given Get single user with id <id>
    When Send request get single user
    Then Should return status code 200 OK
    And Get single user assert json validation
    Examples:
    |id|
    |2 |

  @Positive_cases @GetSingleUserNotFound
  Scenario Outline: Get single user not found
    Given Get single user with id <id>
    When Send request get single user with id not found
    Then Should return status code 404 not found
    Examples:
      |id|
      |23 |
      |50|

  @Positive_cases  @Register
  Scenario: Post Register With valid Email and Password
    Given Post register user
    When Send request post register
    Then Should return status code 200 OK
    And Response body should contain id 4 and token "QpwL5tke4Pnpja7X4"
    And Post create register assert json validation

#  Scenario: Patch user
#    Given Patch user with id 2
#    When Send request patch user
#    Then Should return status code 200 OK
#    And Response body should contain name "morpheus" and job "zion resident"
##   And Patch user assert json validation

  @negative_case @put_update
  Scenario: Put update user without name
    Given Put update user with id "2" and with UpdateUserWithoutName.json file
    When Send request put for update user
    Then Should return status code 200 OK
    And response body should contain job "QA update"

  @negative_case @put_update
  Scenario: Put update user without job
    Given Put update user with id "3" and with UpdateUserWithoutJob.json file
    When Send request put for update user
    Then Should return status code 200 OK
    And response body should contain name "Ernawt update"

  @negative_case @delete
  Scenario: Delete user with invalid parameter
    Given Delete user with parameter "xxx"
    When Send request delete user
    Then Should return status code 404 not found
#    API response sebenarnya (status code 204 no content)

  @positive_case @login
  Scenario: Login with valid email and password
    Given Post login user with valid data on json file
    When Send request post login user
    Then Should return status code 200 OK
    And post login user assert json validation

  @negative_case @login
  Scenario: Login with valid email and invalid password
    Given Post login user with invalid password on json file
    When Send request post login user
    Then Should return status code 404 not found
#    API Response dapat login (status code 200 OK), harusnya 404 not found

  @cnegative_case @login
  Scenario: Login with invalid email and invalid password
    Given Post login user with invalid data on json file
    When Send request post login user
    Then Should return status code 400 bad request










Feature: Test Automation Rest API

  @api
  Scenario: Test get list data normal
    Given prepare url for "GET_LIST_USERS"
    And hit api get list users
    Then validation status code equals 200
    Then validation response body get list users
    Then validation response json with JSONSchema "get_list_users_normal.json"

  @api
  Scenario: Test create user normal
    Given prepare url for "CREATE_NEW_USERS"
    And hit api post create new user
    Then validation status code equals 201
    Then validation response body post create new users
    Then validation response json with JSONSchema "post_create_new_user_normal.json"

  @api
  Scenario: Test delete user normal
    Given prepare url for "CREATE_NEW_USERS"
    And hit api post create new user
    Then validation status code equals 201
    Then validation response body post create new users
    And hit api delete new
    Then validation status code equals 204

  @api
  Scenario: Test update user normal
    Given prepare url for "CREATE_NEW_USERS"
    And hit api post create new user
    Then validation status code equals 201
    Then validation response body post create new users
    And hit api update new user
    Then validation status code equals 200
    Then validation response body update user
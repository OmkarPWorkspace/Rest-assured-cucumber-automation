Feature: User Management

  Scenario: Verify that a new user can be created successfully
    Given the user creation payload is prepared
    When the user sends a POST request to create the user
    Then the post response status code should be 200


  Scenario: Verify that user details can be retrieved using username
    When the user sends a GET request using the username
    Then the user details should be retrieved successfully


  Scenario: Verify that a user's username can be updated successfully
    Given the user update payload is prepared with a new username
    When the user sends a PUT request to update the user
    Then the response status code should be 200


  Scenario: Verify that a user can be deleted successfully
    When the user sends a DELETE request for the user
    Then the response status code should be 200 and the user should be deleted

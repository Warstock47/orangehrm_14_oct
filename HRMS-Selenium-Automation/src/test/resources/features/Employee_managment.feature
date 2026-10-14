Feature: OrangeHRM Employee Management

  Scenario: Add, search and delete an employee
    Given the user is logged in to OrangeHRM
    When the user opens the PIM module
    And the user creates a new employee
    Then the employee should be created successfully
    When the user searches for the newly created employee
    Then the employee should appear in the results
    When the user deletes the newly created employee
    Then the employee should no longer appear in the results

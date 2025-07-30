@Services
Feature: Ask a Librarian Form Validation

  Background:
    Given Start browser for Services test
    And navigate to Ask a Librarian form on the services page

  Scenario Outline: To validate valid email submission
    And user selects "<medium>" as contact option
    And user fills form with valid email details
      |<from>|<query>|
    And user clicks on Submit
    Then it should display confirmation message or no error

    Examples:
      | medium | from               | query                            |
      | email  | user@example.com   | I want to know about book return |

  Scenario Outline: To validate blank name error in chat mode
    And user selects "chat" as contact option
    And user fills name as "" and phone as "<phone>" and query as "<query>"
    And user clicks on Submit
    Then it should display name error message

    Examples:
      | phone       | query                         |
      | 9876543210  | Help me with chat service     |

  Scenario Outline: To validate blank phone error in chat mode
    And user selects "chat" as contact option
    And user fills name as "<name>" and phone as "" and query as "<query>"
    And user clicks on Submit
    Then it should display phone error message

    Examples:
      | name  | query                          |
      | Alice | Need assistance via chat mode  |

  Scenario Outline: To validate invalid name in chat mode
    And user selects "chat" as contact option
    And user fills name as "<name>" and phone as "<phone>" and query as "<query>"
    And user clicks on Submit
    Then it should display invalid name error message

    Examples:
      | name | phone      | query                        |
      | 1234 | 9876543210 | Invalid name test case       |

  Scenario Outline: To validate invalid phone number in chat mode
    And user selects "chat" as contact option
    And user fills name as "<name>" and phone as "<phone>" and query as "<query>"
    And user clicks on Submit
    Then it should display invalid phone error message

    Examples:
      | name  | phone   | query                      |
      | Alice | abc123  | Wrong phone input testing  |

  Scenario Outline: To validate blank query error
    And user selects "<medium>" as contact option
    And user fills name as "<name>" and phone as "<phone>" and leaves query blank
    And user clicks on Submit
    Then it should display query error message

    Examples:
      | medium | name  | phone      |
      | chat   | Alice | 9876543210 |

@Membership
Feature: Membership Registration and Verification
  As a user, I want to register for a membership and verify
  that my details are correctly added to the members list.

  Scenario: End-to-end membership registration and verification
    # 1. First, check the empty members page
    Given the user navigates to the members page
    Then an alert with text "No new Members Added" should be displayed

    # 2. Now, perform the registration
    When the user navigates to the membership registration page
    And the user drags the book to the drop zone
    And the user selects the "Gold" membership type
    And the user enters the library card number "LIB-CARD-98765"
    And the user clicks the submit button
    Then the user should see a membership confirmation message

    # 3. Finally, verify the member was added
    When the user navigates to the members page again
    Then the new member with card number "LIB-CARD-98765" should be displayed
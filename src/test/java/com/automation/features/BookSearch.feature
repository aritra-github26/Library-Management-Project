@BookSearch
Feature: Advanced Book Search

  Background:
    Given User launches Firefox browser for Book Search
    And Navigates to the Advanced Book Search Page

  Scenario: Valid search with all correct fields
    And User fills book search details
      | Philip Roth | fiction | Edition 3 | E_Books | Adult |
    And Clicks the Search button
    Then Matching book results are displayed
      | Philip Roth | 3 | Fiction | E_Books | adult |

    Scenario: Misspelled Author Name
      And User fills book search details
        | Philp Roth | fiction | Edition 3 | E_Books | Adult |
      And Clicks the Search button
      Then No result is shown

    Scenario: Misspelled Subject
      And User fills book search details
        | Philip Roth | fition | Edition 3 | E_Books | Adult |
      And Clicks the Search button
      Then No result is shown

  Scenario: Missing Edition
    And User fills book search details
      | Philip Roth | fiction |  | E_Books | Adult |
    And Clicks the Search button
    Then Please Select Edition is displayed

  Scenario: Missing Format
    And User fills book search details
      | Philip Roth | fiction | Edition 3 |  | Adult |
    And Clicks the Search button
    Then Please Select Format is displayed

  Scenario: Missing Age Group
    And User fills book search details
      | Philip Roth | fiction | Edition 3 | E_Books |  |
    And Clicks the Search button
    Then Please Select AgeGroup is displayed


  Scenario: All Books sections page
    And Navigate to All Books Section
    Then Books are displayed
    Then Close The Browser
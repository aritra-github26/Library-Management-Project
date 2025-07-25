@LibraryCard
Feature: Get a Library Card
  # Enter feature description here
  Background:
    Given Start Firefox Browser for Library Card Page Testing
    And Navigate to Get a Library Card Page

  Scenario: Library Card Registration Working
    And User enters all valid credentials
      | Abc | Xyz | 22 | example12@gmail.com | 9876543210 | Student | ABC High School | Apply New Card |
    And Click on Submit Button
    Then Library Card is Displayed
    Then Close the Browser

  Scenario: Library card displayed after registration
    And User enters all valid credentials
      | Abc | Xyz | 22 | example12@gmail.com | 9876543210 | Student | ABC High School | Apply New Card |
    And Click on Submit Button
    Then Checked all details in generated ID card
      | Abc Xyz | 22 | example12@gmail.com | 9876543210 | student |
    Then Close the Browser
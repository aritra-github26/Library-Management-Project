@LibraryCard
Feature: Get a Library Card
  # Enter feature description here
  Background:
    Given Start Firefox Browser for Library Card Page Testing
    And Navigate to Get a Library Card Page

  Scenario: Library Card Registration Working
    And User enters the following credentials
      | Abc | Xyz | 22 | example12@gmail.com | 9876543210 | Student | ABC High School | Apply New Card |
    And Click on Submit Button
    Then Library Card is Displayed

  Scenario: Library card displayed after registration
    And User enters the following credentials
      | Abc | Xyz | 22 | example12@gmail.com | 9876543210 | Student | ABC High School | Apply New Card |
    And Click on Submit Button
    Then Checked all details in generated ID card
      | Abc Xyz | 22 | example12@gmail.com | 9876543210 | student |

  Scenario: Library card first name validation
    And User enters the following credentials
      | Ab';c | Xyz | 22 | example12@gmail.com | 9876543210 | Student | ABC High School | Apply New Card |
    And Click on Submit Button
    Then First name error message is displayed
    Then Library Card is not generated

  Scenario: Library card last name validation
    And User enters the following credentials
      | Abc | Xy#z58 | 22 | example12@gmail.com | 9876543210 | Student | ABC High School | Apply New Card |
    And Click on Submit Button
    Then Last name error message is displayed
    Then Library Card is not generated

  Scenario: Library card age validation
    And User enters the following credentials
      | Abc | Xyz | -20 | example12@gmail.com | 9876543210 | Student | ABC High School | Apply New Card |
    And Click on Submit Button
    Then Age error message is displayed
    Then Library Card is not generated

  Scenario: Library card email validation
    And User enters the following credentials
      | Abc | Xyz | 22 | example12gmail.com | 9876543210 | Student | ABC High School | Apply New Card |
    And Click on Submit Button
    Then Email error message is displayed
    Then Library Card is not generated

  Scenario: Library card phone validation
    And User enters the following credentials
      | Abc | Xyz | 22 | example12gmail.com | 123456 | Student | ABC High School | Apply New Card |
    And Click on Submit Button
    Then Phone error message is displayed
    Then Library Card is not generated

  Scenario: Library card work validation
    And User enters the following credentials
      | Abc | Xyz | 22 | example12gmail.com | 123456 |  |  | Apply New Card |
    And Click on Submit Button
    Then Work error message is displayed
    Then Library Card is not generated

  Scenario: Library card card action validation
    And User enters the following credentials
      | Abc | Xyz | 22 | example12gmail.com | 123456 | Student | ABC High School |  |
    And Click on Submit Button
    Then Card action error message is displayed
    Then Library Card is not generated
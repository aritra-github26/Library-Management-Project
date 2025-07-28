package com.automation.definitions;
import com.automation.pagebean.MembershipPageActions;
import com.automation.setup.DriverSetup;
//import com.automation.utils.DriverFactory; // Assuming you have a DriverFactory class
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class MembershipTest {

    private WebDriver driver;
    private MembershipPageActions membershipPageActions;

    // Constructor to initialize driver and page actions
    public MembershipTest() {
        this.driver = DriverSetup.getDriver(); // Gets the current driver instance
        this.membershipPageActions = new MembershipPageActions(driver);
    }

    @Given("the user navigates to the members page")
    public void the_user_navigates_to_the_members_page() {
        driver.get("http://webapps.tekstac.com/SeleniumApp2/Library/Members.html");
        System.out.println("Step: Navigated to Members page.");
    }

    @Then("an alert with text {string} should be displayed")
    public void an_alert_with_text_should_be_displayed(String alertText) {
        membershipPageActions.verifyAndAcceptAlert(alertText);
        System.out.println("Step: Verified and accepted alert with text: " + alertText);
    }
    
    @When("the user navigates to the membership registration page")
    public void user_is_on_membership_page() {
        // The URL for the membership page
        driver.get("http://webapps.tekstac.com/SeleniumApp2/Library/Membership.html");
        System.out.println("Step: Navigated to Membership page.");
    }

    @When("the user drags the book to the drop zone")
    public void user_drags_book_to_drop_zone() {
        membershipPageActions.dragAndDropBook();
        System.out.println("Step: Drag and drop action performed.");
    }

    @And("the user selects the {string} membership type")
    public void user_selects_membership_type(String membershipType) {
        membershipPageActions.selectMembershipType(membershipType);
        System.out.println("Step: Selected membership type: " + membershipType);
    }

    @And("the user enters the library card number {string}")
    public void user_enters_library_card_number(String cardNumber) {
        membershipPageActions.enterLibraryCardNumber(cardNumber);
        System.out.println("Step: Entered library card number: " + cardNumber);
    }

    @And("the user clicks the submit button")
    public void user_clicks_submit_button() {
        membershipPageActions.clickSubmit();
        System.out.println("Step: Clicked submit button.");
    }

    @Then("the user should see a membership confirmation message")
    public void user_should_see_confirmation() {
        Assert.assertTrue(membershipPageActions.isConfirmationMessageDisplayed(), "Confirmation message was not displayed.");
        System.out.println("Step: Verified confirmation message is visible.");
    }

    @When("the user navigates to the members page again")
    public void the_user_navigates_to_the_members_page_again() {
        driver.get("http://webapps.tekstac.com/SeleniumApp2/Library/Members.html");
        System.out.println("Step: Navigated to Members page again to verify addition.");
    }

    @Then("the new member with card number {string} should be displayed")
    public void the_new_member_with_card_number_should_be_displayed(String cardNumber) {
        boolean isDisplayed = membershipPageActions.isMemberDisplayed(cardNumber);
        Assert.assertTrue(isDisplayed, "Member with card number " + cardNumber + " was NOT found on the members page.");
        System.out.println("Step: Verified member with card number '" + cardNumber + "' is displayed.");
    }
}
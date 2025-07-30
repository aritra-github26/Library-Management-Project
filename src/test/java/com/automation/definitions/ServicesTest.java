package com.automation.definitions;

import java.util.List;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.en.*;
import io.cucumber.datatable.DataTable;

import com.automation.pagebean.SevicesPageActions;
import com.automation.setup.DriverSetup;

import org.testng.Assert;

public class ServicesTest {

    public static WebDriver driver;
    public static SevicesPageActions servicepf;

    @Given("Start browser for Services test")
    public void start_browser_for_services_test() {
        driver = DriverSetup.getDriver();
        servicepf = new SevicesPageActions(driver);
    }

    @And("navigate to Ask a Librarian form on the services page")
    public void navigate_to_services_page() {
        driver.get("https://webapps.tekstac.com/SeleniumApp2/Library/Services.html");
    }

    @And("user selects {string} as contact option")
    public void user_selects_as_contact_option(String medium) {
        servicepf.selectContactMedium(medium);
    }

    @And("user fills form with valid email details")
    public void user_fills_form_with_valid_email_details(DataTable datatable) {
        List<List<String>> data = datatable.asLists(String.class);
        String from = data.get(0).get(0);
        String query = data.get(0).get(1);
        servicepf.fillEmailForm(from, query);
    }

    @And("user fills name as {string} and phone as {string} and query as {string}")
    public void user_fills_chat_form(String name, String phone, String query) {
        servicepf.fillChatForm(name, phone, query);
    }

    @And("user clicks on Submit")
    public void user_clicks_on_submit() {
        servicepf.clickSubmit();
    }

    @Then("it should display confirmation message or no error")
    public void display_success_message() {
        Assert.assertTrue(servicepf.isSuccessMessageDisplayed());
    }

    @Then("it should display name error message")
    public void display_name_error() {
        Assert.assertTrue(servicepf.getnameErrorMessage().contains("Please Enter Your Name"));
    }

    @Then("it should display phone error message")
    public void display_phone_error() {
        Assert.assertTrue(servicepf.getphoneErrorMessage().contains("Please Enter Your Phone number"));
    }

    @Then("it should display invalid name error message")
    public void display_invalid_name_error() {
        Assert.assertTrue(servicepf.getnameErrorMessage().contains("Invalid name"));
    }

    @Then("it should display invalid phone error message")
    public void display_invalid_phone_error() {
        Assert.assertTrue(servicepf.getphoneErrorMessage().contains("Invalid phone"));
    }

    @And("user fills name as {string} and phone as {string} and leaves query blank")
    public void user_fills_query_blank(String name, String phone) {
        servicepf.fillChatForm(name, phone, "");
    }

    @Then("it should display query error message")
    public void display_query_error() {
        Assert.assertTrue(servicepf.getqueryErrorMessage().contains("Query"));
    }
}

package com.automation.definitions;

import com.automation.pagebean.LibraryCardPageActions;
import com.automation.setup.ConfigFileReader;
import com.automation.setup.DriverSetup;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.List;

import static org.testng.AssertJUnit.*;

public class LibraryCardTest {
    public static WebDriver driver;
    public LibraryCardPageActions libraryCardPageActions;

    @Given("Start Firefox Browser for Library Card Page Testing")
    public void startFirefoxBrowserForLibraryCardPageTesting() {
        driver = DriverSetup.getDriver();
        libraryCardPageActions = new LibraryCardPageActions(driver);
    }

    @And("Navigate to Get a Library Card Page")
    public void navigateToGetALibraryCardPage() {
        ConfigFileReader configFileReader = new ConfigFileReader();
        driver.get(configFileReader.getApplicationUrl());
        driver.manage().window().maximize();
        libraryCardPageActions.clickOnLibraryCardLink();
    }

    @And("User enters the following credentials")
    public void userEntersAllValidCredentials(DataTable dataTable) {
        List<List<String>> data = dataTable.asLists(String.class);
        String firstName = data.get(0).get(0);
        String lastName = data.get(0).get(1);
        String age = data.get(0).get(2);
        String email = data.get(0).get(3);
        String phone = data.get(0).get(4);
        String work = data.get(0).get(5);
        String place = data.get(0).get(6);
        String action = data.get(0).get(7);

        if(firstName != null) {
            libraryCardPageActions.setFirstName(firstName);
        }
        if(lastName != null) {
            libraryCardPageActions.setLastName(lastName);
        }
        if(age != null) {
            libraryCardPageActions.setAge(age);
        }
        if(email != null) {
            libraryCardPageActions.setEmail(email);
        }
        if(phone != null) {
            libraryCardPageActions.setPhone(phone);
        }
        if(work != null) {
            libraryCardPageActions.setWork(work, place);
        }
        if(action != null) {
            libraryCardPageActions.setCardAction(action);
        }

    }

    @And("Click on Submit Button")
    public void clickOnSubmitButton() {
        libraryCardPageActions.clickOnSubmitBtn();
    }

    @Then("Library Card is Displayed")
    public void libraryCardIsDisplayed() {
        assertTrue(libraryCardPageActions.isIDGenerated());
    }

    @Then("Close the Browser")
    public void closeBrowser() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.close();
    }

    @Then("Checked all details in generated ID card")
    public void checkAllDetailsInGeneratedIDCard(DataTable dataTable) {
        List<List<String>> data = dataTable.asLists(String.class);
        String[] details = libraryCardPageActions.getCardData();
        for(String detail : details) {
            System.out.println(detail);
        }
        assertEquals(data.get(0).get(0).toString(), details[0]);
        assertEquals(data.get(0).get(1).toString(), details[1]);
        assertEquals(data.get(0).get(2).toString(), details[2]);
        assertEquals(data.get(0).get(3).toString(), details[3]);
        assertEquals(data.get(0).get(4).toString(), details[4]);
    }

    @Then("First name error message is displayed")
    public void firstNameErrorMessageIsDisplayed() {
        assertTrue(libraryCardPageActions.isFirstNameErrorVisible());
    }

    @Then("Library Card is not generated")
    public void libraryCardIsNotGenerated() {
        assertFalse(libraryCardPageActions.isIDGenerated());
    }

    @Then("Last name error message is displayed")
    public void lastNameErrorMessageIsDisplayed() {
        assertTrue(libraryCardPageActions.isLastNameErrorVisible());
    }

    @Then("Age error message is displayed")
    public void ageErrorMessageIsDisplayed() {
        assertTrue(libraryCardPageActions.isAgeErrorVisible());
    }

    @Then("Email error message is displayed")
    public void emailErrorMessageIsDisplayed() {
        assertTrue(libraryCardPageActions.isEmailErrorVisible());
    }

    @Then("Phone error message is displayed")
    public void phoneErrorMessageIsDisplayed() {
        assertTrue(libraryCardPageActions.isPhoneErrorVisible());
    }

    @Then("Work error message is displayed")
    public void workErrorMessageIsDisplayed() {
        assertTrue(libraryCardPageActions.isWorkErrorVisible());
    }

    @Then("Card action error message is displayed")
    public void cardActionErrorMessageIsDisplayed() {
        assertTrue(libraryCardPageActions.isCardErrorVisible());
    }
}

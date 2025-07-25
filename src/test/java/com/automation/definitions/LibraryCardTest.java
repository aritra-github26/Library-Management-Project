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

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

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

    @And("User enters all valid credentials")
    public void userEntersAllValidCredentials(DataTable dataTable) {
        List<List<String>> data = dataTable.asLists(String.class);
        libraryCardPageActions.setFirstName(data.get(0).get(0).toString());
        libraryCardPageActions.setLastName(data.get(0).get(1).toString());
        libraryCardPageActions.setAge(data.get(0).get(2).toString());
        libraryCardPageActions.setEmail(data.get(0).get(3).toString());
        libraryCardPageActions.setPhone(data.get(0).get(4).toString());
        libraryCardPageActions.setWork(data.get(0).get(5).toString(), data.get(0).get(6).toString());
        libraryCardPageActions.setCardAction(data.get(0).get(7).toString());
    }

    @And("Click on Submit Button")
    public void clickOnSubmitButton() {
        libraryCardPageActions.clickOnSubmitBtn();
    }

    @Then("Library Card is Displayed")
    public void libraryCardIsDisplayed() {
        assertTrue(libraryCardPageActions.isIDGenarated());
    }

    @Then("Close the Browser")
    public void closeBrowser() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
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


}

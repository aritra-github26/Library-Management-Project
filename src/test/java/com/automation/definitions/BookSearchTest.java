package com.automation.definitions;

import com.automation.pagebean.BookSearchPageActions;
import com.automation.setup.ConfigFileReader;
import com.automation.setup.DriverSetup;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.testng.Assert;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;


import java.time.Duration;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;
//import java.util.concurrent.TimeUnit;

public class BookSearchTest {
    public static WebDriver driver;
    public BookSearchPageActions bookSearchPageActions;

    @Given("User launches Firefox browser for Book Search")
    public void startFirefoxBrowserForSearchPageTesting(){
        driver = DriverSetup.getDriver();
        bookSearchPageActions = new BookSearchPageActions(driver);
    }

    @And("Navigates to the Advanced Book Search Page")
    public void navigateToAdvancedBookSearchPage(){
        ConfigFileReader configFileReader = new ConfigFileReader();
        driver.get(configFileReader.getApplicationUrl());
        driver.manage().window().maximize();
        bookSearchPageActions.clickOnSearchLink();
    }

    @And("User fills book search details")
    public void userFillsBookSearchDetails(DataTable dataTable) {
        List<List<String>> data = dataTable.asLists(String.class);
        String author = data.get(0).get(0);
        String subject = data.get(0).get(1);
        String edition = data.get(0).get(2);
        String format = data.get(0).get(3);
        String age = data.get(0).get(4);

        if (author != null) {
            bookSearchPageActions.setAuthor(author);
        }
        if (subject != null) {
            bookSearchPageActions.setSubject(subject);
        }
        if (edition != null) {
            bookSearchPageActions.setEdition(edition);
        }
        if (format != null) {
            bookSearchPageActions.setFormat(format);
        }
        if (age != null) {
            bookSearchPageActions.setAge(age);
        }
    }

    @And("Clicks the Search button")
    public void clicksTheSearchButton(){
        bookSearchPageActions.clickSearch();
    }

    @Then("Matching book results are displayed")
    public void matchingBookResultsAreDisplayed(DataTable dataTable) {
        List<List<String>> data = dataTable.asLists(String.class);
        String[] details = bookSearchPageActions.getBookData();
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
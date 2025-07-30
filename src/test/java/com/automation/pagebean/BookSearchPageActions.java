package com.automation.pagebean;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BookSearchPageActions {

    public WebDriver driver;

    public BookSearchPageActions(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(id = "searchlink")
    public WebElement searchLink;

    @FindBy(id = "authorName")
    public WebElement authorInput;

    @FindBy(id = "subject")
    public WebElement subjectInput;

    @FindBy(id = "edition")
    public WebElement editionDropdown;

    @FindBy(id = "format")
    public WebElement formatDropdown;

    @FindBy(xpath = "//input[@value='kids']")
    public WebElement ageChildrenRadio;

    @FindBy(xpath = "//input[@value='teen']")
    public WebElement ageTeenRadio;

    @FindBy(xpath = "//input[@value='adult']")
    public WebElement ageAdultRadio;

    @FindBy(id = "searchSubmit")
    public WebElement searchButton;

    //Result

    @FindBy(className="bookscontainer")
    public WebElement generatedBook;


    @FindBy(xpath = "//td[text()='Author']/following-sibling::td")
    public WebElement authorResult;

    @FindBy(xpath = "//td[text()='Edition']/following-sibling::td")
    public WebElement editionResult;

    @FindBy(xpath = "//td[text()='Subject']/following-sibling::td")
    public WebElement subjectResult;

    @FindBy(xpath = "//td[text()='Format']/following-sibling::td")
    public WebElement formatResult;

    @FindBy(xpath = "//td[text()='Age']/following-sibling::td")
    public WebElement AgeResult;


    //Error messages
    @FindBy(id = "ageGroupError")
    public WebElement ageGroupError;

    @FindBy(id = "editionError")
    public WebElement editionError;

    @FindBy(id = "formatError")
    public WebElement formatError;

    //All books section
    @FindBy(id = "bookslink")
    public WebElement bookLink;

    @FindBy(id = "books")
    public WebElement booksDiv;

    public void clickOnSearchLink() {
        searchLink.click();
    }

    public void setAuthor(String author) {
        authorInput.sendKeys(author);
    }

    public void setSubject(String subject) {
        subjectInput.sendKeys(subject);
    }



    public void setEdition(String edition) {
        Select select = new Select(editionDropdown);
        select.selectByVisibleText(edition);
    }

    public void setFormat(String format) {
        Select sel = new Select(formatDropdown);
        sel.selectByVisibleText(format);
    }

    public void setAge(String ageGroup) {
        if (ageGroup.equalsIgnoreCase("children")) {
            ageChildrenRadio.click();
        } else if (ageGroup.equalsIgnoreCase("teen")) {
            ageTeenRadio.click();
        } else if (ageGroup.equalsIgnoreCase("adult")) {
            ageAdultRadio.click();
        }
    }

    public void clickSearch() {
        searchButton.click();
    }

    //Result

    public boolean isBookGenerated() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        return generatedBook.isDisplayed();
    }

    public String[] getBookData() {
        String[] bookData = new String[5];
        bookData[0] = authorResult.getText();
        bookData[1] = editionResult.getText();
        bookData[2] = subjectResult.getText();
        bookData[3] = formatResult.getText();
        bookData[4] = AgeResult.getText();

        return bookData;
    }


    //Check error messages
    public boolean isPleaseSelectEdition() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        return editionError.isDisplayed();
    }

    public boolean isPleaseSelectFormat() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        return formatError.isDisplayed();
    }

    public boolean isPleaseSelectAgeGroup() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        return ageGroupError.isDisplayed();
    }

    //no result is shown
    public boolean isResultVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        try {
            wait.until(ExpectedConditions.visibilityOf(generatedBook));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    //All books link
    public void clickOnBookLink() {
        bookLink.click();
    }

    public boolean isBooksSectionVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(booksDiv));
        return booksDiv.isDisplayed();
    }




}
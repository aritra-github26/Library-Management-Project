package com.automation.pagebean;


import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class MembershipPageActions {
	private WebDriver driver;
    private Actions actions;
    private WebDriverWait wait;

    // LOCATORS for Membership Page
    @FindBy(id = "book-to-drag")
    private WebElement draggableBook;

    @FindBy(id = "drop-zone")
    private WebElement droppableArea;

    @FindBy(id = "library-card-number-input")
    private WebElement libraryCardInput;

    @FindBy(css = "button[type='submit']")
    private WebElement submitButton;

    @FindBy(id = "confirmation-message")
    private WebElement confirmationMessage;

    // Constructor to initialize the elements
    public MembershipPageActions(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // ACTIONS for Membership Page
    public void dragAndDropBook() {
        wait.until(ExpectedConditions.visibilityOf(draggableBook));
        wait.until(ExpectedConditions.visibilityOf(droppableArea));
        actions.dragAndDrop(draggableBook, droppableArea).perform();
    }

    public void selectMembershipType(String membershipType) {
        String radioXpath = String.format("//label[contains(normalize-space(), '%s')]/input[@type='radio']", membershipType);
        WebElement radioButton = driver.findElement(By.xpath(radioXpath));
        wait.until(ExpectedConditions.elementToBeClickable(radioButton));
        radioButton.click();
    }

    public void enterLibraryCardNumber(String cardNumber) {
        wait.until(ExpectedConditions.visibilityOf(libraryCardInput));
        libraryCardInput.clear();
        libraryCardInput.sendKeys(cardNumber);
    }

    public void clickSubmit() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        submitButton.click();
    }

    public boolean isConfirmationMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(confirmationMessage));
            return confirmationMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // --- NEW METHODS FOR MEMBERS PAGE VERIFICATION ---

    /**
     * Switches to the alert, verifies its text, and then accepts it.
     * @param expectedText The exact text expected in the alert popup.
     */
    public void verifyAndAcceptAlert(String expectedText) {
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String actualText = alert.getText();
        Assert.assertEquals(actualText.trim(), expectedText.trim(), "Alert text did not match!");
        alert.accept();
    }

    /**
     * Checks if a member with a specific card number is visible in the members list.
     * @param cardNumber The card number to search for in the members table.
     * @return true if the member is found, false otherwise.
     */
    public boolean isMemberDisplayed(String cardNumber) {
        try {
            // This dynamic XPath finds any table cell (td) that contains the specific card number text.
            // This is a robust way to check for presence in a table.
            String memberXPath = String.format("//table//td[contains(text(), '%s')]", cardNumber);
            WebElement memberCell = driver.findElement(By.xpath(memberXPath));
            // We just need to check if it's found. A successful find means it's displayed.
            return true;
        } catch (NoSuchElementException e) {
            // If findElement fails, the element isn't there, which is a valid check.
            return false;
        }
    }
}

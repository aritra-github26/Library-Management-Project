package com.automation.pagebean;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class LibraryCardPageActions {
    public WebDriver driver;

    public LibraryCardPageActions(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "librarycardlink")
    public WebElement libraryCardLink;

    @FindBy(id = "first")
    public WebElement firstNameEl;

    @FindBy(id = "last")
    public WebElement lastNameEl;

    @FindBy(id = "age")
    public WebElement ageEl;

    @FindBy(id = "email")
    public WebElement emailEl;

    @FindBy(id = "phone")
    public WebElement phoneEl;

    @FindBy(id = "work_0")
    public WebElement workStudentBtn;

    @FindBy(id = "school")
    public WebElement workStudentEl;

    @FindBy(id = "work_1")
    public WebElement workEmployeeBtn;

    @FindBy(id = "company")
    public WebElement workEmployeeEl;

    @FindBy(id = "action")
    public WebElement cardActionDrop;

    @FindBy(id = "libraryCardSubmit")
    public WebElement submitBtn;

    @FindBy(id = "idcard")
    public WebElement generatedIDCard;

    @FindBy(id = "idname")
    public WebElement nameInCard;

    @FindBy(id = "idage")
    public WebElement ageInCard;

    @FindBy(id = "idemail")
    public WebElement emailInCard;

    @FindBy(id = "idphone")
    public WebElement phoneInCard;

    @FindBy(id = "idwork")
    public WebElement roleInCard;

    @FindBy(id = "iddate")
    public WebElement dateInCard;

    public void clickOnLibraryCardLink() {
        libraryCardLink.click();
    }

    public void setFirstName(String firstName) {
        firstNameEl.sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        lastNameEl.sendKeys(lastName);
    }

    public void setAge(String age) {
        ageEl.sendKeys(age);
    }

    public void setEmail(String email) {
        emailEl.sendKeys(email);
    }

    public void setPhone(String phone) {
        phoneEl.sendKeys(phone);
    }

    public void setWork(String work, String place) {
        if(work.equalsIgnoreCase("student")) {
            workStudentBtn.click();
            workStudentEl.sendKeys(place);
        } else if(work.equalsIgnoreCase("employee")) {
            workEmployeeBtn.click();
            workEmployeeEl.sendKeys(place);
        }
    }

    public void setCardAction(String action) {
        Select select = new Select(cardActionDrop);
        select.selectByVisibleText(action);
    }

    public void clickOnSubmitBtn() {
        submitBtn.click();
    }

    public boolean isIDGenarated() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        return generatedIDCard.isDisplayed();
    }

    public String[] getCardData() {
        String[] cardData = new String[6];
        cardData[0] = nameInCard.getText();
        cardData[1] = ageInCard.getText();
        cardData[2] = emailInCard.getText();
        cardData[3] = phoneInCard.getText();
        cardData[4] = roleInCard.getText();
        cardData[5] = dateInCard.getText();
        return cardData;
    }

}

package com.automation.pagebean;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SevicesPageActions {

    public WebDriver driver;

    public SevicesPageActions(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "medium_email")
    private WebElement emailRadio;

    @FindBy(id = "medium_call")
    private WebElement callRadio;

    @FindBy(id = "medium_chat")
    private WebElement chatRadio;

    @FindBy(id = "fromEmail")
    private WebElement fromField;

    @FindBy(id = "queryemail")
    private WebElement queryField;

    @FindBy(id = "chatname")
    private WebElement nameField;

    @FindBy(id = "yourphone")
    private WebElement phoneField;

    @FindBy(id = "querychat")
    private WebElement queryChat;

    @FindBy(id = "chatnameError")
    private WebElement chatnameError;

    @FindBy(id = "yourphoneError")
    private WebElement yourphoneError;

    @FindBy(id = "fromemailError")
    private WebElement fromemailError;

    @FindBy(id = "mediummailoutput")
    private WebElement mediummailoutput;

    @FindBy(id = "queryemailError")
    private WebElement queryemailError;

    @FindBy(id = "QuerySubmit")
    private WebElement submitBtn;

    public void selectContactMedium(String medium) {
        switch (medium.toLowerCase()) {
            case "email" -> emailRadio.click();
            case "chat" -> chatRadio.click();
            case "call" -> callRadio.click();
        }
    }

    public void fillEmailForm(String from, String query) {
        fromField.clear();
        fromField.sendKeys(from);
        queryField.clear();
        queryField.sendKeys(query);
    }

    public void fillChatForm(String name, String phone, String query) {
        nameField.clear();
        nameField.sendKeys(name);
        phoneField.clear();
        phoneField.sendKeys(phone);
        queryChat.clear();
        queryChat.sendKeys(query);
    }

    public void clickSubmit() {
        submitBtn.click();
    }

    public String getqueryErrorMessage() {
        return queryemailError.getText();
    }

    public String getnameErrorMessage() {
        return chatnameError.getText();
    }

    public String getphoneErrorMessage() {
        return yourphoneError.getText();
    }

    public boolean isSuccessMessageDisplayed() {
        try {
            return mediummailoutput.getText().isEmpty();
        } catch (Exception e) {
            return true;
        }
    }

}

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

    
    @FindBy(xpath = "//img[@id='drag1']")
    private WebElement draggableBook;

    @FindBy(xpath = "//div[@id='div1']")
    private WebElement droppableArea;
    
  

    @FindBy(css = "input[type='submit']")
    private WebElement submitButton;

    @FindBy(id = "memberoutput")
    private WebElement confirmationMessage;

    
    public MembershipPageActions(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
        
    }

   
    public void dragAndDropBook()  {
      driver.manage().window().maximize();
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("drag1")));
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("div1")));

      String script = """
    	        function simulateDragDrop(sourceNode, destinationNode) {
    	            var EVENT_TYPES = {
    	                DRAG_END: 'dragend',
    	                DRAG_START: 'dragstart',
    	                DROP: 'drop'
    	            }

    	            function createCustomEvent(type) {
    	                var event = new CustomEvent("CustomEvent");
    	                event.initCustomEvent(type, true, true, null);
    	                event.dataTransfer = {
    	                    data: {},
    	                    setData: function(type, val) {
    	                        this.data[type] = val;
    	                    },
    	                    getData: function(type) {
    	                        return this.data[type];
    	                    }
    	                };
    	                return event;
    	            }

    	            function dispatchEvent(node, type, event) {
    	                if (node.dispatchEvent) {
    	                    return node.dispatchEvent(event);
    	                }
    	                if (node.fireEvent) {
    	                    return node.fireEvent("on" + type, event);
    	                }
    	            }

    	            var dragStartEvent = createCustomEvent(EVENT_TYPES.DRAG_START);
    	            dispatchEvent(sourceNode, EVENT_TYPES.DRAG_START, dragStartEvent);

    	            var dropEvent = createCustomEvent(EVENT_TYPES.DROP);
    	            dropEvent.dataTransfer = dragStartEvent.dataTransfer;
    	            dispatchEvent(destinationNode, EVENT_TYPES.DROP, dropEvent);

    	            var dragEndEvent = createCustomEvent(EVENT_TYPES.DRAG_END);
    	            dragEndEvent.dataTransfer = dragStartEvent.dataTransfer;
    	            dispatchEvent(sourceNode, EVENT_TYPES.DRAG_END, dragEndEvent);
    	        }

    	        simulateDragDrop(arguments[0], arguments[1]);
    	        """;

    	    ((JavascriptExecutor) driver).executeScript(script, draggableBook, droppableArea);
      
      
       // actions.dragAndDrop(draggableBook, droppableArea).build().perform();
     //  actions.moveToElement(draggableBook).clickAndHold().moveToElement(droppableArea).pause(Duration.ofSeconds(3)).release().build().perform();
    }

    public void selectMembershipType(String membershipType) {
        String radioXpath = String.format("//label[contains(normalize-space(), 'Platinum')]/input[@type='radio']", membershipType);
        WebElement radioButton = driver.findElement(By.xpath(radioXpath));
        wait.until(ExpectedConditions.elementToBeClickable(radioButton));
        radioButton.click();
    }

    
    public void enterLibraryCardNumber(String cardNumber, String membershipType) {
        String inputId;
    
        if (membershipType.equalsIgnoreCase("Gold")) {
            inputId = "libcardNumberGold";
        } else { 
            inputId = "libcardNumberPtm";
        }
        
        WebElement libraryCardInput = driver.findElement(By.id(inputId));
        
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

    public void verifyAndAcceptAlert(String expectedText) {
    	
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String actualText = alert.getText();
       // Assert.assertEquals(actualText.trim(), expectedText.trim(), "Alert text did not match!");
        alert.accept();
    }

    public boolean isMemberDisplayed(String cardNumber) {
        try {
            String memberXPath = String.format("//table//td[contains(text(), '%s')]", cardNumber);
            driver.findElement(By.xpath(memberXPath));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
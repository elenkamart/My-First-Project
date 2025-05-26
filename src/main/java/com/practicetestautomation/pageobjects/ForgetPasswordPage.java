package com.practicetestautomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ForgetPasswordPage extends BasePage {
    private By cookieBtnLocator = By.id("cookiescript_reject");
    private By emailInputLocator = By.id("email");
    private By submitButtonLocator = By.xpath("//button[@class='ghost-btn']");
    private By infoMessageLocator = By.className("info-box__text");

    public ForgetPasswordPage(WebDriver driver) {
        super(driver);
    }

    public void visit() {
        super.visit("https://info-car.pl/new/logowanie/zapomnialem-hasla");
    }

    public void rejectCookieBtn() {
        driver.findElement(cookieBtnLocator).click();
    }

    public void enterEmail(String email) {
        driver.findElement(emailInputLocator).sendKeys(email);
    }

    public void submitButton() {
        driver.findElement(submitButtonLocator).click();
    }
    public void load() {
        waitForElement(infoMessageLocator);
    }
    public boolean isInfoMessageLocatorDisplayed() {
        return isDisplayed(infoMessageLocator);
    }
    public String getInfoMessage() {
        WebElement infoMessage = waitForElement(infoMessageLocator);
        return infoMessage.getText();
    }
}

package com.practicetestautomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;

public class LoginPage extends BasePage{

    private By emailInputLocator = By.id("username");
    private By passwordInputLocator = By.id("password");
    private By submitButtonLocator = By.id("register-button");
    private By errorMessageLocator = RelativeLocator.with(By.className("error-description-container")).below(By.id("password"));

    public LoginPage(WebDriver driver) {
       super(driver);
    }
    public void visit() {
        super.visit("https://info-car.pl/oauth2/login");
    }
    public void enterEmail(String email) {
        driver.findElement(emailInputLocator).sendKeys(email);
    }
    public void enterPassword(String password) {
        driver.findElement(passwordInputLocator).sendKeys(password);
    }
    public void submitButton() {
        driver.findElement(submitButtonLocator).click();
    }
    public SuccessfulLoginPage executeLogin(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        submitButton();
        return new SuccessfulLoginPage(driver);
    }
   public String getErrorMessage() {
       WebElement errorMessage = waitForElement(errorMessageLocator);
       return errorMessage.getText();
    }
}


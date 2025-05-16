package com.practicetestautomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By emailInputLocator = By.id("username");
    private By passwordInputLocator = By.id("password");
    private By submitButtonLocator = By.id("register-button");
    private By errorMessageLocator = By.className("error-description-container");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
        WebElement errorMessageBelowPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageLocator));
        return errorMessageBelowPassword.getText();
    }
}


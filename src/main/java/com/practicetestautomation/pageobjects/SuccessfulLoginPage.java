package com.practicetestautomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SuccessfulLoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By nameSurnameLocator = By.xpath("//a[@class='ng-tns-c4167178825-2']");
    private By logOutButtonLocator = By.xpath("//button[@class='ng-tns-c4167178825-2']");

    public SuccessfulLoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
    public String getPageSource() {
        return driver.getPageSource();
    }
    public boolean isNameSurnameDisplayed() {
        try {
            return driver.findElement(nameSurnameLocator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public boolean isLogoutButtonDisplayed() {
        try {
            return driver.findElement(logOutButtonLocator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}

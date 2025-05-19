package com.practicetestautomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SuccessfulLoginPage extends BasePage {
    private By logOutButtonLocator = By.xpath("//button[@class='ng-tns-c4167178825-2']");

    public SuccessfulLoginPage(WebDriver driver) {
       super(driver);

    }
    public boolean isLogoutButtonDisplayed() {
            return isDisplayed(logOutButtonLocator);
    }
    public void load() {
        waitForElement(logOutButtonLocator);
    }
}

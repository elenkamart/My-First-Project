package com.practicetestautomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SuccessfulLoginPage extends BasePage {

    private By nameSurnameLocator = By.xpath("//a[@class='ng-tns-c4167178825-2']");
    private By logOutButtonLocator = By.xpath("//button[@class='ng-tns-c4167178825-2']");

    public SuccessfulLoginPage(WebDriver driver) {
       super(driver);
    }

    public boolean isNameSurnameDisplayed() {
            return isDisplayed(nameSurnameLocator);
    }
    public boolean isLogoutButtonDisplayed() {
            return isDisplayed(logOutButtonLocator);
    }
}

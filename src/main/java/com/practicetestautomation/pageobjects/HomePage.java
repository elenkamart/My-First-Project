package com.practicetestautomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {
    private By cookieBtnLocator = By.id("cookiescript_reject");
    private By dropDownCategoriesBtnLocator = By.id("button-categories");
    private By menuDriverLicenseBtnLocator = By.linkText("Prawo jazdy");
    private By serviceLinkLocator = By.linkText("Sprawdź dostępność terminów egzaminu na prawo jazdy lub na test kwalifikacyjny (kod 95)");
    private By signUpLinkLocator = By.xpath("//a[@link='/prawo-jazdy/zapisz-sie-na-egzamin-na-prawo-jazdy']");
    private By signUpBtnLocator = By.xpath("//ic-ghost-button[@class='register-exam-button']");

    public HomePage(WebDriver driver) {
        super(driver);
    }
    public void visit() {
        super.visit("https://info-car.pl/new/");
    }
    public void rejectCookieBtn() {
        driver.findElement(cookieBtnLocator).click();
    }
    public void dropDownCategoriesBtn() {
        driver.findElement(dropDownCategoriesBtnLocator).click();
    }
    public void menuDriverLicenseBtn() {
        driver.findElement(menuDriverLicenseBtnLocator).click();
    }
    public boolean isServiceLinkDisplayedAfterWait(){
        return waitForIsDisplayed(serviceLinkLocator);
    }
    public void serviceLink() {
        driver.findElement(serviceLinkLocator).click();
    }
    public boolean isSignUpLinkDisplayedAfterWait() {
        return waitForIsDisplayed(signUpLinkLocator);
    }
    public void signUpLink() {
        driver.findElement(signUpLinkLocator).click();
    }
    public WebElement waitForSignUpBtn() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(signUpBtnLocator));
    }
    public void setSignUpBtn() {
        driver.findElement(signUpBtnLocator).click();
    }
}

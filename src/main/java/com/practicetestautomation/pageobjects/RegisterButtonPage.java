package com.practicetestautomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterButtonPage extends BasePage{
    private By registerButtonLocator = By.xpath("//button[@class='register-button']");
    private By cookieBtnLocator = By.id("cookiescript_accept");
    private By individualAccountLocator = By.xpath("//h2[@class='tile__title'] ['Konto indywidualne']");
    private By individualRegisterBtnLocator = By.xpath("//button[@class='ghost-btn']");
    private By rejectCookieBtnLocator = By.id("cookiescript_reject");
    private By companyAccountLocator = By.xpath("//h2[@class='tile__title'] ['Konto dla firm']");
    private By companyRegisterBtnLocator = By.xpath("//a[@href='/new/tachograf/partnerzy/zakladanie-konta']");

    public RegisterButtonPage(WebDriver driver) {
        super(driver);
    }
    public void visit() {
        super.visit("https://info-car.pl/oauth2/login");
    }
    public void pushRegisterButton() {
        driver.findElement(registerButtonLocator).click();
    }
    public void acceptCookieBtn() {
        driver.findElement(cookieBtnLocator).click();
    }
    public boolean isIndividualAccountDisplayedAfterWait() {
        return waitForIsDisplayed(individualAccountLocator);
    }
    public void pushIndividualRegisterBtn() {
        driver.findElement(individualRegisterBtnLocator).click();
    }
    public void rejectCookieBtn() {
        driver.findElement(rejectCookieBtnLocator);
    }
    public boolean isCompanyAccountDisplayedAfterWait() {
        return waitForIsDisplayed(companyAccountLocator);
    }
    public void pushCompanyRegisterBtn() {
        driver.findElement(companyRegisterBtnLocator);
    }
}

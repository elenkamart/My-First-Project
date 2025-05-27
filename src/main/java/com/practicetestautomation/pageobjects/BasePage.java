package com.practicetestautomation.pageobjects;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor jse;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    protected void visit(String url) {
        driver.get(url);
    }
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
    public String getPageSource() {
        return driver.getPageSource();
    }
    protected WebElement waitForElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    protected boolean isDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        }catch (NoSuchElementException e) {
            return false;
        }
    }
    protected boolean waitForIsDisplayed(By locator) {
        try {
            waitForElement(locator);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
    protected @Nullable Object goDownTo(By locator) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
       return jse.executeScript("window.scrollBy(0, 3000)");
    }
    }




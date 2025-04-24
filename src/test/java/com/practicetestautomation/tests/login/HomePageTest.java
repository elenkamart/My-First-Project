package com.practicetestautomation.tests.login;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest {
    @Test(groups = {"positive","regression"})
    public void homePage() {
        // Open Page
        WebDriver driver = new ChromeDriver();
        driver.get("https://info-car.pl/new/");

        // Reject cookies
        WebElement cookieBtn = driver.findElement(By.id("cookiescript_reject"));
        cookieBtn.click();

        // Click DropDownCategories button
        WebElement dropDownCategoriesBtn = driver.findElement(By.id("button-categories"));
        dropDownCategoriesBtn.click();

        // Choose Driver's license category
        WebElement menuDriverLicenseBtn = driver.findElement(By.linkText("Prawo jazdy"));
        menuDriverLicenseBtn.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Verify new page URL
        String expectedUrl = "https://info-car.pl/new/prawo-jazdy";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);

        // Check the availability of driving test
        WebElement serviceLink = driver.findElement(By.linkText("Sprawdź dostępność terminów egzaminu na prawo jazdy lub na test kwalifikacyjny (kod 95)"));
        serviceLink.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Verify new page URL
        String expectedLinkUrl = "https://info-car.pl/new/prawo-jazdy/sprawdz-wolny-termin";
        String actualLinkUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualLinkUrl, expectedLinkUrl);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Click on signUp link
        WebElement signUpLink = driver.findElement(By.xpath("//a[@link='/prawo-jazdy/zapisz-sie-na-egzamin-na-prawo-jazdy']"));
        signUpLink.click();

        // Verify new page URL
        String expectedUrlLink = "https://info-car.pl/new/prawo-jazdy/zapisz-sie-na-egzamin-na-prawo-jazdy";
        String actualUrlLink = driver.getCurrentUrl();
        Assert.assertEquals(actualLinkUrl, expectedLinkUrl);


        // Scroll down and click signInBtn
        JavascriptExecutor jse = (JavascriptExecutor)driver;

        WebElement signUpBtn = driver.findElement(By.xpath("//button[@class='ghost-btn']"));
        jse.executeScript("window.scrollBy(0, 3000)",signUpBtn);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        signUpBtn.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        driver.quit();

    }
}

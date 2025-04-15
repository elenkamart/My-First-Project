package com.practicetestautomation.tests.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest {

    @Test
    public void testLoginFunctionality() {
        // Open Page
        WebDriver driver = new ChromeDriver();
        driver.get("https://info-car.pl/oauth2/login");

        // Type address e-mail student into Username field
        WebElement emailInput = driver.findElement(By.id("username"));
        emailInput.sendKeys("elenapumpkin8@gmail.com");

        // Type password into Password field
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("Pumpkin@123");

        // Push Submit button
        WebElement submitbutton = driver.findElement(By.id("register-button"));
        submitbutton.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // Verify new page URL
        String expectedUrl = "https://info-car.pl/new/";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);

        // Verify new page contains expected text ('Elena Povazhna')
        String expectedngcontent= "Elena Pumpkin";
        String pageSource = driver.getPageSource();
        Assert.assertTrue(pageSource.contains(expectedngcontent));


        // Verify button Log out is displayed on the new page
        WebElement logOutButton = driver.findElement(By.xpath("//button[@class='ng-tns-c4167178825-2']"));
        Assert.assertTrue(logOutButton.isDisplayed());

        driver.quit();
    }
}


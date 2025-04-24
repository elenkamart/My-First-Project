package com.practicetestautomation.tests.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ForgetPasswordTest {
    @Test(groups = {"positive","regression"})
    public void testForgetPassword() {
        // Open Page
        WebDriver driver = new ChromeDriver();
        driver.get("https://info-car.pl/new/logowanie/zapomnialem-hasla");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

       WebElement cookieBtn= driver.findElement(By.id("cookiescript_reject"));
        cookieBtn.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Type address e-mail into Username field
        WebElement emailInput = driver.findElement(By.id("email"));
        emailInput.sendKeys("elenapumpkin8@gmail.com");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Push Submit button
        WebElement submitbutton = driver.findElement(By.xpath("//button[@class='ghost-btn']"));
        submitbutton.click();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Verify information is displayed
        WebElement infoMessageBelowEmail = driver.findElement(By.className("info-box__text"));
        Assert.assertTrue(infoMessageBelowEmail.isDisplayed());

        // Verify text is Na podany adres e-mail zostało wysłany link pozwalający na ustawienie nowego hasła.
        String expectedInfoMessage = "Na podany adres e-mail zostało wysłany link pozwalający na ustawienie nowego hasła.";
        String actualInfoMessage = infoMessageBelowEmail.getText();
        Assert.assertEquals(actualInfoMessage, expectedInfoMessage);

        driver.quit();
    }
}

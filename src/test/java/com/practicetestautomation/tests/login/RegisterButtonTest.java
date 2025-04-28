package com.practicetestautomation.tests.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterButtonTest {
    @Test(groups = {"positive","regression"})
    public void registerButton() {
        // Open Page
        WebDriver driver = new EdgeDriver();
        driver.get("https://info-car.pl/oauth2/login");

        // Push Register button
        WebElement registerbutton = driver.findElement(By.xpath("//button[@class='register-button']"));
        registerbutton.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        WebElement cookieBtn= driver.findElement(By.id("cookiescript_accept"));
        cookieBtn.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Verify new page contains expected text ('Konto indywidualne') i ('Konto dla firm')
        String expectedNgContent= "Konto indywidualne";
        String ngContent= "Konto dla firm";
        String pageSource = driver.getPageSource();
        Assert.assertTrue(pageSource.contains(expectedNgContent), ngContent);

        // Push IndividualRegister button
        WebElement individualRegisterButton = driver.findElement(By.xpath("//button[@class='ghost-btn']"));
        individualRegisterButton.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Verify new page URL
        String expectedUrl = "https://info-car.pl/new/rejestracja/formularz";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);

        driver.quit();
    }
}

package com.practicetestautomation.tests.register;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class IndividualRegisterTest {
    @Test(groups = {"positive","smoke"})
    public void individualRegisterFunctionality() {

        // Open Page
        WebDriver driver = new ChromeDriver();
        driver.get("https://info-car.pl/new/rejestracja/formularz");

        // Reject cookies
        WebElement cookieBtn = driver.findElement(By.id("cookiescript_reject"));
        cookieBtn.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Type Name Elena into name field
        WebElement nameInput = driver.findElement(By.id("firstName"));
        nameInput.sendKeys("Elena");

        // Type Surname into surname field
        WebElement surnameInput = driver.findElement(By.id("surname"));
        surnameInput.sendKeys("Pumpkin");

        // Type address e-mail  into email field
        WebElement emailInput = driver.findElement(By.id("email"));
        emailInput.sendKeys("elenapumpkin8@gmail.com");

        // Type password into Password field
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("Pumpkin@123");

        // Confirm Password into confirmPassword field
        WebElement confirmPasswordInput = driver.findElement(By.id("confirmPassword"));
        confirmPasswordInput.sendKeys("Pumpkin@123");

        // Select All checkboxes
        WebElement checkboxSelect = driver.findElement(By.id("ALL"));
        checkboxSelect.isSelected();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        checkboxSelect.click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Push Register button
        WebElement individualRegisterButton = driver.findElement(By.xpath("//button[@class='ghost-btn']"));
        individualRegisterButton.click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Verify new page URL
        String expectedUrl = "https://info-car.pl/new/rejestracja/rejestracja-prawidlowa";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);

        // Verify registration success message is displayed
        WebElement successMessage = driver.findElement(By.className("registration-success-subheader"));
        Assert.assertTrue(successMessage.isDisplayed());

        // Verify text is Na podany adres e-mail został wysłany link aktywacyjny konta. Kliknij w link i aktywuj konto.
        String expectedSuccessMessage = "Na podany adres e-mail został wysłany link aktywacyjny konta. Kliknij w link i aktywuj konto.";
        String actualMessage = successMessage.getText();
        Assert.assertEquals(actualMessage, expectedSuccessMessage);

        driver.quit();
    }
}

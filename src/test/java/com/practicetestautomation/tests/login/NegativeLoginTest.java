package com.practicetestautomation.tests.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeLoginTest {
   @Test
    public void incorrectUsername() {
       // Open Page
       WebDriver driver = new ChromeDriver();
       driver.get("https://info-car.pl/oauth2/login");

       // Type address incorrect e-mail into Username field
       WebElement emailInput = driver.findElement(By.id("username"));
       emailInput.sendKeys("elenapumpkin@gmail.com");

       // Type password into Password field
       WebElement passwordInput = driver.findElement(By.id("password"));
       passwordInput.sendKeys("Pumpkin@123");

       // Push Submit button
       WebElement submitbutton = driver.findElement(By.id("register-button"));
       submitbutton.click();

       // Verify warning message is displayed
      WebElement errorMessageBelowPassword = driver.findElement(RelativeLocator.with(By.className("error-description-container")).below(By.id("password")));
      Assert.assertTrue(errorMessageBelowPassword.isDisplayed());

      // Verify error message text is E-mail bądź hasło nieprawidłowe. Popraw dane!
      String expectedErrorMessage = "E-mail bądź hasło nieprawidłowe. Popraw dane";
      String actualErrorMessage = errorMessageBelowPassword.getText();
      Assert.assertEquals(actualErrorMessage, expectedErrorMessage);

      driver.quit();

    }
}

package com.practicetestautomation.tests.login;

import com.practicetestautomation.pageobjects.LoginPage;
import com.practicetestautomation.pageobjects.SuccessfulLoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginTest {
    private WebDriver driver;
    private Logger logger;

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setUp(@Optional("chrome")String browser){
        logger = Logger.getLogger(LoginTest.class.getName());
        logger.setLevel(Level.INFO);
        logger.info("Running test in"+ browser);
        switch (browser.toLowerCase()){
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                logger.warning("Configuration for " + browser + " is missing, so running tests in Chrome by default");
                driver = new ChromeDriver();
                break;
        }
    }
    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
        logger.info("Browser is closed");
    }
    @Parameters({"username","password"})
    @Test(groups = {"positive","regression"})
    public void testLoginFunctionality(String username, String password) {
       logger.info("Starting testLoginFunctionality");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.visit();
        SuccessfulLoginPage successfulLoginPage = loginPage.executeLogin("elenapumpkin8@gmail.com", "Pumpkin@123");
        successfulLoginPage.load();
        logger.info("Verify the login functionality");
        Assert.assertEquals(successfulLoginPage.getCurrentUrl(),"https://info-car.pl/new/");
        Assert.assertTrue(successfulLoginPage.getPageSource().contains("Elena Pumpkin"));
        Assert.assertTrue(successfulLoginPage.isLogoutButtonDisplayed());
    }

@Test(groups = {"negative","regression"})
public void incorrectUsername() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    // Type address incorrect e-mail into Username field
    WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
    emailInput.sendKeys("elenapumpkin@gmail.com");

    // Type password into Password field
    WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
    passwordInput.sendKeys("Pumpkin@123");

    // Push Submit button
    WebElement submitButton = driver.findElement(By.id("register-button"));
    submitButton.click();

    // Verify warning message is displayed
    WebElement errorMessageBelowPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(RelativeLocator.with(By.className("error-description-container")).below(By.id("password"))));
    Assert.assertTrue(errorMessageBelowPassword.isDisplayed());

    // Verify error message text is E-mail bądź hasło nieprawidłowe. Popraw dane!
    String expectedErrorMessage = "E-mail bądź hasło nieprawidłowe. Popraw dane";
    String actualErrorMessage = errorMessageBelowPassword.getText();
    Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
   }
}


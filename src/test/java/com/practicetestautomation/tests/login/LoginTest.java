package com.practicetestautomation.tests.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.Assert;
import org.testng.annotations.*;

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
        // Open Page
        driver.get("https://info-car.pl/oauth2/login");
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
        // Type address e-mail student into Username field
        WebElement emailInput = driver.findElement(By.id("username"));
        logger.info("Type username");
        emailInput.sendKeys("elenapumpkin8@gmail.com");

        // Type password into Password field
        WebElement passwordInput = driver.findElement(By.id("password"));
        logger.info("Type password");
        passwordInput.sendKeys("Pumpkin@123");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Push Submit button
        WebElement submitButton = driver.findElement(By.id("register-button"));
        logger.info("Click Submit button");
        submitButton.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        logger.info("Verify the login functionality");
        // Verify new page URL
        String expectedUrl = "https://info-car.pl/new/";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);

        // Verify new page contains expected text ('Elena Povazhna')
        String expectedNgContent= "Elena Pumpkin";
        String pageSource = driver.getPageSource();
        Assert.assertTrue(pageSource.contains(expectedNgContent));

        // Verify button Log out is displayed on the new page
        WebElement logOutButton = driver.findElement(By.xpath("//button[@class='ng-tns-c4167178825-2']"));
        Assert.assertTrue(logOutButton.isDisplayed());
    }

@Test(groups = {"negative","regression"})
public void incorrectUsername() {
        logger.info("Starting incorrectUsername");
    // Type address incorrect e-mail into Username field
    WebElement emailInput = driver.findElement(By.id("username"));
    logger.info("Typing username: ");
    emailInput.sendKeys("elenapumpkin@gmail.com");

    // Type password into Password field
    WebElement passwordInput = driver.findElement(By.id("password"));
    logger.info("Typing password");
    passwordInput.sendKeys("Pumpkin@123");

    // Push Submit button
    WebElement submitbutton = driver.findElement(By.id("register-button"));
    logger.info("Click Submit button");
    submitbutton.click();


    logger.info("Verify the warning message: " + "expectedErrorMessage");
    // Verify warning message is displayed
    WebElement errorMessageBelowPassword = driver.findElement(RelativeLocator.with(By.className("error-description-container")).below(By.id("password")));
    Assert.assertTrue(errorMessageBelowPassword.isDisplayed());

    // Verify error message text is E-mail bądź hasło nieprawidłowe. Popraw dane!
    String expectedErrorMessage = "E-mail bądź hasło nieprawidłowe. Popraw dane";
    String actualErrorMessage = errorMessageBelowPassword.getText();
    Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
   }
}


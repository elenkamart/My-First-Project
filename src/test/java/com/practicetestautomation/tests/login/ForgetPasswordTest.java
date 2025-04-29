package com.practicetestautomation.tests.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ForgetPasswordTest {
    private WebDriver driver;
    private Logger logger;

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setUp(@Optional("edge")String browser) {
        logger = Logger.getLogger(LoginTest.class.getName());
        logger.setLevel(Level.INFO);
        logger.info("Running test in" + browser);
        switch (browser.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                logger.warning("Configuration for " + browser + " is missing, so running tests in Edge by default");
                driver = new ChromeDriver();
                break;
        }
        // Open Page
        driver = new ChromeDriver();
        driver.get("https://info-car.pl/new/logowanie/zapomnialem-hasla");
    }
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
        logger.info("Browser is closed");
    }
    @Test(groups = {"positive","regression"})
    public void testForgetPassword() {
        logger.info("Starting testForgetPassword");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

       WebElement cookieBtn= driver.findElement(By.id("cookiescript_reject"));
        logger.info("Reject cookies");
        cookieBtn.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Type address e-mail into Username field
        WebElement emailInput = driver.findElement(By.id("email"));
        logger.info("Type address e-mail");
        emailInput.sendKeys("elenapumpkin8@gmail.com");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Push Submit button
        WebElement submitbutton = driver.findElement(By.xpath("//button[@class='ghost-btn']"));
        logger.info("Click Submit button");
        submitbutton.click();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        logger.info("Verify the information is displayed");
        // Verify information is displayed
        WebElement infoMessageBelowEmail = driver.findElement(By.className("info-box__text"));
        Assert.assertTrue(infoMessageBelowEmail.isDisplayed());

        // Verify text is Na podany adres e-mail zostało wysłany link pozwalający na ustawienie nowego hasła.
        String expectedInfoMessage = "Na podany adres e-mail zostało wysłany link pozwalający na ustawienie nowego hasła.";
        String actualInfoMessage = infoMessageBelowEmail.getText();
        Assert.assertEquals(actualInfoMessage, expectedInfoMessage);
    }
}

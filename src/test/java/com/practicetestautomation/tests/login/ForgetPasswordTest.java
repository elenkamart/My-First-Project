package com.practicetestautomation.tests.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
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
                driver = new EdgeDriver();
                break;
        }
        // Open Page
        driver.get("https://info-car.pl/new/logowanie/zapomnialem-hasla");
    }
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
        logger.info("Browser is closed");
    }
    @Test(groups = {"positive","regression"})
    public void testForgetPassword() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement cookieBtn= driver.findElement(By.id("cookiescript_reject"));
        cookieBtn.click();
        
        // Type address e-mail into Username field
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        emailInput.sendKeys("elenapumpkin8@gmail.com");

        // Push Submit button
        WebElement submitButton = driver.findElement(By.xpath("//button[@class='ghost-btn']"));
        submitButton.click();

        WebElement infoMessageBelowEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("info-box__text")));
        // Verify information is displayed
        Assert.assertTrue(infoMessageBelowEmail.isDisplayed());

        // Verify text is Na podany adres e-mail zostało wysłany link pozwalający na ustawienie nowego hasła.
        String expectedInfoMessage = "Na podany adres e-mail zostało wysłany link pozwalający na ustawienie nowego hasła.";
        String actualInfoMessage = infoMessageBelowEmail.getText();
        Assert.assertEquals(actualInfoMessage, expectedInfoMessage);
    }
}

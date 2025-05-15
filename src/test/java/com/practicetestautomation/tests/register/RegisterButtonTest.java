package com.practicetestautomation.tests.register;

import com.practicetestautomation.tests.login.LoginTest;
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

public class RegisterButtonTest {
    private WebDriver driver;
    private Logger logger;

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setUp(@Optional("edge") String browser) {
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
        driver.get("https://info-car.pl/oauth2/login");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
        logger.info("Browser is closed");
    }

    @Test(groups = {"positive", "regression"})
    public void registerButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Push Register button
        WebElement registerButton = driver.findElement(By.xpath("//button[@class='register-button']"));
        registerButton.click();

        // Accept cookies
        WebElement cookieBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cookiescript_accept")));
        cookieBtn.click();

        // Verify new page contains expected text ('Konto indywidualne')
//        String expectedNgContent = "Konto indywidualne";
//        String pageSource = driver.getPageSource();
//        Assert.assertTrue(pageSource.contains(expectedNgContent), ngContent);
        WebElement individualAccount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='tile__title'] ['Konto indywidualne']")));
        Assert.assertTrue(individualAccount.isDisplayed());

        // Push IndividualRegister button
        WebElement individualRegisterButton = driver.findElement(By.xpath("//button[@class='ghost-btn']"));
        individualRegisterButton.click();

        // Verify new page URL
        String expectedUrl = "https://info-car.pl/new/rejestracja/formularz";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    @Test(groups = {"positive", "debug"})
    public void registerButtonForCompany() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Push Register button
        WebElement registerButton = driver.findElement(By.xpath("//button[@class='register-button']"));
        registerButton.click();

        // Reject cookies
        WebElement cookieBtn = driver.findElement(By.id("cookiescript_reject"));
        cookieBtn.click();

        // Verify new page contains expected text ('Konto indywidualne') i ('Konto dla firm')
//        String expectedNgContent = "Konto indywidualne";
//        String ngContent = "Konto dla firm";
//        String pageSource = driver.getPageSource();
//        Assert.assertTrue(pageSource.contains(expectedNgContent), ngContent);
        WebElement companyAccount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='tile__title'] ['Konto dla firm']")));
        Assert.assertTrue(companyAccount.isDisplayed());

        // Push company Register button
        WebElement companyRegisterButton = driver.findElement(By.xpath("//a[@href='/new/tachograf/partnerzy/zakladanie-konta']"));
        companyRegisterButton.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Verify new page URL
        String expectedUrl = "https://info-car.pl/new/tachograf/partnerzy/zakladanie-konta";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }
}


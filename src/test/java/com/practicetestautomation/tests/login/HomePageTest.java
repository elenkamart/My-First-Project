package com.practicetestautomation.tests.login;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class HomePageTest {
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
        driver.get("https://info-car.pl/new/");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
        logger.info("Browser is closed");
    }
    @Test(groups = {"positive","regression"})
    public void homePage() {
        logger.info("Starting homePage");
        // Reject cookies
        WebElement cookieBtn = driver.findElement(By.id("cookiescript_reject"));
        logger.info("Click reject cookies");
        cookieBtn.click();

        // Click DropDownCategories button
        WebElement dropDownCategoriesBtn = driver.findElement(By.id("button-categories"));
        logger.info("Click categories button");
        dropDownCategoriesBtn.click();

        // Choose Driver's license category
        WebElement menuDriverLicenseBtn = driver.findElement(By.linkText("Prawo jazdy"));
        logger.info("Click driver license button");
        menuDriverLicenseBtn.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Verify new page URL
        String expectedUrl = "https://info-car.pl/new/prawo-jazdy";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);

        // Check the availability of driving test
        WebElement serviceLink = driver.findElement(By.linkText("Sprawdź dostępność terminów egzaminu na prawo jazdy lub na test kwalifikacyjny (kod 95)"));
        logger.info("Click service link");
        serviceLink.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Verify new page URL
        String expectedLinkUrl = "https://info-car.pl/new/prawo-jazdy/sprawdz-wolny-termin";
        String actualLinkUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualLinkUrl, expectedLinkUrl);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Click on signUp link
        WebElement signUpLink = driver.findElement(By.xpath("//a[@link='/prawo-jazdy/zapisz-sie-na-egzamin-na-prawo-jazdy']"));
        logger.info("Click sign up link");
        signUpLink.click();

        // Verify new page URL
        String expectedUrlLink = "https://info-car.pl/new/prawo-jazdy/zapisz-sie-na-egzamin-na-prawo-jazdy";
        String actualUrlLink = driver.getCurrentUrl();
        Assert.assertEquals(actualLinkUrl, expectedLinkUrl);


        // Scroll down and click signInBtn
        JavascriptExecutor jse = (JavascriptExecutor)driver;

        WebElement signUpBtn = driver.findElement(By.xpath("//button[@class='ghost-btn']"));
        logger.info("Click sign up button");
        jse.executeScript("window.scrollBy(0, 3000)",signUpBtn);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        signUpBtn.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

package com.practicetestautomation.tests.login;

import com.practicetestautomation.pageobjects.HomePage;
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
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
        logger.info("Browser is closed");
    }
    @Test(groups = {"positive","regression"})
    public void homePage() {
        HomePage homePage = new HomePage(driver);
        homePage.visit();
        homePage.rejectCookieBtn();
        homePage.dropDownCategoriesBtn();
        homePage.menuDriverLicenseBtn();
        logger.info("Verify new page Url");
        Assert.assertEquals(homePage.getCurrentUrl(),"https://info-car.pl/new/prawo-jazdy");
        homePage.serviceLink();
        logger.info("Verify new page Url");
        Assert.assertEquals(homePage.getCurrentUrl(),"https://info-car.pl/new/prawo-jazdy/sprawdz-wolny-termin");
        homePage.signUpLink();
        logger.info("Verify new page Url");
        Assert.assertEquals(homePage.getCurrentUrl(),"https://info-car.pl/new/prawo-jazdy/zapisz-sie-na-egzamin-na-prawo-jazdy");

//        // Verify new page URL
//        String expectedUrl = "https://info-car.pl/new/prawo-jazdy";
//        String actualUrl = driver.getCurrentUrl();
//        Assert.assertEquals(actualUrl, expectedUrl);

//        // Verify new page URL
//        String expectedLinkUrl = "https://info-car.pl/new/prawo-jazdy/sprawdz-wolny-termin";
//        String actualLinkUrl = driver.getCurrentUrl();
//        Assert.assertEquals(actualLinkUrl, expectedLinkUrl);


//        // Verify new page URL
//        String expectedUrlLink = "https://info-car.pl/new/prawo-jazdy/zapisz-sie-na-egzamin-na-prawo-jazdy";
//        String actualUrlLink = driver.getCurrentUrl();
//        Assert.assertEquals(actualLinkUrl, expectedLinkUrl);


        // Scroll down and click signInBtn
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        WebElement signUpBtn = driver.findElement(By.xpath("//button[@class='ghost-btn']"));
        jse.executeScript("window.scrollBy(0, 3000)",signUpBtn);

    }
}

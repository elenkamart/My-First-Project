package com.practicetestautomation.tests.login;

import com.practicetestautomation.pageobjects.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class HomePageTest extends BaseTest {
//    private WebDriver driver;
//    private Logger logger;
//    @BeforeMethod(alwaysRun = true)
//    @Parameters("browser")
//    public void setUp(@Optional("chrome") String browser) {
//        logger = Logger.getLogger(LoginTest.class.getName());
//        logger.setLevel(Level.INFO);
//        logger.info("Running test in" + browser);
//        switch (browser.toLowerCase()) {
//            case "chrome":
//                driver = new ChromeDriver();
//                break;
//            case "edge":
//                driver = new EdgeDriver();
//                break;
//            default:
//                logger.warning("Configuration for " + browser + " is missing, so running tests in Chrome by default");
//                driver = new ChromeDriver();
//                break;
//        }
//    }
//
//    @AfterMethod(alwaysRun = true)
//    public void tearDown() {
//        driver.quit();
//        logger.info("Browser is closed");
//    }

    @Test(groups = {"positive", "regression"})
    public void testHomePage() throws InterruptedException {
        logger.info("Starting testHomePage");
        HomePage homePage = new HomePage(driver);
        homePage.visit();
        homePage.rejectCookieBtn();
        homePage.dropDownCategoriesBtn();
        homePage.menuDriverLicenseBtn();
        homePage.isServiceLinkDisplayedAfterWait();
        logger.info("Verify new page Url");
        Assert.assertEquals(homePage.getCurrentUrl(), "https://info-car.pl/new/prawo-jazdy");
        homePage.serviceLink();
        homePage.isSignUpLinkDisplayedAfterWait();
        logger.info("Verify new page Url");
        Assert.assertEquals(homePage.getCurrentUrl(), "https://info-car.pl/new/prawo-jazdy/sprawdz-wolny-termin");
        homePage.signUpLink();
        logger.info("Verify new page Url");
        Assert.assertEquals(homePage.getCurrentUrl(), "https://info-car.pl/new/prawo-jazdy/zapisz-sie-na-egzamin-na-prawo-jazdy");
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        WebElement signUpBtn = driver.findElement(By.xpath("//button[@class='ghost-btn']"));
//        driver.findElement(By.xpath("//button[@class='ghost-btn']")).sendKeys(Keys.CONTROL, Keys.END);
        jse.executeScript("arguments[0].scrollIntoView(true);", signUpBtn);
        driver.manage().timeouts().implicitlyWait(250, TimeUnit.SECONDS);
        homePage.waitForSignUpBtn();
        Thread.sleep(3000);
        homePage.setSignUpBtn();

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
//        JavascriptExecutor jse = (JavascriptExecutor)driver;
//        WebElement signUpBtn = driver.findElement(By.xpath("//button[@class='ghost-btn']"));
//        jse.executeScript("window.scrollBy(0, 3000)",signUpBtn);
            }
        }


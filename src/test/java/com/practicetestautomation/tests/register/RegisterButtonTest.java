package com.practicetestautomation.tests.register;

import com.practicetestautomation.pageobjects.RegisterButtonPage;
import com.practicetestautomation.tests.login.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterButtonTest extends BaseTest {
//    private WebDriver driver;
//    private Logger logger;
//
//    @BeforeMethod(alwaysRun = true)
//    @Parameters("browser")
//    public void setUp(@Optional("edge") String browser) {
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
//                logger.warning("Configuration for " + browser + " is missing, so running tests in Edge by default");
//                driver = new EdgeDriver();
//                break;
//        }

//    @AfterMethod(alwaysRun = true)
//    public void tearDown() {
//        driver.quit();
//        logger.info("Browser is closed");
//    }

    @Test(groups = {"positive", "regression"})
    public void registerButtonTest() {
        logger.info("Starting testRegisterButton");
        RegisterButtonPage registerButtonPage = new RegisterButtonPage(driver);
        registerButtonPage.visit();
        registerButtonPage.pushRegisterButton();
        registerButtonPage.acceptCookieBtn();
        registerButtonPage.isIndividualAccountDisplayedAfterWait();
        registerButtonPage.pushIndividualRegisterBtn();
        logger.info("Verify new page Url");
        Assert.assertEquals(registerButtonPage.getCurrentUrl(), "https://info-car.pl/new/rejestracja/formularz");

//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
// Push Register button
//        WebElement registerButton = driver.findElement(By.xpath("//button[@class='register-button']"));
//        registerButton.click();
        // Accept cookies
//        WebElement cookieBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cookiescript_accept")));
//        cookieBtn.click();
        // Verify new page contains expected text ('Konto indywidualne')
//        String expectedNgContent = "Konto indywidualne";
//        String pageSource = driver.getPageSource();
//        Assert.assertTrue(pageSource.contains(expectedNgContent), ngContent);
//        WebElement individualAccount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='tile__title'] ['Konto indywidualne']")));
//        Assert.assertTrue(individualAccount.isDisplayed());
        // Push IndividualRegister button
//        WebElement individualRegisterButton = driver.findElement(By.xpath("//button[@class='ghost-btn']"));
//        individualRegisterButton.click();
        // Verify new page URL
//        String expectedUrl = "https://info-car.pl/new/rejestracja/formularz";
//        String actualUrl = driver.getCurrentUrl();
//        Assert.assertEquals(actualUrl, expectedUrl);
    }

    @Test(groups = {"positive", "debug"})
    public void registerButtonForCompany() throws InterruptedException {
        logger.info("Starting testRegisterButtonForCompany");
        RegisterButtonPage registerButtonPage = new RegisterButtonPage(driver);
        registerButtonPage.visit();
        registerButtonPage.pushRegisterButton();
        registerButtonPage.rejectCookieBtn();
        registerButtonPage.isCompanyAccountDisplayedAfterWait();
        registerButtonPage.pushCompanyRegisterBtn();
        Thread.sleep(2000);
        Assert.assertEquals(registerButtonPage.getCurrentUrl(),"https://info-car.pl/new/tachograf/partnerzy/zakladanie-konta");
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Push Register button
//        WebElement registerButton = driver.findElement(By.xpath("//button[@class='register-button']"));
//        registerButton.click();
        // Reject cookies
//        WebElement cookieBtn = driver.findElement(By.id("cookiescript_reject"));
//        cookieBtn.click();
        // Verify new page contains expected text ('Konto indywidualne') i ('Konto dla firm')
//        String expectedNgContent = "Konto indywidualne";
//        String ngContent = "Konto dla firm";
//        String pageSource = driver.getPageSource();
//        Assert.assertTrue(pageSource.contains(expectedNgContent), ngContent);
//        WebElement companyAccount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='tile__title'] ['Konto dla firm']")));
//        Assert.assertTrue(companyAccount.isDisplayed());
        // Push company Register button
//        WebElement companyRegisterButton = driver.findElement(By.xpath("//a[@href='/new/tachograf/partnerzy/zakladanie-konta']"));
//        companyRegisterButton.click();

        // Verify new page URL
//        String expectedUrl = "https://info-car.pl/new/tachograf/partnerzy/zakladanie-konta";
//        String actualUrl = driver.getCurrentUrl();
//        Assert.assertEquals(actualUrl, expectedUrl);
    }
}


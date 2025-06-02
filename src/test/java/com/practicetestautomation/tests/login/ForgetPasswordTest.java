package com.practicetestautomation.tests.login;

import com.practicetestautomation.pageobjects.ForgetPasswordPage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ForgetPasswordTest extends BaseTest {
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
//    }
//
//    @AfterMethod(alwaysRun = true)
//    public void tearDown() {
//        driver.quit();
//        logger.info("Browser is closed");
//    }
    @Parameters({"username", "expectedInfoMessage"})
    @Test(groups = {"positive", "regression"})
    public void testForgetPassword(String email, String expectedInfoMessage) {
        ForgetPasswordPage forgetPasswordPage = new ForgetPasswordPage(driver);
        forgetPasswordPage.visit();
        forgetPasswordPage.rejectCookieBtn();
        forgetPasswordPage.enterEmail("elenapumpkin8@gmail.com");
        forgetPasswordPage.submitButton();
        forgetPasswordPage.load();
        Assert.assertTrue(forgetPasswordPage.isInfoMessageLocatorDisplayed());
        Assert.assertTrue(forgetPasswordPage.getPageSource().contains("Na podany adres e-mail zostało wysłany link pozwalający na ustawienie nowego hasła."));
        Assert.assertEquals(forgetPasswordPage.getInfoMessage(), expectedInfoMessage);
    }
}



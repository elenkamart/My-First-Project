package com.practicetestautomation.tests.login;

import com.practicetestautomation.pageobjects.LoginPage;
import com.practicetestautomation.pageobjects.SuccessfulLoginPage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
//    private WebDriver driver;
//    private Logger logger;
//
//    @BeforeMethod(alwaysRun = true)
//    @Parameters("browser")
//    public void setUp(@Optional("chrome")String browser){
//        logger = Logger.getLogger(LoginTest.class.getName());
//        logger.setLevel(Level.INFO);
//        logger.info("Running test in"+ browser);
//        switch (browser.toLowerCase()){
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
//    @AfterMethod(alwaysRun = true)
//    public void tearDown(){
//        driver.quit();
//        logger.info("Browser is closed");
//    }
    @Parameters({"username","password"})
    @Test(groups = {"positive","regression"})
    public void testLoginFunctionality(String email, String password) {
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
@Parameters({"username","password", "expectedErrorMessage" })
@Test(groups = {"negative","regression"})
public void incorrectUsernameTest(String email, String password, String expectedErrorMessage ) {
   logger.info("Starting incorrectUsernameTest");
    LoginPage loginPage = new LoginPage(driver);
    loginPage.visit();
    loginPage.executeLogin(email, password);
    Assert.assertTrue(loginPage.getPageSource().contains("E-mail bądź hasło nieprawidłowe. Popraw dane"));
    Assert.assertEquals(loginPage.getErrorMessage(), expectedErrorMessage);
}
}



package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;


public class BaseTest
{
    // This is a base test class
    // This class will contain common methods that can be used across all test classes
    // For example, setup and teardown methods
    // This class will be extended by all test classes

    LoginPage loginPage;
    WebDriver driver;

    public WebDriverWait wait;
    public String baseUrl = "https://opensource-demo.orangehrmlive.com/";
    public String AdminAccount = "Admin";
    public String AdminPassword = "admin123";
    public String AdminUsernameEnabled = "";
    public String AdminPasswordEnabled = "";
    public String EssUsernameEnabled = "";
    public String EssPasswordEnabled = "";
    public String UsernameDisabled = "";
    public String PasswordDisabled = "";


    public String projectPath = System.getProperty("user.dir");


    // Locators

    public BaseTest()
    {
    }

    public BaseTest(WebDriver driver)
    {
        loginPage = new LoginPage(driver);
        this.driver = driver;
    }

    public void login(String username, String password)
    {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();

    }

//    @AfterMethod
//    public void tearDown(ITestResult result) {
//        // Capture screenshot for every test case
//        ScreenshotUtils.captureScreenshot(driver);
//
//        // Add test result to Allure with screenshot
//        if (result.getStatus() == ITestResult.FAILURE) {
//            Allure.addAttachment("Failed Test Screenshot", new ByteArrayInputStream(ScreenshotUtils.captureScreenshot(driver)));
//        } else {
//            Allure.addAttachment("Passed Test Screenshot", new ByteArrayInputStream(ScreenshotUtils.captureScreenshot(driver)));
//        }
//
//        // Optionally, close the driver here if you don't want it running for multiple tests
//        driver.quit();
//    }



}

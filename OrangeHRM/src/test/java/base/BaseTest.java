package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import pages.LoginPage;

import java.time.Duration;


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
    public String EmptyUsername = "";
    public String EmptyPassword = "";
    public String NonExistentUser = "NonExistentUser";
    public String caseSensitiveUsername = "admin";


    public String projectPath = System.getProperty("user.dir");


    // Constructor
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

    public void setImplicitWaitMillis(long timeInMillis) {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(timeInMillis));
    }




    // After each test, quit the browser
//    @AfterMethod
//    public void tearDown()
//    {
//        if (driver != null) {
//            driver.quit();
//        }
//    }


}

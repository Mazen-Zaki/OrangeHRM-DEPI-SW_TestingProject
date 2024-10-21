package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;

import java.time.Duration;


public class BaseTest
{
    // This is a base test class
    // This class will contain common methods that can be used across all test classes
    // For example, setup and teardown methods
    // This class will be extended by all test classes

    public LoginPage loginPage;
    public WebDriver driver;

    public WebDriverWait wait;
    public String baseUrl = "https://opensource-demo.orangehrmlive.com/";
    public String AdminAccount = "Admin";
    public String AdminPassword = "admin123";
    public String AdminUsernameEnabled = "";
    public String AdminPasswordEnabled = "";
    public String EssUsernameEnabled = "FMLName";
    public String EssPasswordEnabled = "mm12345";
    public String UsernameDisabled = "";
    public String PasswordDisabled = "";
    public String EmptyUsername = "";
    public String EmptyPassword = "";
    public String NonExistentUser = "NonExistentUser";
    public String caseSensitiveUsername = "admin";
    public String newPassword = "nn12345";
    public String validationLengthPassword = "nn123";
    public String validationCaseSensitivePassword = "123456789";


    public String projectPath = System.getProperty("user.dir");


    // Constructor
    public BaseTest()
    {}


    public void login(String username, String password)
    {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();

    }

    public void setImplicitWaitMillis(long timeInMillis)
    {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(timeInMillis));
    }


    // Setup method to initialize WebDriver and open the login page
    @BeforeMethod
    public void setUp() {
        // Initialize ChromeDriver (Selenium 4 manages drivers automatically)
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));

        // Initialize the login page object
        loginPage = new LoginPage(driver);
    }


    // After each test, quit the browser
    @AfterMethod
    public void tearDown()
    {
        if (driver != null) {
            driver.quit();
        }
    }


}

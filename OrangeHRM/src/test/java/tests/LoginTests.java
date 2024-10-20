package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.time.Duration;

public class LoginTests extends BaseTest
{
    WebDriver driver;
    LoginPage loginPage;
    BaseTest baseTest;

    // Locators
    By invalidCredentialAlert = By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']");
    By requiredAlert = By.xpath("//span[@class=\"oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message\"]");
    By resetPasswordSuccessfullyMessage = By.xpath("//h6[@class='oxd-text oxd-text--h6 orangehrm-forgot-password-title']");
    By resetPasswordButton = By.xpath("//button[@type=\"submit\"]");


    // Setup method to initialize WebDriver and open the login page
    @BeforeMethod
    public void setUp()
    {
        // Initialize ChromeDriver (Selenium 4 manages drivers automatically)
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));

        // Initialize the LoginPage object
        loginPage = new LoginPage(driver);
        baseTest = new BaseTest(driver);
    }

    // Test case: Verify valid login for Admin role
    @Test(priority = 1, description = "Verify valid login for Admin role")
    public void verifyValidLoginAdminRole()
    {
        // Login with valid credentials
        baseTest.login(AdminAccount, AdminPassword);
        
        // Check if the user is redirected to the dashboard
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"), "Admin dashboard is not displayed.");
    }

    // Test case: Verify invalid login with incorrect password
    @Test(priority = 1, description = "Verify invalid login with incorrect password")
    public void verifyInvalidLoginIncorrectPassword()
    {
        // Login with invalid credentials
        baseTest.login(AdminAccount, EssPasswordEnabled);

        // Wait for the error message to be visible (with a timeout of 10 seconds)
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(invalidCredentialAlert));

        // Check if the user remains on the login page (URL should not change to dashboard)
        Assert.assertTrue(driver.getCurrentUrl().contains("login"), "User is redirected despite invalid credentials.");
    }

    // Test case: Verify empty username and password at login page
    @Test(priority = 1, description = "Verify empty username and password at login page")
    public void verifyEmptyUsernameAndPassword()
    {
        // Leave the username and password fields empty
        baseTest.login(EmptyUsername, EmptyPassword);

        // Wait for the error message to be visible (with a timeout of 10 seconds)
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(requiredAlert));

        // Check if the user remains on the login page (URL should not change to dashboard)
        Assert.assertTrue(driver.getCurrentUrl().contains("login"), "User is redirected despite invalid credentials.");
    }

    // Test case: Verify empty username at login page
    @Test(priority = 1, description = "Verify empty username at login page")
    public void verifyEmptyUsername()
    {
        // Leave the username field empty
        baseTest.login(EmptyUsername, AdminPassword);

        // Wait for the error message to be visible (with a timeout of 3 seconds)
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(requiredAlert));

        // Check if the user remains on the login page (URL should not change to dashboard)
        Assert.assertTrue(driver.getCurrentUrl().contains("login"), "User is redirected despite invalid credentials.");
    }

    // Test case: Verify empty password at login page
    @Test(priority = 1, description = "Verify empty password at login page")
    public void verifyEmptyPassword()
    {
        // Leave the username field empty
        baseTest.login(AdminAccount, EmptyPassword);

        // Wait for the error message to be visible (with a timeout of 3 seconds)
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(requiredAlert));

        // Check if the user remains on the login page (URL should not change to dashboard)
        Assert.assertTrue(driver.getCurrentUrl().contains("login"), "User is redirected despite invalid credentials.");
    }

    // Test case: Verify functionality of LinkedIn button
    @Test(priority = 3, description = "Verify functionality of linkedin button")
    public void verifyLinkedInButtonFunctionality()
    {
        String linkedInUrl;

        // Click on the LinkedIn button
        loginPage.clickLinkedInButton();

        // Verify that the LinkedIn page URL is correct
        linkedInUrl = loginPage.getLinkedInPageUrl();
        Assert.assertTrue(linkedInUrl.contains("linkedin.com"), "The LinkedIn page URL is incorrect!");
    }

    @Test(priority = 3, description = "Verify functionality of facebook button")
    public void verifyFacebookButtonFunctionality()
    {
        String facebookUrl;

        loginPage.clickFacebookButton();

        facebookUrl = loginPage.getFacebookPageUrl();
        Assert.assertTrue(facebookUrl.contains("facebook.com"), "The Facebook page URL is incorrect!");
    }

    @Test(priority = 3, description = "Verify functionality of twitter button")
    public void verifyTwitterButtonFunctionality()
    {
        String twitterUrl;

        loginPage.clickTwitterButton();

        twitterUrl = loginPage.getTwitterPageUrl();
        Assert.assertTrue(twitterUrl.contains("x.com"), "The Twitter page URL is incorrect!");
    }

    @Test(priority = 3, description = "Verify functionality of youtube button")
    public void verifyYoutubeButtonFunctionality()
    {
        String youtubeUrl;

        loginPage.clickYoutubeButton();

        youtubeUrl = loginPage.getYoutubePageUrl();
        Assert.assertTrue(youtubeUrl.contains("youtube.com"), "The Youtube page URL is incorrect!");

    }

    // Test case: Username Case Sensitivity at Login Page
    @Test(priority = 2, description = "Username Case Sensitivity at Login Page")
    public void caseSensitivityUsername()
    {
        // Login with invalid credentials
        baseTest.login(caseSensitiveUsername, AdminPassword);

        // Check if the user remains on the login page (URL should not change to dashboard)
        Assert.assertTrue(driver.getCurrentUrl().contains("login"), "User is redirected despite invalid credentials.");
    }

    // Test case: Forgot Password - Invalid Username at Login Page
    @Test(priority = 1, description = "Forgot Password - Invalid Username at Login Page")
    public void forgotPasswordInvalidUsername()
    {
        loginPage.clickForgotPasswordButton();

        wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        wait.until(ExpectedConditions.visibilityOfElementLocated(resetPasswordButton));

        loginPage.enterUsername(NonExistentUser);
        loginPage.clickResetPasswordButton();

        wait.until(ExpectedConditions.visibilityOfElementLocated(resetPasswordSuccessfullyMessage));

        boolean res = loginPage.isResetPasswordSuccessfullyMessageShown();

        if (res)
        {
            Assert.fail("Reset password successfully message should not be displayed.");
        }
        else
        {
            Assert.assertTrue(true, "Reset password successfully message is not displayed as expected.");
        }

    }

    // Test case: Forgot Password - Cancel Button at Login Page
    @Test(priority = 1, description = "Forgot Password - Cancel Button at Login Page")
    public void verifyFunctionalityOfCancelButton()
    {
        loginPage.clickForgotPasswordButton();

        wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        wait.until(ExpectedConditions.visibilityOfElementLocated(resetPasswordButton));

        loginPage.clickCancelButton();

        Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Cancel Button is not working as expected.");


    }

    // Test case: Verity Valid Login - ESS Role at Login Page
    @Test(priority = 1, description = "Verity Valid Login - ESS Role at Login Page")
    public void verifyValidLoginEssRole()
    {
        // Login with valid credentials
        baseTest.login(EssUsernameEnabled, EssPasswordEnabled);

        // Check if the user is redirected to the dashboard
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"), "ESS dashboard is not displayed.");
    }

    // Test case: Verity Invalid Login - Disabled Status at Login Page
    @Test(priority = 15, description = "Verity Invalid Login - Disabled Status at Login Page")
    public void verifyInvalidLoginDisabledStatus()
    {
        // Login with invalid credentials
        baseTest.login(UsernameDisabled, PasswordDisabled);

        Assert.assertTrue(driver.getCurrentUrl().contains("login"), "User is redirected despite invalid credentials.");
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

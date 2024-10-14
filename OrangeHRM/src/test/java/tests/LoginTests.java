package tests;

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

public class LoginTests
{
    WebDriver driver;
    LoginPage loginPage;

    // Setup method to initialize WebDriver and open the login page
    @BeforeMethod
    public void setUp() {
        // Initialize ChromeDriver (Selenium 4 manages drivers automatically)
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));

        // Initialize the LoginPage object
        loginPage = new LoginPage(driver);
    }

    // Test case: Verify valid login for Admin role
    @Test(priority = 1, description = "Verify valid login for Admin role")
    public void verifyValidLoginAdminRole() {
        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickLoginButton();

        // Check if the user is redirected to the dashboard
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"), "Admin dashboard is not displayed.");
    }

    // Test case: Verify invalid login with incorrect password
    @Test(priority = 2, description = "Verify invalid login with incorrect password")
    public void verifyInvalidLoginIncorrectPassword() {
        loginPage.enterUsername("Admin");
        loginPage.enterPassword("wrongpassword");
        loginPage.clickLoginButton();

        // Wait for the error message to be visible (with a timeout of 10 seconds)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']")));

        // Check if the user remains on the login page (URL should not change to dashboard)
        Assert.assertTrue(driver.getCurrentUrl().contains("login"), "User is redirected despite invalid credentials.");
    }

    // Test case: Verify empty username and password at login page
    @Test(priority = 3, description = "Verify empty username and password at login page")
    public void verifyEmptyUsernameAndPassword() {
        // Leave username and password empty
        loginPage.enterUsername("");
        loginPage.enterPassword("");
        loginPage.clickLoginButton();

        // Wait for the error message to be visible (with a timeout of 10 seconds)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\"oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message\"]")));

        // Check if the user remains on the login page (URL should not change to dashboard)
        Assert.assertTrue(driver.getCurrentUrl().contains("login"), "User is redirected despite invalid credentials.");
    }

    // Test case: Verify empty username at login page
    @Test(priority = 4, description = "Verify empty username at login page")
    public void verifyEmptyUsername() {
        // Leave the username field empty
        loginPage.enterUsername("");
        loginPage.enterPassword("admin123");
        loginPage.clickLoginButton();

        // Wait for the error message to be visible (with a timeout of 3 seconds)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\"oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message\"]")));

        // Check if the user remains on the login page (URL should not change to dashboard)
        Assert.assertTrue(driver.getCurrentUrl().contains("login"), "User is redirected despite invalid credentials.");
    }

    // Test case: Verify empty password at login page
    @Test(priority = 5, description = "Verify empty password at login page")
    public void verifyEmptyPassword() {
        // Leave the username field empty
        loginPage.enterUsername("Admin");
        loginPage.enterPassword("");
        loginPage.clickLoginButton();

        // Wait for the error message to be visible (with a timeout of 3 seconds)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\"oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message\"]")));

        // Check if the user remains on the login page (URL should not change to dashboard)
        Assert.assertTrue(driver.getCurrentUrl().contains("login"), "User is redirected despite invalid credentials.");
    }

    // Test case: Verify functionality of LinkedIn button
    @Test(priority = 6, description = "Verify functionality of linkedin button")
    public void verifyLinkedInButtonFunctionality() {
        // Store the current window handle
        String originalWindow = driver.getWindowHandle();

        // Click on the LinkedIn button
        loginPage.clickLinkedInButton();

        // Verify that the LinkedIn page URL is correct
        String linkedInUrl = loginPage.getLinkedInPageUrl();
        Assert.assertTrue(linkedInUrl.contains("linkedin.com"), "The LinkedIn page URL is incorrect!");

        // Switch back to the original window
        loginPage.switchBackToOriginalWindow(originalWindow);
    }

    @Test(priority = 7, description = "Verify functionality of facebook button")
    public void verifyFacebookButtonFunctionality()
    {
        String originalWindow = driver.getWindowHandle();

        loginPage.clickFacebookButton();

        String facebookUrl = loginPage.getFacebookPageUrl();
        Assert.assertTrue(facebookUrl.contains("facebook.com"), "The Facebook page URL is incorrect!");

        loginPage.switchBackToOriginalWindow(originalWindow);
    }

    @Test(priority = 8, description = "Verify functionality of twitter button")
    public void verifyTwitterButtonFunctionality()
    {
        String originalWindow = driver.getWindowHandle();

        loginPage.clickTwitterButton();

        String twitterUrl = loginPage.getTwitterPageUrl();
        Assert.assertTrue(twitterUrl.contains("x.com"), "The Twitter page URL is incorrect!");

        loginPage.switchBackToOriginalWindow(originalWindow);
    }

    @Test(priority = 9, description = "Verify functionality of youtube button")
    public void verifyYoutubeButtonFunctionality()
    {
        String originalWindow = driver.getWindowHandle();

        loginPage.clickYoutubeButton();

        String youtubeUrl = loginPage.getYoutubePageUrl();
        Assert.assertTrue(youtubeUrl.contains("youtube.com"), "The Youtube page URL is incorrect!");

        loginPage.switchBackToOriginalWindow(originalWindow);
    }

    // After each test, quit the browser
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

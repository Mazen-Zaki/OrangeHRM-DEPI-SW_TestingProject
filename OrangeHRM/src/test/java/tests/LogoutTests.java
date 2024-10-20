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
import pages.LogoutPage;

import java.time.Duration;

public class LogoutTests extends BaseTest
{
    WebDriver driver;
    LogoutPage logoutPage;
    BaseTest baseTest;

    // Locators
    By userDropDownButton = By.xpath("//img[@class='oxd-userdropdown-img']");

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

        // Initialize the LogoutPage object
        logoutPage = new LogoutPage(driver);
        baseTest = new BaseTest(driver);
    }

    // Test case: Logout - Verify of Logout Button - Admin Role
    @Test(priority = 1, description = "Logout - Verify of Logout Button - Admin Role")
    public void verifyFunctionalityOfLogoutButtonAdmin()
    {
        baseTest.login(AdminAccount, AdminPassword);

        // Wait for the error message to be visible (with a timeout of 10 seconds)
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(userDropDownButton) );

        logoutPage.clickUserDropDownButton();
        logoutPage.clickLogoutButtonButton();

        Assert.assertTrue(driver.getCurrentUrl().contains("login"));
    }

    // Test case: Logout - Verify of Logout Button - ESS Role
    @Test(priority = 2, description = "Logout - Verify of Logout Button - ESS Role")
    public void verifyFunctionalityOfLogoutButtonEss()
    {
        baseTest.login(EssUsernameEnabled, EssPasswordEnabled);

        // Wait for the error message to be visible (with a timeout of 10 seconds)
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(userDropDownButton) );

        logoutPage.clickUserDropDownButton();
        logoutPage.clickLogoutButtonButton();

        Assert.assertTrue(driver.getCurrentUrl().contains("login"));
    }

    @AfterMethod
    public void tearDown()
    {
        driver.quit();
    }

}

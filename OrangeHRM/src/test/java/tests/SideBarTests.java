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
import pages.SideBarPage;

import java.time.Duration;

public class SideBarTests extends BaseTest
{
    SideBarPage sideBarPage;

    // Locators
    By userDropDownButton = By.xpath("//img[@class='oxd-userdropdown-img']");


    // override the setUp method to initialize the SideBarPage
    @BeforeMethod
    public void setUp()
    {
        super.setUp();
        sideBarPage = new SideBarPage(driver);
    }

    // Test case: Verity Navigation to the SideBar Page - Admin
    @Test(priority = 1, description = "Verity Navigation to the SideBar Page - Admin")
    public void verifyNavigationAdminRole()
    {
        // Login to the application
        login(AdminAccount, AdminPassword);

        // Wait for the error message to be visible (with a timeout of 10 seconds)
        wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        wait.until(ExpectedConditions.visibilityOfElementLocated(userDropDownButton) );


        // Verify the page is displayed
        Assert.assertTrue(sideBarPage.isThePageDisplayed("Admin"), "Admin page is not displayed");

        setImplicitWaitMillis(500);

        Assert.assertTrue(sideBarPage.isThePageDisplayed("recruitment"), "Recruitment page is not displayed");

        setImplicitWaitMillis(500);

        Assert.assertTrue(sideBarPage.isThePageDisplayed("PIM"), "PIM page is not displayed");

        setImplicitWaitMillis(500);

        Assert.assertTrue(sideBarPage.isThePageDisplayed("Time"), "Time page is not displayed");

        setImplicitWaitMillis(500);

        Assert.assertTrue(sideBarPage.isThePageDisplayed("Performance"), "Performance page is not displayed");

        setImplicitWaitMillis(500);

        Assert.assertTrue(sideBarPage.isThePageDisplayed("Dashboard"), "Dashboard page is not displayed");

        setImplicitWaitMillis(500);

        Assert.assertTrue(sideBarPage.isThePageDisplayed("Directory"), "Directory page is not displayed");

        setImplicitWaitMillis(500);

        Assert.assertTrue(sideBarPage.isThePageDisplayed("Buzz"), "Buzz page is not displayed");

        setImplicitWaitMillis(500);

        Assert.assertTrue(sideBarPage.isThePageDisplayed("My Info"), "My Info page is not displayed");

        setImplicitWaitMillis(500);

        Assert.assertTrue(sideBarPage.isThePageDisplayed("Claim"), "Claim page is not displayed");

        setImplicitWaitMillis(500);

        Assert.assertTrue(sideBarPage.isThePageDisplayed("Leave"), "Leave page is not displayed");

        setImplicitWaitMillis(500);

        Assert.assertTrue(sideBarPage.isThePageDisplayed("Maintenance"), "Maintenance page is not displayed");



    }

    // Test case: Verity Navigation to the SideBar Page - ESS
    @Test(priority = 2, description = "Verity Navigation to the SideBar Page - ESS")
    public void verifyNavigationEssRole()
    {
        // Login to the application
        login(EssUsernameEnabled, EssPasswordEnabled);

        // Wait for the error message to be visible (with a timeout of 10 seconds)
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(userDropDownButton) );


        // Verify the page is displayed
        Assert.assertFalse(sideBarPage.isThePageDisplayed("Admin"));

        // Wait for the error message to be visible (with a timeout of 10 seconds)
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(userDropDownButton) );

        Assert.assertFalse(sideBarPage.isThePageDisplayed("Recruitment"));

        // Wait for the error message to be visible (with a timeout of 10 seconds)
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(userDropDownButton) );

        Assert.assertFalse(sideBarPage.isThePageDisplayed("PIM"));

        // Wait for the error message to be visible (with a timeout of 10 seconds)
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(userDropDownButton) );

        Assert.assertTrue(sideBarPage.isThePageDisplayed("Leave"));

        // Wait for the error message to be visible (with a timeout of 10 seconds)
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(userDropDownButton) );

        Assert.assertTrue(sideBarPage.isThePageDisplayed("Time"));

        // Wait for the error message to be visible (with a timeout of 10 seconds)
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(userDropDownButton) );

        Assert.assertTrue(sideBarPage.isThePageDisplayed("Performance"));

        // Wait for the error message to be visible (with a timeout of 10 seconds)
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(userDropDownButton) );

        Assert.assertTrue(sideBarPage.isThePageDisplayed("Dashboard"));

        // Wait for the error message to be visible (with a timeout of 10 seconds)
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(userDropDownButton) );

        Assert.assertTrue(sideBarPage.isThePageDisplayed("Directory"));

        // Wait for the error message to be visible (with a timeout of 10 seconds)
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(userDropDownButton) );

        Assert.assertFalse(sideBarPage.isThePageDisplayed("Maintenance"));

        // Wait for the error message to be visible (with a timeout of 10 seconds)
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(userDropDownButton) );

        Assert.assertTrue(sideBarPage.isThePageDisplayed("Buzz"));

        // Wait for the error message to be visible (with a timeout of 10 seconds)
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(userDropDownButton) );

        Assert.assertTrue(sideBarPage.isThePageDisplayed("My Info"));

        // Wait for the error message to be visible (with a timeout of 10 seconds)
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(userDropDownButton) );

        Assert.assertTrue(sideBarPage.isThePageDisplayed("My Leave"));


    }
}

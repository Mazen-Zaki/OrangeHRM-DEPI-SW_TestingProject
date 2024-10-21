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
import pages.InterfacePage;

import java.time.Duration;

public class InterfaceTests extends BaseTest
{
    WebDriver driver;
    InterfacePage interfacePage;
    BaseTest baseTest;

    // Locators
    By userDropDownButton = By.xpath("//img[@class='oxd-userdropdown-img']");
    By AboutButton = By.xpath("//ul[@class=\"oxd-dropdown-menu\"]//li[1]");
    By successMessageChangePassword = By.xpath("//p[contains(@class, 'oxd-text--toast-title')]");



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

        // Initialize the InterfacePage object
        interfacePage = new InterfacePage(driver);
        baseTest = new BaseTest(driver);

    }

    // Test case: LVerity Functionality of Profile Icon
    @Test(priority = 2, description = "Verity Functionality of Profile Icon")
    public void verifyFunctionalityOfProfileIcon()
    {
        baseTest.login(AdminAccount, AdminPassword);

        // Wait for the error message to be visible (with a timeout of 10 seconds)
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(userDropDownButton) );

        interfacePage.clickUserDropDownButton();

        // Wait for the dropdown menu to be visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(AboutButton));


        // Assert both items are found
        Assert.assertTrue(interfacePage.isDropdownListFull(), "'About' item is not found in the dropdown");
    }

    // Test case: Verify Functionality of Upgrade Button
    @Test(priority = 2, description = "Verify Functionality of Upgrade Button")
    public void verifyUpgradeButtonFunctionality()
    {
        String originalWindow;
        baseTest.login(AdminAccount, AdminPassword);

        // Wait for the error message to be visible (with a timeout of 10 seconds)
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(userDropDownButton) );

        interfacePage.clickUpgradeButton();


        String upgradeUrl = interfacePage.getCurrentPageUrl();

        Assert.assertTrue(upgradeUrl.contains("orangehrm.com"), "The page URL is incorrect!");
    }

    // Test case: Verify Functionality of Side Bar Button
    @Test(priority = 2, description = "Verify Functionality of Side Bar Button")
    public void verifySideBarButtonFunctionality()
    {
        baseTest.login(AdminAccount, AdminPassword);

        // Wait for the error message to be visible (with a timeout of 10 seconds)
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(userDropDownButton) );

        interfacePage.clickSideBarButton();
        interfacePage.clickSideBarButton();
        interfacePage.clickSideBarButton();

        Assert.assertTrue(interfacePage.getCurrentPageUrl().contains("dashboard"), "The side Bar isn't work correctly");
    }

    // Test case: Verify Functionality of Search text
    @Test(priority = 2, description = "Verify Functionality of Search text")
    public void verifySearchFunctionality()
    {
        baseTest.login(AdminAccount, AdminPassword);
        boolean searchResult = false;

        baseTest.setImplicitWaitMillis(500);

        searchResult = interfacePage.searchAndVerify("Admin");
        Assert.assertTrue(searchResult, "The side Bar isn't work correctly - Admin");

        baseTest.setImplicitWaitMillis(500);

        searchResult = interfacePage.searchAndVerify("PIM");
        Assert.assertTrue(searchResult, "The side Bar isn't work correctly - PIM");

        baseTest.setImplicitWaitMillis(500);

        searchResult = interfacePage.searchAndVerify("Leave");
        Assert.assertTrue(searchResult, "The side Bar isn't work correctly - Leave");

        baseTest.setImplicitWaitMillis(500);

        searchResult = interfacePage.searchAndVerify("Time");
        Assert.assertTrue(searchResult, "The side Bar isn't work correctly - Time");

        baseTest.setImplicitWaitMillis(500);

        searchResult = interfacePage.searchAndVerify("Recruitment");
        Assert.assertTrue(searchResult, "The side Bar isn't work correctly - Recruitment");

        baseTest.setImplicitWaitMillis(500);

        searchResult = interfacePage.searchAndVerify("Performance");
        Assert.assertTrue(searchResult, "The side Bar isn't work correctly - Performance");

        baseTest.setImplicitWaitMillis(500);

        searchResult = interfacePage.searchAndVerify("Directory");
        Assert.assertTrue(searchResult, "The side Bar isn't work correctly - Directory");

        baseTest.setImplicitWaitMillis(500);

        searchResult = interfacePage.searchAndVerify("Buzz");
        Assert.assertTrue(searchResult, "The side Bar isn't work correctly - Buzz");

        baseTest.setImplicitWaitMillis(500);

        searchResult = interfacePage.searchAndVerify("My Info");
        Assert.assertTrue(searchResult, "The side Bar isn't work correctly - My Info");

        baseTest.setImplicitWaitMillis(500);

        searchResult = interfacePage.searchAndVerify("Claim");
        Assert.assertTrue(searchResult, "The side Bar isn't work correctly - Claim");

        baseTest.setImplicitWaitMillis(500);

        searchResult = interfacePage.searchAndVerify("Maintenance");
        Assert.assertTrue(searchResult, "The side Bar isn't work correctly - Maintenance");
    }

    // Test case: Verify Functionality of About Button - Admin Role
    @Test(priority = 3, description = "Verify Functionality of About Button - Admin Role")
    public void verifyAboutButtonAdminFunctionality()
    {
        baseTest.login(AdminAccount, AdminPassword);

        // Wait for the error message to be visible (with a timeout of 10 seconds)
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(userDropDownButton) );

        interfacePage.clickUserDropDownButton();

        // Wait for the error message to be visible (with a timeout of 10 seconds)
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(AboutButton) );

        interfacePage.clickAboutButton();

        Assert.assertTrue(interfacePage.isAboutHeaderDisplayed(), "The About button is not working correctly");

        interfacePage.clickCloseAboutButton();
        Assert.assertFalse(interfacePage.isAboutHeaderDisplayed(), "The Close About button is not working correctly");

    }

    // Test case: Verify Functionality of About Button - ESS Role
    @Test(priority = 3, description = "Verify Functionality of About Button - ESS Role")
    public void verifyAboutButtonEssFunctionality()
    {
        baseTest.login(EssUsernameEnabled, EssPasswordEnabled);

        // Wait for the error message to be visible (with a timeout of 10 seconds)
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(userDropDownButton) );

        interfacePage.clickUserDropDownButton();

        // Wait for the error message to be visible (with a timeout of 10 seconds)
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(AboutButton) );

        interfacePage.clickAboutButton();

        Assert.assertTrue(interfacePage.isAboutHeaderDisplayed(), "The About button is not working correctly");
    }

    // Test case: Verify of Support Button - Admin Role
    @Test(priority = 3, description = "Verify of Support Button - Admin Role")
    public void verifySupportButtonAdminFunctionality()
    {
        baseTest.login(AdminAccount, AdminPassword);

        // Wait for the error message to be visible (with a timeout of 10 seconds)
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(userDropDownButton) );

        interfacePage.clickUserDropDownButton();

        // Wait for the error message to be visible (with a timeout of 10 seconds)
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(AboutButton) );

        interfacePage.clickSupportButton();

        String supportUrl = interfacePage.getCurrentPageUrl();

        Assert.assertTrue(supportUrl.contains("support"), "The support button is not working correctly");

    }

    // Test case: Verify of Support Button - ESS Role
    @Test(priority = 3, description = "Verify of Support Button - ESS Role")
    public void verifyAboutSupportEssFunctionality()
    {
        baseTest.login(EssUsernameEnabled, EssPasswordEnabled);

        // Wait for the error message to be visible (with a timeout of 10 seconds)
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(userDropDownButton) );

        interfacePage.clickUserDropDownButton();

        // Wait for the error message to be visible (with a timeout of 10 seconds)
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(AboutButton) );

        interfacePage.clickSupportButton();

        String supportUrl = interfacePage.getCurrentPageUrl();

        Assert.assertTrue(supportUrl.contains("support"), "The support button is not working correctly");
    }


    // TODO
    // Test case: Change Password - Successful Password Update - Admin Role
    @Test(priority = 3, description = "Change Password - Successful Password Update - Admin Role")
    public void changePasswordAdminRole()
    {
        baseTest.login(AdminUsernameEnabled, AdminPasswordEnabled);

        // Wait for the error message to be visible (with a timeout of 10 seconds)
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(userDropDownButton) );

        interfacePage.clickUserDropDownButton();

        // Wait for the error message to be visible (with a timeout of 10 seconds)
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(AboutButton) );

        interfacePage.clickChangePasswordButton();

        interfacePage.setNewPassword(AdminPasswordEnabled, newPassword, newPassword);

        Assert.assertTrue(interfacePage.isPasswordChanged(), "The password is not changed correctly");

        AdminPasswordEnabled = newPassword;
    }


    // TODO
    // Test case: Change Password - Successful Password Update - ESS Role
    @Test(priority = 3, description = "Change Password - Successful Password Update - ESS Role")
    public void changePasswordEssRole()
    {
        baseTest.login(EssUsernameEnabled, EssPasswordEnabled);

        // Wait for the error message to be visible (with a timeout of 10 seconds)
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(userDropDownButton) );

        interfacePage.clickUserDropDownButton();

        // Wait for the error message to be visible (with a timeout of 10 seconds)
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(AboutButton) );

        interfacePage.clickChangePasswordButton();

        baseTest.setImplicitWaitMillis(500);

        interfacePage.setNewPassword(EssPasswordEnabled, newPassword, newPassword);

        // Wait for the error message to be visible (with a timeout of 10 seconds)
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessageChangePassword) );

        Assert.assertTrue(interfacePage.isPasswordChanged(), "The password is not changed correctly");
    }

    // TODO
    // Test case: Change Password - Mismatch Between Password and Confirm Password

    // TODO
    // Test case: Change Password - Validate Password Length at Change Password

    // TODO
    // Test case: Change Password - Validate Presence of Lower-case Letter

    // TODO
    // Test case: Change Password - Validate Current Password (Incorrect Password)

    // TODO
    // Test case: Change Password - Verify Functionality of Change Password Button




    // After each test, quit the browser
    @AfterMethod
    public void tearDown()
    {
        if (driver != null) {
            driver.quit();
        }
    }
}

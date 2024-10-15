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
import pages.InterfacePage;

import java.time.Duration;

public class InterfaceTests
{
    WebDriver driver;
    InterfacePage interfacePage;

    // Locators
    By userDropDownButton = By.xpath("//img[@class='oxd-userdropdown-img']");
    By AboutButton = By.xpath("//ul[@class=\"oxd-dropdown-menu\"]//li[1]");


    // Setup method to initialize WebDriver and open the login page
    @BeforeMethod
    public void setUp()
    {
        // Initialize ChromeDriver (Selenium 4 manages drivers automatically)
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));

        // Initialize the LoginPage object
        interfacePage = new InterfacePage(driver);
    }

    // Test case: LVerity Functionality of Profile Icon
    @Test(priority = 1, description = "Verity Functionality of Profile Icon")
    public void verifyFunctionalityOfProfileIcon()
    {
        interfacePage.login("Admin", "admin123");

        // Wait for the error message to be visible (with a timeout of 10 seconds)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(userDropDownButton));

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
        String originalWindow = driver.getWindowHandle();

        interfacePage.login("Admin", "admin123");

        // Wait for the error message to be visible (with a timeout of 10 seconds)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(userDropDownButton));

        interfacePage.clickUpgradeButton();

        String upgradeUrl = interfacePage.getCurrentPageUrl();

        Assert.assertTrue(upgradeUrl.contains("orangehrm.com"), "The page URL is incorrect!");

        interfacePage.switchBackToOriginalWindow(originalWindow);
    }

    // Test case: Verify Functionality of Side Bar Button
    @Test(priority = 3, description = "Verify Functionality of Side Bar Button")
    public void verifySideBarButtonFunctionality()
    {
        String originalWindow = driver.getWindowHandle();

        interfacePage.login("Admin", "admin123");

        // Wait for the error message to be visible (with a timeout of 10 seconds)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(userDropDownButton));

        interfacePage.clickUpgradeButton();

        String upgradeUrl = interfacePage.getCurrentPageUrl();

        Assert.assertTrue(upgradeUrl.contains("orangehrm.com"), "The page URL is incorrect!");

        interfacePage.switchBackToOriginalWindow(originalWindow);
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

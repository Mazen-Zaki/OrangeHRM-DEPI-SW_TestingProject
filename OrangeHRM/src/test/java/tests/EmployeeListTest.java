package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import pages.AddEmployeePage;
import pages.LoginPage;
import pages.PIMPage;
import pages.EmployeeListPage;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class EmployeeListTest extends BaseTest {
    EmployeeListPage  EmployeeListPage;
    PIMPage pimPage;
    //public WebDriver driver;

    @BeforeMethod
    public void setUp() {
        super.setUp();
        EmployeeListPage=new EmployeeListPage(driver);
        pimPage = new PIMPage(driver);
        login(AdminAccount, AdminPassword);
    }

    @Test
    public void testSearchByName() {
        // Step 1: Login
        //LoginPage loginPage = new LoginPage(driver);

        login("Admin", "admin123");

        // Step 2: Navigate to PIM
        pimPage.navigateToPIM();
        pimPage.navigateToEmployeeTab();
        // Step 3: Search by name
        EmployeeListPage.searchByName("John Doe");

        // Verify that search results are displayed
        if (EmployeeListPage.isSearchResultDisplayed()) {
            System.out.println("Test Passed: Search by name successful.");
        } else {
            System.out.println("Test Failed: No results found for search by name.");
        }
    }
    @Test
    public void testSearchById() {
        // Step 1: Login and Navigate to PIM
       login("Admin", "admin123");
        pimPage.navigateToPIM();
        pimPage.navigateToEmployeeTab();
        EmployeeListPage.searchById("12345");

        // Verify that search results are displayed
        if (EmployeeListPage.isSearchResultDisplayed()) {
            System.out.println("Test Passed: Search by ID successful.");
        } else {
            System.out.println("Test Failed: No results found for search by ID.");
        }
    }
    @Test
    public void testSearchByNameAndId() {
        // Step 1: Login and Navigate to PIM
        login("Admin", "admin123");
        pimPage.navigateToPIM();
        pimPage.navigateToEmployeeTab();
        EmployeeListPage.searchByNameAndId("John Doe", "12345");

        // Verify that search results are displayed
        if (EmployeeListPage.isSearchResultDisplayed()) {
            System.out.println("Test Passed: Search by name and ID successful.");
        } else {
            System.out.println("Test Failed: No results found for search by name and ID.");
        }
    }
    @Test
    public void testResetFields() {
        // Step 1: Login and Navigate to PIM
        login("Admin", "admin123");
        pimPage.navigateToPIM();
        pimPage.navigateToEmployeeTab();
        EmployeeListPage.resetFields();

        // Verify that fields are cleared (Check if the fields are empty)
        if (driver.findElement(By.xpath("//input[@placeholder='Type for hints...']")).getAttribute("value").isEmpty() &&
            driver.findElement(By.xpath("//label[text()='Employee Id']/following::input[1]")).getAttribute("value").isEmpty()) {
            System.out.println("Test Passed: Reset functionality successful.");
        } else {
            System.out.println("Test Failed: Reset functionality did not clear the fields.");
        }
    }
}

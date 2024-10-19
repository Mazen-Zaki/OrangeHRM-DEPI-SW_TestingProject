package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.TimePage;
import pages.PIMPage;
import pages.ViewEmployeeListPage;

public class ViewEmployeeListTest extends BaseTest2 {

    @Test
    public void testSearchEmployee() {
        // Step 1: Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");

        // Step 2: Navigate to PIM
        PIMPage pimPage = new PIMPage(driver);
        pimPage.navigateToPIM();

        // Step 3: Search for an employee
        ViewEmployeeListPage employeeListPage = new ViewEmployeeListPage(driver);
        employeeListPage.searchEmployee("John Doe");

        // Verify that the employee is displayed in search results
        Assert.assertTrue("Employee was not found in search results.", employeeListPage.isSuccessMessageDisplayed());
    }

    @Test
    public void testEditEmployeeDetails() {
        // Step 1: Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");

        // Step 2: Navigate to PIM
        PIMPage pimPage = new PIMPage(driver);
        pimPage.navigateToPIM();

        // Step 3: Search and edit employee details
        ViewEmployeeListPage employeeListPage = new ViewEmployeeListPage(driver);
        employeeListPage.searchEmployee("John Doe");
        employeeListPage.editEmployeeDetails("Johnny", "Doe");

        // Verify that the changes were saved successfully
        Assert.assertTrue("Edit operation failed; success message was not displayed.", employeeListPage.isSuccessMessageDisplayed());
    }

    @Test
    public void testDeleteEmployee() {
        // Step 1: Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");

        // Step 2: Navigate to PIM
        PIMPage pimPage = new PIMPage(driver);
        pimPage.navigateToPIM();

        // Step 3: Search and delete employee
        ViewEmployeeListPage employeeListPage = new ViewEmployeeListPage(driver);
        employeeListPage.searchEmployee("Johnny Doe");
        employeeListPage.deleteEmployee();

        // Verify that the deletion was successful
        Assert.assertTrue("Delete operation failed; success message was not displayed.", employeeListPage.isSuccessMessageDisplayed());
    }
}

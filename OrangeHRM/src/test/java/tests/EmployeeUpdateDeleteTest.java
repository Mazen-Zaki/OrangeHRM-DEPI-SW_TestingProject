package tests;

import base.BaseTest;
import org.testng.annotations.BeforeMethod;
import pages.*;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class EmployeeUpdateDeleteTest extends BaseTest {

    PIMPage pimPage ;

    @BeforeMethod
    public void setUp() {
        super.setUp();
        pimPage = new PIMPage(driver);
        login(AdminAccount, AdminPassword);
    }
    @Test
    public void testUpdateEmployee() {
        // Step 1: Login
        login("Admin", "admin123");

        // Step 2: Navigate to PIM

        pimPage.navigateToPIM();

        // Step 3: Search and edit employee details
        EmployeeListPage employeeListPage = new EmployeeListPage(driver);
        //employeeListPage.searchEmployee("John Doe");
        employeeListPage.clickEditButton();

        // Step 4: Update details on the Personal Details page
        PersonalDetailsPage personalDetailsPage = new PersonalDetailsPage(driver);
        personalDetailsPage.updateDetails("Johnny", "Doe Updated");
        personalDetailsPage.saveChanges();

        // Verify that the update was successful
        if (personalDetailsPage.isUpdateSuccessful()) {
            System.out.println("Test Passed: Employee details updated successfully.");
        } else {
            System.out.println("Test Failed: Employee details update failed.");
        }
    }
    @Test
    public void testDeleteEmployee() {
        // Step 1: Login
        login("Admin", "admin123");

        // Step 2: Navigate to PIM
        //PIMPage pimPage = new PIMPage(driver);
        pimPage.navigateToPIM();

        // Step 3: Search for the employee and delete
        EmployeeListPage employeeListPage = new EmployeeListPage(driver);
        //employeeListPage.searchEmployee("Johnny Doe Updated");
        employeeListPage.clickDeleteButton();

        // Confirm deletion (may need to handle a confirmation popup if any)
        driver.switchTo().alert().accept(); // Example of handling a JavaScript alert

        // Verify that the employee was deleted
        if (employeeListPage.isEmployeeDeleted("Johnny Doe Updated")) {
            System.out.println("Test Passed: Employee deleted successfully.");
        } else {
            System.out.println("Test Failed: Employee was not deleted.");
        }
    }
}

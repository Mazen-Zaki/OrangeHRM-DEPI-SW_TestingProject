package tests;

import base.BaseTest;
import pages.LoginPage;
import pages.PIMPage;
import pages.EmployeeListPage;

public class EmployeeListTest extends BaseTest {

    public static void main(String[] args) {
        EmployeeListTest test = new EmployeeListTest();
        test.setUp();

        try {
            test.testSearchByName();
            test.testSearchById();
            test.testSearchByNameAndId();
            test.testResetFields();
        } finally {
            test.tearDown();
        }
    }

    public void testSearchByName() {
        // Step 1: Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");

        // Step 2: Navigate to PIM
        PIMPage pimPage = new PIMPage(driver);
        pimPage.navigateToPIM();

        // Step 3: Search by name
        EmployeeListPage employeeListPage = new EmployeeListPage(driver);
        employeeListPage.searchByName("John Doe");

        // Verify that search results are displayed
        if (employeeListPage.isSearchResultDisplayed()) {
            System.out.println("Test Passed: Search by name successful.");
        } else {
            System.out.println("Test Failed: No results found for search by name.");
        }
    }

    public void testSearchById() {
        // Step 1: Login and Navigate to PIM
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");
        PIMPage pimPage = new PIMPage(driver);
        pimPage.navigateToPIM();

        // Step 3: Search by ID
        EmployeeListPage employeeListPage = new EmployeeListPage(driver);
        employeeListPage.searchById("12345");

        // Verify that search results are displayed
        if (employeeListPage.isSearchResultDisplayed()) {
            System.out.println("Test Passed: Search by ID successful.");
        } else {
            System.out.println("Test Failed: No results found for search by ID.");
        }
    }

    public void testSearchByNameAndId() {
        // Step 1: Login and Navigate to PIM
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");
        PIMPage pimPage = new PIMPage(driver);
        pimPage.navigateToPIM();

        // Step 3: Search by name and ID
        EmployeeListPage employeeListPage = new EmployeeListPage(driver);
        employeeListPage.searchByNameAndId("John Doe", "12345");

        // Verify that search results are displayed
        if (employeeListPage.isSearchResultDisplayed()) {
            System.out.println("Test Passed: Search by name and ID successful.");
        } else {
            System.out.println("Test Failed: No results found for search by name and ID.");
        }
    }

    public void testResetFields() {
        // Step 1: Login and Navigate to PIM
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");
        PIMPage pimPage = new PIMPage(driver);
        pimPage.navigateToPIM();

        // Step 3: Perform reset
        EmployeeListPage employeeListPage = new EmployeeListPage(driver);
        employeeListPage.resetFields();

        // Verify that fields are cleared (Check if the fields are empty)
        if (driver.findElement(By.xpath("//input[@placeholder='Type for hints...']")).getAttribute("value").isEmpty() &&
            driver.findElement(By.xpath("//label[text()='Employee Id']/following::input[1]")).getAttribute("value").isEmpty()) {
            System.out.println("Test Passed: Reset functionality successful.");
        } else {
            System.out.println("Test Failed: Reset functionality did not clear the fields.");
        }
    }
}

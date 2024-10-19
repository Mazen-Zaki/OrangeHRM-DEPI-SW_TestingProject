package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage2;
import pages.PIMPage;

public class AddEmployeeTest  extends BaseTest2 {
    private WebDriver driver;

    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/");
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void testAddNewEmployee() {
        // Step 1: Login
        LoginPage2 loginPage = new LoginPage2(driver);
        loginPage.login("Admin", "admin123");

        // Step 2: Navigate to PIM and add employee
        PIMPage pimPage = new PIMPage(driver);
        pimPage.navigateToPIM();
        pimPage.clickAddEmployee();
        pimPage.addEmployee("John", "Doe", "1234");

        // Verify that no error message is displayed after adding a new employee
        if (pimPage.isErrorMessageDisplayed()) {
            System.out.println("Error message is displayed, employee might not have been added successfully.");
        } else {
            System.out.println("Employee added successfully.");
        }
    }

    public void testAddEmployeeWithDuplicateId() {
        // Step 1: Login
        LoginPage2 loginPage = new LoginPage2(driver);
        loginPage.login("Admin", "admin123");

        // Step 2: Navigate to PIM and add an employee with a duplicate ID
        PIMPage pimPage = new PIMPage(driver);
        pimPage.navigateToPIM();
        pimPage.clickAddEmployee();
        pimPage.addEmployee("Jane", "Doe", "1234"); // Using the same ID as the previous test

        // Verify that an error message is displayed due to duplicate ID
        if (!pimPage.isErrorMessageDisplayed()) {
            System.out.println("Expected error message for duplicate ID is not displayed.");
        } else {
            System.out.println("Error message for duplicate ID displayed as expected.");
        }
    }

    public static void main(String[] args) {
        AddEmployeeTest test = new AddEmployeeTest();
        test.setUp();

        try {
            test.testAddNewEmployee();
            test.testAddEmployeeWithDuplicateId();
        } finally {
            test.tearDown();
        }
    }
}

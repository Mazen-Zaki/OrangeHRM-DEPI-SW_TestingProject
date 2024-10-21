package tests;

import base.BaseTest;
import pages.LoginPage;
import pages.PIMPage;
import pages.AddEmployeePage;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class EmployeeTest extends BaseTest {

    public static void main(String[] args) {
        EmployeeTest test = new EmployeeTest();
        test.setUp();

        try {
            @test
            test.testAddEmployee();
        } finally {
            test.tearDown();
        }
    }

    public void testAddEmployee() {
        // Step 1: Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");

        // Step 2: Navigate to PIM
        PIMPage pimPage = new PIMPage(driver);
        pimPage.navigateToPIM();

        // Step 3: Add a new employee
        AddEmployeePage addEmployeePage = new AddEmployeePage(driver);
        addEmployeePage.addEmployee("John", "Doe", "12345");

        // Step 4: Verify if the employee was added successfully
        if (addEmployeePage.isEmployeeAddedSuccessfully()) {
            System.out.println("Test Passed: Employee was added successfully.");
        } else {
            System.out.println("Test Failed: Employee was not added.");
        }
    }
}

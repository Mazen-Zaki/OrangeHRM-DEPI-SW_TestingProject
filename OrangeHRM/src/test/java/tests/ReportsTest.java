package tests;

import pages.LoginPage2;
import pages.PIMPage;
import pages.ReportsPage;

public class ReportsTest extends BaseTest2 {

    public static void main(String[] args) {
        ReportsTest test = new ReportsTest();
        test.setUp();
        test.runTests();
        test.tearDown();
    }

    public void runTests() {
        testAddReport();
        testEditReport();
        testDeleteReport();
    }

    public void testAddReport() {
        // Step 1: Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");

        // Step 2: Navigate to PIM and then to Reports
        PIMPage pimPage = new PIMPage(driver);
        pimPage.navigateToPIM();

        // Step 3: Add a new report
        ReportsPage reportsPage = new ReportsPage(driver);
        reportsPage.navigateToReports();
        reportsPage.addReport("Employee Details Report");

        // Verify that the report was added successfully
        if (reportsPage.isSuccessMessageDisplayed()) {
            System.out.println("Report added successfully.");
        } else {
            System.out.println("Failed to add report.");
        }
    }

    public void testEditReport() {
        // Step 1: Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");

        // Step 2: Navigate to PIM and then to Reports
        PIMPage pimPage = new PIMPage(driver);
        pimPage.navigateToPIM();

        // Step 3: Search and edit the report
        ReportsPage reportsPage = new ReportsPage(driver);
        reportsPage.navigateToReports();
        reportsPage.searchReport("Employee Details Report");
        reportsPage.editReport("Updated Employee Details Report");

        // Verify that the report was updated successfully
        if (reportsPage.isSuccessMessageDisplayed()) {
            System.out.println("Report edited successfully.");
        } else {
            System.out.println("Failed to edit report.");
        }
    }

    public void testDeleteReport() {
        // Step 1: Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");

        // Step 2: Navigate to PIM and then to Reports
        PIMPage pimPage = new PIMPage(driver);
        pimPage.navigateToPIM();

        // Step 3: Search and delete the report
        ReportsPage reportsPage = new ReportsPage(driver);
        reportsPage.navigateToReports();
        reportsPage.searchReport("Updated Employee Details Report");
        reportsPage.deleteReport();

        // Verify that the report was deleted successfully
        if (reportsPage.isSuccessMessageDisplayed()) {
            System.out.println("Report deleted successfully.");
        } else {
            System.out.println("Failed to delete report.");
        }
    }
}

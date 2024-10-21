package tests;

import base.BaseTest;
import pages.LoginPage;
import pages.PIMPage;
import pages.ReportsPage;

public class ReportsTest extends BaseTest {

    public static void main(String[] args) {
        ReportsTest test = new ReportsTest();
        test.setUp();

        try {
            test.testAddReport();
            test.testEditReport();
            test.testDeleteReport();
        } finally {
            test.tearDown();
        }
    }

    public void testAddReport() {
        // Step 1: Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");

        // Step 2: Navigate to PIM and then to Reports
        PIMPage pimPage = new PIMPage(driver);
        pimPage.navigateToPIM();
        pimPage.navigateToReports();

        // Step 3: Add a new report
        ReportsPage reportsPage = new ReportsPage(driver);
        reportsPage.addReport("Test Report");

        // Verify that the report is added
        if (reportsPage.isReportListed("Test Report")) {
            System.out.println("Test Passed: Report added successfully.");
        } else {
            System.out.println("Test Failed: Report was not added.");
        }
    }

    public void testEditReport() {
        // Step 1: Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");

        // Step 2: Navigate to PIM and then to Reports
        PIMPage pimPage = new PIMPage(driver);
        pimPage.navigateToPIM();
        pimPage.navigateToReports();

        // Step 3: Edit the existing report
        ReportsPage reportsPage = new ReportsPage(driver);
        reportsPage.editReport("Updated Test Report");

        // Verify that the report is edited
        if (reportsPage.isReportListed("Updated Test Report")) {
            System.out.println("Test Passed: Report edited successfully.");
        } else {
            System.out.println("Test Failed: Report edit failed.");
        }
    }

    public void testDeleteReport() {
        // Step 1: Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");

        // Step 2: Navigate to PIM and then to Reports
        PIMPage pimPage = new PIMPage(driver);
        pimPage.navigateToPIM();
        pimPage.navigateToReports();

        // Step 3: Delete the report
        ReportsPage reportsPage = new ReportsPage(driver);
        reportsPage.deleteReport();

        // Verify that the report is deleted
        if (!reportsPage.isReportListed("Updated Test Report")) {
            System.out.println("Test Passed: Report deleted successfully.");
        } else {
            System.out.println("Test Failed: Report deletion failed.");
        }
    }
}

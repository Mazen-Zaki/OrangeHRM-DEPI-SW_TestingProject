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
            test.testSearchReport();
            test.testResetButton();
        } finally {
            test.tearDown();
        }
    }

    public void testAddReport() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");

        PIMPage pimPage = new PIMPage(driver);
        pimPage.navigateToPIM();
        pimPage.navigateToReports();

        ReportsPage reportsPage = new ReportsPage(driver);
        reportsPage.addNewReport("Sample Report");

        if (reportsPage.isReportPresent("Sample Report")) {
            System.out.println("Test Passed: Report added successfully.");
        } else {
            System.out.println("Test Failed: Report not added.");
        }
    }

    public void testEditReport() {
        ReportsPage reportsPage = new ReportsPage(driver);
        reportsPage.editReport("Updated Report");

        if (reportsPage.isReportPresent("Updated Report")) {
            System.out.println("Test Passed: Report updated successfully.");
        } else {
            System.out.println("Test Failed: Report not updated.");
        }
    }

    public void testDeleteReport() {
        ReportsPage reportsPage = new ReportsPage(driver);
        reportsPage.deleteReport();

        if (!reportsPage.isReportPresent("Updated Report")) {
            System.out.println("Test Passed: Report deleted successfully.");
        } else {
            System.out.println("Test Failed: Report not deleted.");
        }
    }

    public void testSearchReport() {
        ReportsPage reportsPage = new ReportsPage
    }
}
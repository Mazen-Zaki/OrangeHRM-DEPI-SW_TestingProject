package tests;

import base.BaseTest;
import pages.LoginPage;
import pages.PIMPage;
import pages.ReportsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ReportsTest extends BaseTest {

    public static void main(String[] args) {
        ReportsTest test = new ReportsTest();
        test.setUp();

        try {
            @test
            test.testAddReport();
            @test
            test.testEditReport();
            @test
            test.testDeleteReport();
            @test
            test.testSearchReport();
            @test
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

        Assert.assertTrue("Test Failed: Report not added.", reportsPage.isReportPresent("Sample Report"));
         System.out.println("Test Passed: Report added successfully.");
       /* if (reportsPage.isReportPresent("Sample Report")) {
            System.out.println("Test Passed: Report added successfully.");
        } else {
            System.out.println("Test Failed: Report not added.");
        }*/
    }

    public void testEditReport() {
        ReportsPage reportsPage = new ReportsPage(driver);
        reportsPage.editReport("Updated Report");

        Assert.assertTrue("Test Failed: Report not updated.", reportsPage.isReportPresent("Updated Report"));
        System.out.println("Test Passed: Report updated successfully.");

        /*if (reportsPage.isReportPresent("Updated Report")) {
            System.out.println("Test Passed: Report updated successfully.");
        } else {
            System.out.println("Test Failed: Report not updated.");
        }*/
    }

    public void testDeleteReport() {
        ReportsPage reportsPage = new ReportsPage(driver);
        reportsPage.deleteReport();

        Assert.assertFalse("Test Failed: Report not deleted.", reportsPage.isReportPresent("Updated Report"));
        System.out.println("Test Passed: Report deleted successfully."); 

        /*if (!reportsPage.isReportPresent("Updated Report")) {
            System.out.println("Test Passed: Report deleted successfully.");
        } else {
            System.out.println("Test Failed: Report not deleted.");
        }*/
    }

    public void testSearchReport() {
                ReportsPage reportsPage = new ReportsPage(driver);

       reportsPage.searchReport("Sample Report");

        Assert.assertTrue("Test Failed: Report not found in search results.", reportsPage.isReportPresent("Sample Report"));
        System.out.println("Test Passed: Report found in search results.");
    }

    public void testResetButton() {
        ReportsPage reportsPage = new ReportsPage(driver);
        reportsPage.searchReport("Sample Report");
        reportsPage.clickResetButton();

        // Verify that the search field is cleared (assume it becomes empty or default state).
        String searchFieldText = driver.findElement(By.xpath("//input[@placeholder='Search...']")).getAttribute("value");
        Assert.assertTrue("Test Failed: Reset button did not clear the search fields.", searchFieldText.isEmpty());
        System.out.println("Test Passed: Reset button cleared the search fields.");
    }
}
package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.PIMPage;
import pages.ReportsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ReportsTest extends BaseTest {
    PIMPage pimPage = new PIMPage(driver);;
    ReportsPage reportsPage;
    @BeforeMethod
    public void setUp() {
        super.setUp();
        pimPage = new PIMPage(driver);
        ReportsPage reportsPage=new ReportsPage(driver);
        login(AdminAccount, AdminPassword);
    }
    @Test
    public void testAddReport() {
        //LoginPage loginPage = new LoginPage(driver);
        login("Admin", "admin123");
        pimPage.navigateToPIM();
        pimPage.navigateToReports();

        ReportsPage reportsPage = new ReportsPage(driver);
        reportsPage.addNewReport("Sample Report");

        Assert.assertTrue(reportsPage.isReportPresent("Sample Report"), "Test Failed: Report not added.");
         System.out.println("Test Passed: Report added successfully.");
       /* if (reportsPage.isReportPresent("Sample Report")) {
            System.out.println("Test Passed: Report added successfully.");
        } else {
            System.out.println("Test Failed: Report not added.");
        }*/
    }
    @Test
    public void testEditReport() {
        reportsPage.editReport("Updated Report");

        Assert.assertTrue(reportsPage.isReportPresent("Updated Report"), "Test Failed: Report not updated.");
        System.out.println("Test Passed: Report updated successfully.");

        /*if (reportsPage.isReportPresent("Updated Report")) {
            System.out.println("Test Passed: Report updated successfully.");
        } else {
            System.out.println("Test Failed: Report not updated.");
        }*/
    }
    @Test
    public void testDeleteReport() {
        reportsPage.deleteReport();
        Assert.assertFalse(reportsPage.isReportPresent("Updated Report"), "Test Failed: Report not deleted.");
        System.out.println("Test Passed: Report deleted successfully."); 

        /*if (!reportsPage.isReportPresent("Updated Report")) {
            System.out.println("Test Passed: Report deleted successfully.");
        } else {
            System.out.println("Test Failed: Report not deleted.");
        }*/
    }
    @Test
    public void testSearchReport() {

       reportsPage.searchReport("Sample Report");

        Assert.assertTrue(reportsPage.isReportPresent("Sample Report"), "Test Failed: Report not found in search results.");
        System.out.println("Test Passed: Report found in search results.");
    }
    @Test
    public void testResetButton() {
        reportsPage.searchReport("Sample Report");
        reportsPage.clickResetButton();

        // Verify that the search field is cleared (assume it becomes empty or default state).
        String searchFieldText = driver.findElement(By.xpath("//input[@placeholder='Search...']")).getAttribute("value");
        Assert.assertTrue(searchFieldText.isEmpty(), "Test Failed: Reset button did not clear the search fields.");
        System.out.println("Test Passed: Reset button cleared the search fields.");
    }
}
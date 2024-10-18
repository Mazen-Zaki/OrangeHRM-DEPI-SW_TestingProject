package tests;
import base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;

public class PIMTest extends BaseTest {
    WebDriver driver;
    BaseTest basetest;
    PIMPage pimPage;
    ConfigurationPage configurationPage;
    ReportsPage reportsPage;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe"); // Example path for Windows
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(baseUrl);

       basetest= new BaseTest(driver);
        pimPage = new PIMPage(driver);
        configurationPage = new ConfigurationPage(driver);
        reportsPage = new ReportsPage(driver);

        basetest.login("Admin", "admin123");
    }

    // PIM Tests
    @Test
    public void testAddEmployeeHappyScenario() {
        pimPage.navigateToAddEmployee();
        pimPage.addEmployee("John", "Doe", "12345");

        // Verify the employee was added
        Assert.assertTrue(driver.getPageSource().contains("John Doe"), "Employee was not added successfully");
    }

    @Test
    public void testAddEmployeeWorstScenario() {
        pimPage.navigateToAddEmployee();
        pimPage.addEmployee("", "", "");

        // Verify the error message is displayed
        Assert.assertTrue(driver.getPageSource().contains("Required"), "Error message was not displayed for missing mandatory fields");
    }

    @Test
    public void testDeleteEmployeeHappyScenario() {
        pimPage.searchEmployee("John Doe");
        pimPage.deleteEmployee();

        // Verify the employee was deleted
        Assert.assertFalse(driver.getPageSource().contains("John Doe"), "Employee was not deleted successfully");
    }

    @Test
    public void testDeleteEmployeeWorstScenario() {
        pimPage.searchEmployee("Nonexistent Employee");
        pimPage.deleteEmployee();

        // Verify the error message is displayed
        Assert.assertTrue(driver.getPageSource().contains("No Records Found"), "Error message was not displayed for non-existent employee");
    }

    @Test
    public void testEditEmployeeHappyScenario() {
        pimPage.searchEmployee("John Doe");
        pimPage.editEmployee("Johnny", "Doe");

        // Verify the employee details were updated
        Assert.assertTrue(driver.getPageSource().contains("Johnny Doe"), "Employee details were not updated successfully");
    }

    @Test
    public void testEditEmployeeWorstScenario() {
        pimPage.searchEmployee("John Doe");
        pimPage.editEmployee("", "");

        // Verify the error message is displayed
        Assert.assertTrue(driver.getPageSource().contains("Required"), "Error message was not displayed for missing mandatory fields");
    }

    // Configuration Tests
    @Test
    public void testEnableOptionalFieldsHappyScenario() {
        configurationPage.navigateToOptionalFields();
        configurationPage.enableOptionalFields();

        // Verify the optional fields were enabled
        Assert.assertTrue(driver.findElement(By.id("configPim_chkShowSSN")).isSelected(), "Optional fields were not enabled");
    }

    @Test
    public void testAddCustomFieldHappyScenario() {
        configurationPage.navigateToCustomFields();
        configurationPage.addCustomField("Custom Field 1", "Text");

        // Verify the custom field was added
        Assert.assertTrue(driver.getPageSource().contains("Custom Field 1"), "Custom field was not added successfully");
    }

    @Test
    public void testAddCustomFieldWorstScenario() {
        configurationPage.navigateToCustomFields();
        configurationPage.addCustomField("", "");

        // Verify the error message is displayed
        Assert.assertTrue(driver.getPageSource().contains("Required"), "Error message was not displayed for missing mandatory fields");
    }

    @Test
    public void testUploadCsvFileHappyScenario() {
        configurationPage.navigateToDataImport();
        configurationPage.uploadCsvFile("C:\\path\\to\\your\\csvfile.csv");

        // Verify the file was uploaded
        Assert.assertTrue(driver.getPageSource().contains("Successfully Uploaded"), "CSV file was not uploaded successfully");
    }

    @Test
    public void testUploadCsvFileWorstScenario() {
        configurationPage.navigateToDataImport();
        configurationPage.uploadCsvFile("C:\\path\\to\\nonexistentfile.csv");

        // Verify the error message is displayed
        Assert.assertTrue(driver.getPageSource().contains("Failed to Upload"), "Error message was not displayed for invalid file upload");
    }

    // Reports Tests
    @Test
    public void testAddReportHappyScenario() {
        reportsPage.navigateToReports();
        reportsPage.addReport("Employee Report", "Employee Name");

        // Verify the report was added
        Assert.assertTrue(driver.getPageSource().contains("Employee Report"), "Report was not added successfully");
    }

    @Test
    public void testAddReportWorstScenario() {
        reportsPage.navigateToReports();
        reportsPage.addReport("", "");

        // Verify the error message is displayed
        Assert.assertTrue(driver.getPageSource().contains("Required"), "Error message was not displayed for missing mandatory fields");
    }

    @Test
    public void testDeleteReportHappyScenario() {
        reportsPage.navigateToReports();
        reportsPage.searchReport("Employee Report");
        reportsPage.deleteReport();

        // Verify the report was deleted
        Assert.assertFalse(driver.getPageSource().contains("Employee Report"), "Report was not deleted successfully");
    }

    @Test
    public void testDeleteReportWorstScenario() {
        reportsPage.navigateToReports();
        reportsPage.searchReport("Nonexistent Report");
        reportsPage.deleteReport();

        // Verify the error message is displayed
        Assert.assertTrue(driver.getPageSource().contains("No Records Found"), "Error message was not displayed for non-existent report");
    }

    @Test
    public void testEditReportHappyScenario() {
        reportsPage.navigateToReports();
        reportsPage.searchReport("Employee Report");
        reportsPage.addReport("Updated Employee Report", "Employee Name");

        // Verify the report details were updated
        Assert.assertTrue(driver.getPageSource().contains("Updated Employee Report"), "Report details were not updated successfully");
    }

    @Test
    public void testEditReportWorstScenario() {
        reportsPage.navigateToReports();
        reportsPage.searchReport("Employee Report");
        reportsPage.addReport("", "");

        // Verify the error message is displayed
        Assert.assertTrue(driver.getPageSource().contains("Required"), "Error message was not displayed for missing mandatory fields");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}


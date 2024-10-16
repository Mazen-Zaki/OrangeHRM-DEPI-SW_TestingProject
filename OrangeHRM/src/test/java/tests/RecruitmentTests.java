package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.RecruitmentPage;

import java.time.Duration;

public class RecruitmentTests {

    WebDriver driver;
    RecruitmentPage recruitmentPage;

    // Setup method to initialize WebDriver
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        recruitmentPage = new RecruitmentPage(driver);
    }

    // Test case: Verify adding a new candidate with valid data
    @Test(priority = 1, description = "Verify adding a new candidate with valid data")
    public void verifyAddNewCandidate() {
        // Login first
        driver.get("https://opensource-demo.orangehrmlive.com/");
        recruitmentPage.loginAsAdmin("Admin", "admin123");

        // Navigate to Add Candidate page
        recruitmentPage.navigateToAddCandidatePage();

        // Fill in the candidate form with valid data
        recruitmentPage.enterFirstName("Adham");
        recruitmentPage.enterMiddleName("M.");
        recruitmentPage.enterLastName("Medhat");
        recruitmentPage.enterEmail("adham@test.com");
        recruitmentPage.enterContactNumber("1234567890");
        recruitmentPage.uploadResume("C:\\Users\\PC\\Desktop\\depi\\OrangeHRM-DEPI-SW_TestingProject\\OrangeHRM\\Software Testing Project Guidelines - DEPI 1.pdf"); // Updated resume upload
        recruitmentPage.selectVacancy("Software Engineer"); // Select vacancy from dropdown
        recruitmentPage.enterKeywords("Selenium, Testing");
        recruitmentPage.enterDateOfApplication("2024-10-14"); // Updated date format
        recruitmentPage.enterNotes("Testing candidate addition");
        recruitmentPage.checkConsent();
        recruitmentPage.clickSave();

        // Assert success message is displayed
        Assert.assertTrue(recruitmentPage.isSuccessMessageDisplayed(), "Success message not displayed!");
    }

    // Test case: Verify adding a new candidate with missing required fields
    @Test(priority = 2, description = "Verify adding a new candidate with missing required fields")
    public void verifyAddCandidateWithMissingFields() {
        // Login first
        driver.get("https://opensource-demo.orangehrmlive.com/");
        recruitmentPage.loginAsAdmin("Admin", "admin123");

        // Navigate to Add Candidate page
        recruitmentPage.navigateToAddCandidatePage();

        // Leave some required fields empty
        recruitmentPage.enterFirstName("");
        recruitmentPage.enterEmail("");
        recruitmentPage.clickSave();

        // Assert that error messages for required fields are displayed
        Assert.assertTrue(recruitmentPage.isFirstNameErrorDisplayed(), "First name error message not displayed!");
        Assert.assertTrue(recruitmentPage.isEmailErrorDisplayed(), "Email error message not displayed!");
    }

    // Test case: Verify adding a candidate with invalid email format
    @Test(priority = 3, description = "Verify adding a candidate with invalid email format")
    public void verifyAddCandidateWithInvalidEmail() {
        // Login first
        driver.get("https://opensource-demo.orangehrmlive.com/");
        recruitmentPage.loginAsAdmin("Admin", "admin123");

        // Navigate to Add Candidate page
        recruitmentPage.navigateToAddCandidatePage();

        // Enter invalid email format
        recruitmentPage.enterFirstName("Adham");
        recruitmentPage.enterLastName("Medhat");
        recruitmentPage.enterEmail("invalid-email-format");
        recruitmentPage.enterContactNumber("1234567890");
        recruitmentPage.clickSave();

        // Assert that email format error message is displayed
        Assert.assertTrue(recruitmentPage.isEmailFormatErrorDisplayed(), "Expected format: admin@example.com");
    }

    // Test case: Verify adding a candidate without checking the consent checkbox
    @Test(priority = 4, description = "Verify adding a candidate without checking consent")
    public void verifyAddCandidateWithoutConsent() {
        // Login first
        driver.get("https://opensource-demo.orangehrmlive.com/");
        recruitmentPage.loginAsAdmin("Admin", "admin123");

        // Navigate to Add Candidate page
        recruitmentPage.navigateToAddCandidatePage();

        // Enter valid data but don't check consent checkbox
        recruitmentPage.enterFirstName("Adham");
        recruitmentPage.enterEmail("adham@test.com");
        recruitmentPage.enterContactNumber("1234567890");
        recruitmentPage.clickSave();

        // Assert that consent error message is displayed
        Assert.assertTrue(recruitmentPage.isFirstNameErrorDisplayed(), "Consent error message not displayed!");
    }

    // Test case: Verify resetting the form (Cancel functionality)
    @Test(priority = 5, description = "Verify resetting the form using Cancel button")
    public void verifyFormReset() {
        // Login first
        driver.get("https://opensource-demo.orangehrmlive.com/");
        recruitmentPage.loginAsAdmin("Admin", "admin123");

        // Navigate to Add Candidate page
        recruitmentPage.navigateToAddCandidatePage();

        // Enter some data and then click Cancel
        recruitmentPage.enterFirstName("Adham");
        recruitmentPage.enterEmail("adham@test.com");
        recruitmentPage.clickCancel();

        // Assert that the form is reset (all fields should be empty)
        Assert.assertTrue(recruitmentPage.isFormReset(), "Form was not reset!");
    }

    // After each test, quit the browser
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

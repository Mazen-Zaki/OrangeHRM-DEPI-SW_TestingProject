package tests;

import base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.RecruitmentPage;
import java.time.Duration;

public class RecruitmentTests extends BaseTest {
    WebDriver driver;
    RecruitmentPage recruitmentPage;
    BaseTest baseTest;

    //// Setup method to initialize WebDriver
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        recruitmentPage = new RecruitmentPage(driver);
        baseTest = new BaseTest(driver);
        driver.get(baseUrl);
        baseTest.login(AdminAccount, AdminPassword);
    }

    // Test case: Verify adding a new candidate with valid data
    @Test(priority = 1, description = "Verify adding a new candidate with valid data")
    public void verifyAddNewCandidate() {
        // Navigate to Add Candidate page
        recruitmentPage.navigateToAddCandidatePage();
        // Fill in the candidate form with valid data
        recruitmentPage.enterFirstName("Depi");
        recruitmentPage.enterMiddleName("Depi");
        recruitmentPage.enterLastName("Depi");
        recruitmentPage.enterEmail("Depi@test.com");
        recruitmentPage.enterContactNumber("1234567890");
        // Use dynamic relative path
        String filePath = projectPath + "/TestData/Software Testing Project Guidelines - DEPI 1.pdf";
        recruitmentPage.uploadResume(filePath);

        recruitmentPage.selectVacancy(); // Select vacancy from dropdown
        recruitmentPage.enterKeywords("Depi Selenium, Testing");
        recruitmentPage.enterDateOfApplication("2024-15-10");
        recruitmentPage.enterNotes("Testing candidate addition");
        recruitmentPage.checkConsent();
        recruitmentPage.clickSave();
        // Assert success message is displayed
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Assert.assertEquals("Depi Depi Depi", recruitmentPage.GetName(), "The username does not match!");
    }

    // Test case: Verify adding a new candidate with missing required fields
    @Test(priority = 2, description = "Verify adding a new candidate with missing required fields")
    public void verifyAddCandidateWithMissingFields() {
        // Navigate to Add Candidate page
        recruitmentPage.navigateToAddCandidatePage();
        // Leave some required fields empty
        recruitmentPage.enterFirstName("");
        recruitmentPage.enterLastName("");
        recruitmentPage.enterEmail("");
        recruitmentPage.clickSave();
        // Assert that error messages for required fields are displayed
        Assert.assertTrue(recruitmentPage.isFirstNameErrorDisplayed(), "First name error message not displayed!");
        Assert.assertTrue(recruitmentPage.isLastNameErrorDisplayed(), "Last name error message not displayed!");
        Assert.assertTrue(recruitmentPage.isEmailErrorDisplayed(), "Email error message not displayed!");

    }

    // Test case: Verify adding a candidate with invalid email format
    @Test(priority = 3, description = "Verify adding a candidate with invalid email format")
    public void verifyAddCandidateWithInvalidEmail() {
        // Navigate to Add Candidate page
        recruitmentPage.navigateToAddCandidatePage();
        // Enter invalid email format
        recruitmentPage.enterFirstName("Depi");
        recruitmentPage.enterLastName("Depi");
        recruitmentPage.enterEmail("invalid-email-format");
        recruitmentPage.enterContactNumber("1234567890");
        recruitmentPage.clickSave();
        // Assert that email format error message is displayed
        Assert.assertTrue(recruitmentPage.isEmailFormatErrorDisplayed(), "Expected format: admin@example.com");
    }

    // Test case: Verify adding a candidate without checking the consent checkbox
    @Test(priority = 4, description = "Verify adding a candidate without checking consent")
    public void verifyAddCandidateWithoutConsent() {
        // Navigate to Add Candidate page
        recruitmentPage.navigateToAddCandidatePage();
        // Enter valid data but don't check consent checkbox
        recruitmentPage.enterFirstName("Depi");
        recruitmentPage.enterMiddleName("Depi");
        recruitmentPage.enterLastName("Depi");
        recruitmentPage.enterEmail("Depi@test.com");
        recruitmentPage.enterContactNumber("1234567890");
        recruitmentPage.clickSave();
        // Assert that consent error message is displayed
        Assert.assertEquals("Depi Depi Depi", recruitmentPage.GetName(), "The username does not match!");
    }

    // Test case: Verify (Cancel functionality)
    @Test(priority = 5, description = "Verify Cancel button")
    public void verifyFormReset() {
        // Navigate to Add Candidate page
        recruitmentPage.navigateToAddCandidatePage();
        // Enter some data and then click Cancel
        recruitmentPage.enterFirstName("Depi");
        recruitmentPage.enterEmail("Depi@test.com");
        recruitmentPage.clickCancel();
        String actualUrl = driver.getCurrentUrl();
        // Expected URL
        String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/recruitment/viewCandidates";
        // Assert that the current URL matches the expected URL
        Assert.assertEquals(actualUrl, expectedUrl, "User is not redirected to the expected page!");
    }

    // Test case: Verify adding a new candidate with Wrong DOA
    @Test(priority = 6, description = "Verify adding a new candidate with Wrong DOA")
    public void verifyAddCandidateWithWrongDoa() {
        // Navigate to Add Candidate page
        recruitmentPage.navigateToAddCandidatePage();
        // Leave some required fields empty
        recruitmentPage.enterFirstName("Depi");
        recruitmentPage.enterLastName("Depi");
        recruitmentPage.enterEmail("Depi@test.com");
        recruitmentPage.enterDateOfApplication("2025-15-10");
        recruitmentPage.clickSave();
        // Assert that error messages for required fields are displayed
        Assert.assertTrue(recruitmentPage.WrongDoaErrorDisplayed(), "Wrong DOA Displayed!");

    }

    @Test(priority = 7, description = "Verify adding a new candidate with Wrong DOA")
    public void verifyAddCandidateWithWrongDoaFormat() {
        // Navigate to Add Candidate page
        recruitmentPage.navigateToAddCandidatePage();
        // Leave some required fields empty
        recruitmentPage.enterFirstName("Depi");
        recruitmentPage.enterLastName("Depi");
        recruitmentPage.enterEmail("Depi@test.com");
        recruitmentPage.enterDateOfApplication("202515/10");
        recruitmentPage.clickSave();
        // Assert that error messages for required fields are displayed
        Assert.assertTrue(recruitmentPage.WrongDoaFormatDisplayed(), "Wrong DOA Format Displayed!");

    }

    // Test case: Verify adding a new candidate with Exceeded File Size
    @Test(priority = 8, description = "Verify adding a new candidate Exceeded File Size")
    public void CandidateWithWrongFileSize() {
        // Navigate to Add Candidate page
        recruitmentPage.navigateToAddCandidatePage();
        // Fill in the candidate form with valid data
        recruitmentPage.enterFirstName("Depi");
        recruitmentPage.enterLastName("Depi");
        recruitmentPage.enterEmail("Depi@test.com");
        recruitmentPage.enterContactNumber("1234567890");

        // Use dynamic relative path
        String filePath = projectPath + "/TestData/1.5MB.pdf"; // Adjust the relative path as needed

        recruitmentPage.uploadResume(filePath);

        // Assert success message is displayed
        Assert.assertTrue(recruitmentPage.AttachmentSizeExceeded(), "Attachment Size Exceeded");
    }

    // Test case: Verify adding a new candidate with Wrong File Type
    @Test(priority = 9, description = "Verify adding a new candidate with Wrong File Type")
    public void CandidateWithWrongFileType() {
        // Navigate to Add Candidate page
        recruitmentPage.navigateToAddCandidatePage();
        // Fill in the candidate form with valid data
        recruitmentPage.enterFirstName("Depi");
        recruitmentPage.enterLastName("Depi");
        recruitmentPage.enterEmail("Depi@test.com");
        recruitmentPage.enterContactNumber("1234567890");
        // Use dynamic relative path
        String filePath = projectPath + "/TestData/images.png";
        recruitmentPage.uploadResume(filePath);

        //// Assert success message is displayed
        Assert.assertTrue(recruitmentPage.WrongFileType(), "Wrong File Type");
    }

    // @Test(priority = 10, description = "Verify adding a new vacancy")
    // public void AddNewVacancy() {
        
    //     recruitmentPage.navigateToAddJobVacancy();
    //     recruitmentPage.VacancyName("DepiAdmin");
    //     recruitmentPage.JobTitle(); 
    //     recruitmentPage.AddHiringManager("Peter Mac Anderson");
    //     recruitmentPage.clickSave();
        
    // }

    // After each test, quit the browser
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

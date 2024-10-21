package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.AddEmployeePage;
import pages.LoginPaage;

import static org.junit.jupiter.api.Assertions.assertTrue;
//import io.github.bonigarcia.wdm.WebDriverManager;

public class AddEmployeeTest {
    private WebDriver driver;
    private AddEmployeePage addEmployeePage;
    private LoginPaage loginPaage;

    // Declare instance variables for user input
    private String firstName;
    private String middleName;
    private String lastName;
    private String employeeId;
    private String username;
    private String password;

    @BeforeEach
    public void setUp() {
        // Automatically downloads and sets up the correct ChromeDriver
        driver = new ChromeDriver();
        loginPaage = new LoginPaage(driver);

        addEmployeePage = new AddEmployeePage(driver);
        loginPaage.open();
        loginPaage.enterUsername("Admin");
        loginPaage.enterPassword("admin123");
        loginPaage.clickLogin();
        // Simulating user input (these can be modified as per your requirement)
        firstName = "Shahd";          // User input for first name
        middleName = "hatim";           // User input for middle name
        lastName = "h";            // User input for last name
        employeeId = "123";        // User input for employee ID
        username = "Shahdhatim";       // User input for username
        password = "Password123";     // User input for password
    }

    @Test
    public void testAddEmployeeWithAllValidData() {

        addEmployeePage.navigatetoadd();

        addEmployeePage.enterFirstName(firstName);
        addEmployeePage.enterMiddleName(middleName);
        addEmployeePage.enterLastName(lastName);
        addEmployeePage.enterEmployeeId(employeeId);
        addEmployeePage.uploadImage("../../../image/1.jpg"); // Replace with a valid image path
        addEmployeePage.checkCreateLoginDetails();
        addEmployeePage.enterUsername(username);
        addEmployeePage.enterPassword(password);
        addEmployeePage.enterConfirmPassword(password);
        addEmployeePage.clickSave();

        assertTrue(addEmployeePage.isEmployeeAdded(), "Employee was not added successfully.");
    }

    @Test
    public void testAddEmployeeWithMissingFirstName() {
        addEmployeePage.navigatetoadd();
        addEmployeePage.enterMiddleName(middleName);
        addEmployeePage.enterLastName(lastName);
        addEmployeePage.enterEmployeeId(employeeId);
        addEmployeePage.clickSave();

        String errorMessage = addEmployeePage.getErrorMessage();
        assertTrue(errorMessage.contains("Required"), "Expected first name required message.");
    }

    @Test
    public void testAddEmployeeWithMissingLastName() {
        addEmployeePage.navigatetoadd();
        addEmployeePage.enterFirstName(firstName);
        addEmployeePage.enterMiddleName(middleName);
        addEmployeePage.enterEmployeeId(employeeId);
        addEmployeePage.clickSave();

        String errorMessage = addEmployeePage.getErrorMessage();
        assertTrue(errorMessage.contains("Required"), "Expected last name required message.");
    }

    @Test
    public void testAddEmployeeWithMissingEmployeeId() {
        addEmployeePage.navigatetoadd();
        addEmployeePage.clearEmployeeIdField();

        addEmployeePage.enterFirstName(firstName);
        addEmployeePage.enterMiddleName(middleName);
        addEmployeePage.enterLastName(lastName);
        addEmployeePage.clickSave();
        addEmployeePage.clearEmployeeIdFieldPage();
        String errorMessage = addEmployeePage.getErrorMessage();
        assertTrue(errorMessage.contains("Required"), "Expected employee ID required message.");
    }

    @Test
    public void testAddEmployeeWithMissingLoginDetails() {
        addEmployeePage.navigatetoadd();
        addEmployeePage.enterFirstName(firstName);
        addEmployeePage.enterLastName(lastName);
        addEmployeePage.enterEmployeeId(employeeId);
        addEmployeePage.checkCreateLoginDetails(); // Check checkbox
        addEmployeePage.clickSave();

        String errorMessage = addEmployeePage.getErrorMessage();
        assertTrue(errorMessage.contains("Required"), "Expected username required message.");
    }

    @Test
    public void testAddEmployeeWithInvalidImageUpload() {
        addEmployeePage.navigatetoadd();
        addEmployeePage.enterFirstName(firstName);
        addEmployeePage.enterMiddleName(middleName);
        addEmployeePage.enterLastName(lastName);
        addEmployeePage.enterEmployeeId(employeeId);
        addEmployeePage.uploadImage("C:\\Users\\Admin\\IdeaProjects\\FINAL_PROJECT\\image\\1.jpg");
        //  addEmployeePage.clickSave();

        // String errorMessage = addEmployeePage.getErrorMessage();
        //  assertTrue(errorMessage.contains("Invalid file type"), "Expected invalid file type message.");
    }

    @AfterEach
    public void tearDown() {
        // Close browser after each test
        if (driver != null) {
            driver.quit();
        }
    }
}
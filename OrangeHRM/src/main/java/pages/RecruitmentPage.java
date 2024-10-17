package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RecruitmentPage {
    private WebDriver driver;

    // Locators
    @FindBy(id = "addJobVacancy_name")
    WebElement jobVacancyName;

    @FindBy(id = "addJobVacancy_hiringManager")
    WebElement hiringManager;

    @FindBy(name = "dateOfBirth")
    WebElement dateOfBirth; // Added date of birth field

    // Constructor
    public RecruitmentPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Method to login as Admin
    public void loginAsAdmin(String username, String password) {
        WebElement usernameField = driver.findElement(By.name("username"));
        usernameField.clear();
        usernameField.sendKeys(username);
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.clear();
        passwordField.sendKeys(password);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    // Method to navigate to the Add Candidate page
    public void navigateToAddCandidatePage() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/recruitment/addCandidate");
    }

    // Method to enter the first name
    public void enterFirstName(String firstName) {
        WebElement firstNameField = driver.findElement(By.name("firstName"));
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }

    // Method to enter the middle name
    public void enterMiddleName(String middleName) {
        WebElement middleNameField = driver.findElement(By.name("middleName"));
        middleNameField.clear();
        middleNameField.sendKeys(middleName);
    }

    // Method to enter the last name
    public void enterLastName(String lastName) {
        WebElement lastNameField = driver.findElement(By.name("lastName"));
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    // Method to enter the email
    public void enterEmail(String email) {
        WebElement emailField = driver.findElement(By.xpath("//input[@placeholder='Type here']"));
        emailField.clear();
        emailField.sendKeys(email);
    }

    // Method to enter the contact number
    public void enterContactNumber(String contactNumber) {
        WebElement contactNumberField = driver.findElement(
                By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div[2]/div/div[2]/input"));
        contactNumberField.clear();
        contactNumberField.sendKeys(contactNumber);
    }

    // Method to upload a resume
    public void uploadResume(String resumePath) {
        WebElement resumeUploadInput = driver.findElement(By.xpath("//input[@type='file']"));
        resumeUploadInput.sendKeys(resumePath); // Directly upload the file
    }

    // Method to enter keywords
    public void enterKeywords(String keywords) {
        WebElement keywordsField = driver
                .findElement(By.xpath("//input[@placeholder='Enter comma seperated words...']"));
        // keywordsField.clear();
        keywordsField.sendKeys(keywords);
    }

    // Method to enter the date of application
    public void enterDateOfApplication(String date) {
        WebElement dateField = driver.findElement(By.xpath("//input[@placeholder='yyyy-dd-mm']"));
        dateField.sendKeys(Keys.CONTROL + "a");
        dateField.sendKeys(Keys.DELETE);
        dateField.sendKeys(date); // Ensure the format is "yyyy-mm-dd"
    }

    // Method to enter notes
    public void enterNotes(String notes) {
        WebElement notesField = driver.findElement(By.xpath("//textarea[@placeholder='Type here']"));
        notesField.clear();
        notesField.sendKeys(notes);
    }

    // Method to check consent
    public void checkConsent() {
        WebElement consentCheckbox = driver
                .findElement(By.xpath("//i[@class=\"oxd-icon bi-check oxd-checkbox-input-icon\"]"));
        consentCheckbox.click();

    }

    // Method to select a vacancy from the dropdown
    public void selectVacancy() {
        WebElement dropdownbutton = driver
                .findElement(By.xpath("//i[@class=\"oxd-icon bi-caret-down-fill oxd-select-text--arrow\"]"));
        dropdownbutton.click();
        dropdownbutton = driver.findElement(By
                .xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div/div/div[2]/div/div[2]/div[2]"));
        dropdownbutton.click();

    }

    // Method to enter date of birth
    public void enterDateOfBirth(String dob) {
        dateOfBirth.clear();
        dateOfBirth.sendKeys(dob); // Ensure format is "yyyy-mm-dd"
    }

    // Method to click the Save button
    public void clickSave() {
        driver.findElement(By
                .xpath("//button[@class=\"oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space\"]"))
                .click();
    }

    // Method to click the Cancel button
    public void clickCancel() {
        driver.findElement(By.xpath("//button[@class=\"oxd-button oxd-button--medium oxd-button--ghost\"]")).click();
    }

    // Method to check if success message is displayed
    public boolean isSuccessMessageDisplayed() {
        return driver.findElements(By.xpath("//div[@class='success-message']")).size() > 0;
    }

    public String GetName() {
        WebElement NameField = driver
                .findElement(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div[1]/form/div[1]/div[1]/div/div[2]/p"));
        return NameField.getText();
    }

    // Method to check if first name error message is displayed
    public boolean isFirstNameErrorDisplayed() {
        return driver
                .findElements(By.xpath(
                        "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div/div/div[2]/div[1]/span"))
                .size() > 0;
    }

    public boolean isLastNameErrorDisplayed() {
        return driver
                .findElements(By.xpath(
                        "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div/div/div[2]/div[3]/span"))
                .size() > 0;
    }

    // Method to check if email error message is displayed
    public boolean isEmailErrorDisplayed() {
        return driver
                .findElements(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div[1]/div/span"))
                .size() > 0;
    }

    // Method to check if email format error message is displayed
    public boolean isEmailFormatErrorDisplayed() {
        return driver
                .findElements(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div[1]/div/span"))
                .size() > 0;
    }

    // Method to check if form is reset
    // public boolean isFormReset() {
    // return
    // driver.findElement(By.name("firstName")).getAttribute("value").isEmpty() &&
    // driver.findElement(By.xpath("//button[@class=\"oxd-button oxd-button--medium
    // oxd-button--ghost\"]]")).getAttribute("value").isEmpty();
    // }
}

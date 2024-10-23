package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class RecruitmentPage {
    private WebDriver driver;

    // Define locators
    By firstNameField = By.name("firstName");
    By middleNameField = By.name("middleName");
    By lastNameField = By.name("lastName");
    By emailField = By.xpath("//input[@placeholder='Type here']");
    By contactNumberField = By
            .xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div[2]/div/div[2]/input");
    By resumeUploadInput = By.xpath("//input[@type='file']");
    By keywordsField = By.xpath("//input[@placeholder='Enter comma seperated words...']");
    By dateField = By.xpath("//input[@placeholder='yyyy-dd-mm']");
    By notesField = By.xpath("//textarea[@placeholder='Type here']");
    By consentCheckbox = By.xpath("//i[@class=\"oxd-icon bi-check oxd-checkbox-input-icon\"]");
    By vacancyDropdownButton = By.xpath("//i[@class=\"oxd-icon bi-caret-down-fill oxd-select-text--arrow\"]");
    By saveButton = By
            .xpath("//button[@class=\"oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space\"]");
    By cancelButton = By.xpath("//button[@class=\"oxd-button oxd-button--medium oxd-button--ghost\"]");
    By successMessage = By.xpath("//div[@class='success-message']");
    By firstNameError = By
            .xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div/div/div[2]/div[1]/span");
    By lastNameError = By
            .xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div/div/div[2]/div[3]/span");
    By emailError = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div[1]/div/span");
    By emailFormatError = By.xpath("//span[contains(normalize-space(.), 'Expected format: admin@example.com')]");
    By wrongDoaError = By
            .xpath("//span[@class=\"oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message\"]");
    By wrongDoaFormat = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[5]/div/div[2]/div/span");
    By attachmentSizeExceeded = By
            .xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[4]/div/div/div/div/span");
    By wrongFileType = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[4]/div/div/div/div/span");

    By nameField = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div[1]/form/div[1]/div[1]/div/div[2]/p");

    // Constructor
    public RecruitmentPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToAddCandidatePage() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/recruitment/addCandidate");
    }

    // Method to enter the first name
    public void enterFirstName(String firstName) {
        WebElement firstNameElement = driver.findElement(firstNameField);
        firstNameElement.clear();
        firstNameElement.sendKeys(firstName);
    }

    // Method to enter the middle name
    public void enterMiddleName(String middleName) {
        WebElement middleNameElement = driver.findElement(middleNameField);
        middleNameElement.clear();
        middleNameElement.sendKeys(middleName);
    }

    // Method to enter the last name
    public void enterLastName(String lastName) {
        WebElement lastNameElement = driver.findElement(lastNameField);
        lastNameElement.clear();
        lastNameElement.sendKeys(lastName);
    }

    // Method to enter the email
    public void enterEmail(String email) {
        WebElement emailElement = driver.findElement(emailField);
        emailElement.clear();
        emailElement.sendKeys(email);
    }

    // Method to enter the contact number
    public void enterContactNumber(String contactNumber) {
        WebElement contactNumberElement = driver.findElement(contactNumberField);
        contactNumberElement.clear();
        contactNumberElement.sendKeys(contactNumber);
    }

    // Method to upload a resume
    public void uploadResume(String resumePath) {
        WebElement resumeUploadElement = driver.findElement(resumeUploadInput);
        resumeUploadElement.sendKeys(resumePath);
    }

    // Method to enter keywords
    public void enterKeywords(String keywords) {
        WebElement keywordsElement = driver.findElement(keywordsField);
        keywordsElement.sendKeys(keywords);
    }

    // Method to enter the date of application
    public void enterDateOfApplication(String date) {
        WebElement dateElement = driver.findElement(dateField);
        dateElement.sendKeys(Keys.CONTROL + "a");
        dateElement.sendKeys(Keys.DELETE);
        dateElement.sendKeys(date); // Ensure the format is "yyyy-mm-dd"
    }

    // Method to enter notes
    public void enterNotes(String notes) {
        WebElement notesElement = driver.findElement(notesField);
        notesElement.clear();
        notesElement.sendKeys(notes);
    }

    // Method to check consent
    public void checkConsent() {
        WebElement consentElement = driver.findElement(consentCheckbox);
        consentElement.click();
    }

    // Method to select a vacancy from the dropdown
    public void selectVacancy() {
        WebElement vacancyDropdownElement = driver.findElement(vacancyDropdownButton);
        vacancyDropdownElement.click();
        vacancyDropdownElement.click(); // Assuming another click is required based on your original code
    }

    // Method to click the Save button
    public void clickSave() {
        WebElement saveButtonElement = driver.findElement(saveButton);
        saveButtonElement.click();
    }

    // Method to click the Cancel button
    public void clickCancel() {
        WebElement cancelButtonElement = driver.findElement(cancelButton);
        cancelButtonElement.click();
    }

    // Method to check if success message is displayed
    public boolean isSuccessMessageDisplayed() {
        return driver.findElements(successMessage).size() > 0;
    }

    // Method to check if first name error message is displayed
    public boolean isFirstNameErrorDisplayed() {
        return driver.findElements(firstNameError).size() > 0;
    }

    // Method to check if last name error message is displayed
    public boolean isLastNameErrorDisplayed() {
        return driver.findElements(lastNameError).size() > 0;
    }

    // Method to check if email error message is displayed
    public boolean isEmailErrorDisplayed() {
        return driver.findElements(emailError).size() > 0;
    }

    // Method to check if email format error message is displayed
    public boolean isEmailFormatErrorDisplayed() {
        return driver.findElements(emailFormatError).size() > 0;
    }

    // Method to check for wrong DoA error
    public boolean WrongDoaErrorDisplayed() {
        return driver.findElements(wrongDoaError).size() > 0;
    }

    // Method to check for wrong DoA format error
    public boolean WrongDoaFormatDisplayed() {
        return driver.findElements(wrongDoaFormat).size() > 0;
    }

    // Method to check if attachment size exceeded
    public boolean AttachmentSizeExceeded() {
        return driver.findElements(attachmentSizeExceeded).size() > 0;
    }

    // Method to check if the wrong file type was uploaded
    public boolean WrongFileType() {
        return driver.findElements(wrongFileType).size() > 0;
    }

    public String GetName() {
        WebElement nameElement = driver.findElement(nameField);
        return nameElement.getText();
    }
}
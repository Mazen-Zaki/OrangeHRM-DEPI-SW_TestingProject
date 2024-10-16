package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class RecruitmentPage {
    private WebDriver driver;

    // Locators
    private By usernameLocator = By.name("username");
    private By passwordLocator = By.name("password");
    private By loginButtonLocator = By.xpath("//button[@type='submit']");

    private By firstNameLocator = By.name("firstName");
    private By middleNameLocator = By.name("middleName");
    private By lastNameLocator = By.name("lastName");
    private By emailLocator = By.xpath("//input[@placeholder='Type here']");

    private By contactNumberLocator = By.xpath("//input[@placeholder='Type here']");
    private By resumeLocator = By.xpath("//input[@type='file']"); // Updated to file input for direct upload
    private By keywordsLocator = By.xpath("//input[@placeholder='Enter comma seperated words...']");
    private By dateOfApplicationLocator = By.xpath("//input[@placeholder='yyyy-mm-dd']"); // Updated format
    private By notesLocator = By.xpath("//textarea[@placeholder='Type here']");
    private By consentCheckboxInputLocator = By.xpath("//input[@name='consent']");
    private By saveButtonLocator = By.xpath("//button[contains(@class, 'oxd-button--secondary') and normalize-space()='Save']");
    private By cancelButtonLocator = By.xpath("//button[contains(@class, 'oxd-button--ghost') and normalize-space()='Cancel']");

    private By successMessageLocator = By.xpath("//div[@class='success-message']");
    private By firstNameErrorLocator = By.xpath("//span[contains(text(),'Required')]");
    private By emailErrorLocator = By.xpath("//span[contains(text(),'Required')]");
    private By emailFormatErrorLocator = By.xpath("//span[contains(text(),'Expected format: admin@example.com')]");

    // Vacancy dropdown locator
    private By vacancyLocator = By.xpath("//select[@name='vacancy']"); // Updated for dropdown

    // Constructor
    public RecruitmentPage(WebDriver driver) {
        this.driver = driver;
    }

    //// Method to login as Admin
    public void loginAsAdmin(String username, String password) {
        WebElement usernameField = driver.findElement(usernameLocator);
        usernameField.clear();
        usernameField.sendKeys(username);

        WebElement passwordField = driver.findElement(passwordLocator);
        passwordField.clear();
        passwordField.sendKeys(password);

        driver.findElement(loginButtonLocator).click();
    }

    // Method to navigate to the Add Candidate page
    public void navigateToAddCandidatePage() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/recruitment/addCandidate");
    }

    // Method to enter the first name
    public void enterFirstName(String firstName) {
        WebElement firstNameField = driver.findElement(firstNameLocator);
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }

    // Method to enter the middle name
    public void enterMiddleName(String middleName) {
        WebElement middleNameField = driver.findElement(middleNameLocator);
        middleNameField.clear();
        middleNameField.sendKeys(middleName);
    }

    // Method to enter the last name
    public void enterLastName(String lastName) {
        WebElement lastNameField = driver.findElement(lastNameLocator);
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    // Method to enter the email
    public void enterEmail(String email) {
        WebElement emailField = driver.findElement(emailLocator);
        emailField.clear();
        emailField.sendKeys(email);
    }

    // Method to enter the contact number
    public void enterContactNumber(String contactNumber) {
        WebElement contactNumberField = driver.findElement(contactNumberLocator);
        contactNumberField.clear();
        contactNumberField.sendKeys(contactNumber);
    }

    // Method to upload a resume (fixed)
    public void uploadResume(String resumePath) {
        WebElement resumeUploadInput = driver.findElement(resumeLocator);
        resumeUploadInput.sendKeys(resumePath); // Directly upload the file
    }

    // Method to enter keywords
    public void enterKeywords(String keywords) {
        WebElement keywordsField = driver.findElement(keywordsLocator);
        keywordsField.clear();
        keywordsField.sendKeys(keywords);
    }

    // Method to enter the date of application (updated date format)
    public void enterDateOfApplication(String date) {
        WebElement dateField = driver.findElement(dateOfApplicationLocator);
        dateField.clear();
        dateField.sendKeys(date); // Ensure the format is "yyyy-mm-dd"
    }

    // Method to enter notes
    public void enterNotes(String notes) {
        WebElement notesField = driver.findElement(notesLocator);
        notesField.clear();
        notesField.sendKeys(notes);
    }

    // Method to check consent
    public void checkConsent() {
        WebElement consentCheckbox = driver.findElement(consentCheckboxInputLocator);
        if (!consentCheckbox.isSelected()) {
            consentCheckbox.click();
        }
    }

    // Method to select a vacancy from the dropdown (added)
    public void selectVacancy(String vacancy) {
        WebElement vacancyDropdown = driver.findElement(vacancyLocator);
        Select selectVacancy = new Select(vacancyDropdown);
        selectVacancy.selectByVisibleText(vacancy);  // Or use selectByIndex() if required
    }

    // Method to click the Save button
    public void clickSave() {
        driver.findElement(saveButtonLocator).click();
    }

    // Method to click the Cancel button
    public void clickCancel() {
        driver.findElement(cancelButtonLocator).click();
    }

    // Method to check if success message is displayed
    public boolean isSuccessMessageDisplayed() {
        return driver.findElements(successMessageLocator).size() > 0;
    }

    // Method to check if first name error message is displayed
    public boolean isFirstNameErrorDisplayed() {
        return driver.findElements(firstNameErrorLocator).size() > 0;
    }

    // Method to check if email error message is displayed
    public boolean isEmailErrorDisplayed() {
        return driver.findElements(emailErrorLocator).size() > 0;
    }

    //// Method to check if email format error message is displayed
    public boolean isEmailFormatErrorDisplayed() {
        return driver.findElements(emailFormatErrorLocator).size() > 0;
    }

    // Method to check if form is reset
    public boolean isFormReset() {
        return driver.findElement(firstNameLocator).getAttribute("value").isEmpty() &&
                driver.findElement(emailLocator).getAttribute("value").isEmpty();
    }
}

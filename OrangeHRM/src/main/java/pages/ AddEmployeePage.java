package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddEmployeePage {
    private WebDriver driver;

    // Locators
    private By firstNameField = By.name("firstName");
    private By lastNameField = By.name("lastName");
    private By employeeIdField = By.xpath("//label[text()='Employee Id']/following::input[1]");
    private By saveButton = By.xpath("//button[text()='Save']");
    private By successMessage = By.xpath("//div[contains(@class, 'oxd-toast-content') and contains(text(), 'Successfully Saved')]");

    public AddEmployeePage(WebDriver driver) {
        this.driver = driver;
    }

    public void addEmployee(String firstName, String lastName, String employeeId) {
        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(employeeIdField).clear();
        driver.findElement(employeeIdField).sendKeys(employeeId);
        driver.findElement(saveButton).click();
    }

    public boolean isEmployeeAddedSuccessfully() {
        return driver.findElements(successMessage).size() > 0;
    }
}

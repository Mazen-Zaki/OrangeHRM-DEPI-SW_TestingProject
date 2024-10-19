package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PIMPage {
    private WebDriver driver;

    // Locators
    private By pimTab = By.xpath("//span[text()='PIM']");
    private By addEmployeeButton = By.xpath("//button[text()='Add Employee']");
    private By firstNameField = By.name("firstName");
    private By lastNameField = By.name("lastName");
    private By employeeIdField = By.name("employeeId");
    private By saveButton = By.xpath("//button[text()='Save']");
    private By errorMessage = By.xpath("//span[contains(text(),'Already exists')]");

    public PIMPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToPIM() {
        driver.findElement(pimTab).click();
    }

    public void clickAddEmployee() {
        driver.findElement(addEmployeeButton).click();
    }

    public void addEmployee(String firstName, String lastName, String employeeId) {
        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(employeeIdField).clear();
        driver.findElement(employeeIdField).sendKeys(employeeId);
        driver.findElement(saveButton).click();
    }

    public boolean isErrorMessageDisplayed() {
        return driver.findElements(errorMessage).size() > 0;
    }
}

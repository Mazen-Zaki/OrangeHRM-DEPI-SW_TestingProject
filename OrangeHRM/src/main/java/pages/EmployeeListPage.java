package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EmployeeListPage {
    private WebDriver driver;

    // Locators
    private By employeeNameField = By.xpath("//input[@placeholder='Type for hints...']");
    private By employeeIdField = By.xpath("//label[text()='Employee Id']/following::input[1]");
    private By searchButton = By.xpath("//button[text()='Search']");
    private By resetButton = By.xpath("//button[text()='Reset']");
    private By searchResult = By.xpath("//div[@class='oxd-table']");
    private By editButton = By.xpath("//i[@class='oxd-icon bi-pencil-fill']"); // Locator for the edit icon (update button)
    private By deleteButton = By.xpath("//i[@class='oxd-icon bi-trash']"); 

    public EmployeeListPage(WebDriver driver) {
        this.driver = driver;
    }

    public void searchByName(String employeeName) {
        driver.findElement(employeeNameField).sendKeys(employeeName);
        driver.findElement(searchButton).click();
    }

    public void searchById(String employeeId) {
        driver.findElement(employeeIdField).sendKeys(employeeId);
        driver.findElement(searchButton).click();
    }

    public void searchByNameAndId(String employeeName, String employeeId) {
        driver.findElement(employeeNameField).sendKeys(employeeName);
        driver.findElement(employeeIdField).sendKeys(employeeId);
        driver.findElement(searchButton).click();
    }

    public void resetFields() {
        driver.findElement(resetButton).click();
    }

    public boolean isSearchResultDisplayed() {
        return driver.findElements(searchResult).size() > 0;
    }
     public void clickEditButton() {
        driver.findElement(editButton).click();
    }

    public void clickDeleteButton() {
        driver.findElement(deleteButton).click();
    }

    public boolean isEmployeeDeleted(String employeeName) {
        // Re-search the employee after deletion to ensure the record no longer exists.
        //searchEmployee(employeeName);
        return driver.findElements(By.xpath("//div[text()='" + employeeName + "']")).isEmpty();
    }
}


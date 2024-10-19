package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ViewEmployeeListPage {
    private WebDriver driver;

    private By searchField = By.xpath("//input[@placeholder='Search']");
    private By searchButton = By.xpath("//button[@type='submit']");
    private By employeeNameLink = By.xpath("//a[contains(text(),'John Doe')]"); // Change "John Doe" based on search criteria
    private By editButton = By.id("btnSave");
    private By deleteButton = By.xpath("//button[contains(@class, 'delete')]");
    private By confirmDeleteButton = By.xpath("//button[text()='Yes, Delete']");
    private By successMessage = By.xpath("//div[contains(@class, 'success-message')]");

    public ViewEmployeeListPage(WebDriver driver) {
        this.driver = driver;
    }

    public void searchEmployee(String employeeName) {
        driver.findElement(searchField).sendKeys(employeeName);
        driver.findElement(searchButton).click();
    }

    public void editEmployeeDetails(String newFirstName, String newLastName) {
        driver.findElement(employeeNameLink).click(); // Clicks on the employee to view/edit details
        driver.findElement(editButton).click();
        WebElement firstNameField = driver.findElement(By.id("firstName"));
        WebElement lastNameField = driver.findElement(By.id("lastName"));
        firstNameField.clear();
        firstNameField.sendKeys(newFirstName);
        lastNameField.clear();
        lastNameField.sendKeys(newLastName);
        driver.findElement(editButton).click(); // Save changes
    }

    public void deleteEmployee() {
        driver.findElement(employeeNameLink).click(); // Select the employee
        driver.findElement(deleteButton).click();
        driver.findElement(confirmDeleteButton).click();
    }

    public boolean isSuccessMessageDisplayed() {
        return driver.findElements(successMessage).size() > 0;
    }
}

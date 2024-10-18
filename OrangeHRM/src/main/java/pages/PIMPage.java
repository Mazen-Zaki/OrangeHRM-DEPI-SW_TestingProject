package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PIMPage {
    WebDriver driver;

    By addEmployeeButton = By.xpath("//a[@href='/web/index.php/pim/addEmployee']");
    By firstName = By.name("firstName");
    By lastName = By.name("lastName");
    By employeeId = By.name("employeeId");
    By saveButton = By.id("btnSave");
    By searchEmployeeName = By.xpath("//input[@name='empsearch[employee_name][empName]']");
    By searchButton = By.id("searchBtn");
    By resetButton = By.id("resetBtn");
    By deleteButton = By.id("btnDelete");
    By confirmDeleteButton = By.id("dialogDeleteBtn");
    By editButton = By.id("btnSave");

    public PIMPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToAddEmployee() {
        driver.findElement(addEmployeeButton).click();
    }

    public void addEmployee(String fName, String lName, String empId) {
        driver.findElement(firstName).sendKeys(fName);
        driver.findElement(lastName).sendKeys(lName);
        driver.findElement(employeeId).clear();
        driver.findElement(employeeId).sendKeys(empId);
        driver.findElement(saveButton).click();
    }

    public void searchEmployee(String empName) {
        driver.findElement(searchEmployeeName).sendKeys(empName);
        driver.findElement(searchButton).click();
    }

    public void resetSearch() {
        driver.findElement(resetButton).click();
    }

    public void deleteEmployee() {
        driver.findElement(deleteButton).click();
        driver.findElement(confirmDeleteButton).click();
    }

    public void editEmployee(String newFName, String newLName) {
        driver.findElement(editButton).click();
        driver.findElement(firstName).clear();
        driver.findElement(firstName).sendKeys(newFName);
        driver.findElement(lastName).clear();
        driver.findElement(lastName).sendKeys(newLName);
        driver.findElement(saveButton).click();
    }
}

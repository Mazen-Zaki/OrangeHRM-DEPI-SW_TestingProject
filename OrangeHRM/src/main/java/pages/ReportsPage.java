package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ReportsPage {
    private WebDriver driver;

    // Locators for adding, editing, and deleting a report
    private By addReportButton = By.xpath("//button[text()='Add']");
    private By reportNameField = By.name("report[report_name]");
    private By saveButton = By.xpath("//button[text()='Save']");
    private By reportList = By.xpath("//table/tbody/tr");
    private By editButton = By.xpath("//i[@class='oxd-icon bi-pencil-fill']"); // Example locator for the edit button
    private By deleteButton = By.xpath("//i[@class='oxd-icon bi-trash']"); // Example locator for the delete button

    public ReportsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addReport(String reportName) {
        driver.findElement(addReportButton).click();
        driver.findElement(reportNameField).sendKeys(reportName);
        driver.findElement(saveButton).click();
    }

    public void editReport(String newReportName) {
        driver.findElement(editButton).click();
        driver.findElement(reportNameField).clear();
        driver.findElement(reportNameField).sendKeys(newReportName);
        driver.findElement(saveButton).click();
    }

    public void deleteReport() {
        driver.findElement(deleteButton).click();
        driver.switchTo().alert().accept(); // Confirm deletion if a JavaScript alert appears.
    }

    public boolean isReportListed(String reportName) {
        // Check if the report name appears in the list after adding or editing.
        return driver.findElements(By.xpath("//table/tbody/tr/td[text()='" + reportName + "']")).size() > 0;
    }
}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ReportsPage {
    private WebDriver driver;

    // Locators
    private By addButton = By.xpath("//button[text()='Add']");
    private By reportNameField = By.name("report_name");
    private By saveButton = By.xpath("//button[text()='Save']");
    private By searchField = By.xpath("//input[@placeholder='Search...']");
    private By searchButton = By.xpath("//button[text()='Search']");
    private By editButton = By.xpath("//i[@class='oxd-icon bi-pencil-fill']");
    private By deleteButton = By.xpath("//i[@class='oxd-icon bi-trash']");
    private By resetButton = By.xpath("//button[text()='Reset']");

    public ReportsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addNewReport(String reportName) {
        driver.findElement(addButton).click();
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
        driver.switchTo().alert().accept(); // Example of handling a confirmation popup
    }

    public void searchReport(String reportName) {
        driver.findElement(searchField).sendKeys(reportName);
        driver.findElement(searchButton).click();
    }

    public void clickResetButton() {
        driver.findElement(resetButton).click();
    }

    public boolean isReportPresent(String reportName) {
        return driver.findElements(By.xpath("//div[text()='" + reportName + "']")).size() > 0;
    }
}

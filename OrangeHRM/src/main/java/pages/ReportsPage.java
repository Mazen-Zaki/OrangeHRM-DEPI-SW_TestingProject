package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ReportsPage {
    private WebDriver driver;

    private By reportsTab = By.id("menu_pim_viewReport");
    private By addReportButton = By.id("btnAdd");
    private By reportNameField = By.id("report_report_name");
    private By saveReportButton = By.id("btnSave");
    private By searchReportField = By.id("search_search");
    private By searchButton = By.id("btnSearch");
    private By reportNameLink = By.xpath("//a[contains(text(), 'Sample Report')]"); // Replace 'Sample Report' as needed
    private By editButton = By.id("btnSave");
    private By deleteButton = By.id("btnDelete");
    private By confirmDeleteButton = By.id("dialogDeleteBtn");
    private By successMessage = By.xpath("//div[contains(@class, 'success-message')]");

    public ReportsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToReports() {
        driver.findElement(reportsTab).click();
    }

    public void addReport(String reportName) {
        driver.findElement(addReportButton).click();
        driver.findElement(reportNameField).sendKeys(reportName);
        driver.findElement(saveReportButton).click();
    }

    public void searchReport(String reportName) {
        driver.findElement(searchReportField).sendKeys(reportName);
        driver.findElement(searchButton).click();
    }

    public void editReport(String newReportName) {
        driver.findElement(reportNameLink).click();
        driver.findElement(editButton).click();
        WebElement reportNameInput = driver.findElement(reportNameField);
        reportNameInput.clear();
        reportNameInput.sendKeys(newReportName);
        driver.findElement(saveReportButton).click();
    }

    public void deleteReport() {
        driver.findElement(reportNameLink).click();
        driver.findElement(deleteButton).click();
        driver.findElement(confirmDeleteButton).click();
    }

    public boolean isSuccessMessageDisplayed() {
        return driver.findElements(successMessage).size() > 0;
    }
}

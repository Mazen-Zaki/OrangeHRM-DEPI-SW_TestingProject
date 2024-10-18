
package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ReportsPage {
    WebDriver driver;

    By reportsTab = By.xpath("//a[@href='/web/index.php/pim/viewDefinedPredefinedReports']");
    By addReportButton = By.id("btnAdd");
    By reportName = By.id("report_report_name");
    By selectionCriteria = By.id("report_criteria_list");
    By saveButton = By.id("btnSave");
    By searchReportName = By.id("search_search");
    By searchButton = By.id("searchBtn");
    By resetButton = By.id("resetBtn");
    By deleteButton = By.id("btnDelete");
    By confirmDeleteButton = By.id("dialogDeleteBtn");

    public ReportsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToReports() {
        driver.findElement(reportsTab).click();
    }

    public void addReport(String name, String criteria) {
        driver.findElement(addReportButton).click();
        driver.findElement(reportName).sendKeys(name);
        driver.findElement(selectionCriteria).sendKeys(criteria);
        driver.findElement(saveButton).click();
    }

    public void searchReport(String name) {
        driver.findElement(searchReportName).sendKeys(name);
        driver.findElement(searchButton).click();
    }

    public void resetSearch() {
        driver.findElement(resetButton).click();
    }

    public void deleteReport() {
        driver.findElement(deleteButton).click();
        driver.findElement(confirmDeleteButton).click();
    }
}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PIMPage {
    private WebDriver driver;

    // Locator
    private By pimTab = By.xpath("//span[text()='PIM']");
     private By addEmployeeTab = By.xpath("//a[@href='/web/index.php/pim/addEmployee']");
      private By reportsTab = By.linkText("Reports");
    private By EmployeeListTab = By.xpath("//a[@href='/web/index.php/pim/viewEmployeeList']");

    public PIMPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToPIM() {
        driver.findElement(pimTab).click();
    }
     public void navigateToEmployeeTab() {
        driver.findElement(addEmployeeTab).click();
    }
     public void navigateToReports() {
        driver.findElement(reportsTab).click();
    }
    public void ListViewTab() {driver.findElement(EmployeeListTab).click();
    }
}

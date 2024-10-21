package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class AdminPages {

    WebDriver driver;
    WebDriverWait wait;

    // Locators for the elements
    By userRoleDropdownLocator = By.xpath("//label[text()='User Role']/following::div[@class='oxd-select-text-input']");
    By statusDropdownLocator = By.xpath("//label[text()='Status']/following::div[@class='oxd-select-text-input']");
    By dropdownOptionsLocator = By.xpath("//div[@role='listbox']//span");
    By searchButtonLocator = By.xpath("//button[@type='submit'][normalize-space()='Search']");
    By resetButtonLocator = By.xpath("//button[normalize-space()='Reset']");
    By recordsFoundLocator = By.xpath("//span[@class='oxd-text oxd-text--span']");

    public AdminPages(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Method to select user role from dropdown
    public void selectUserRole(String role) {
        WebElement userRoleDropdown = wait.until(ExpectedConditions.elementToBeClickable(userRoleDropdownLocator));
        userRoleDropdown.click();

        // Select role from dropdown options
        List<WebElement> dropdownOptions = driver.findElements(dropdownOptionsLocator);
        for (WebElement option : dropdownOptions) {
            if (option.getText().equals(role)) {
                option.click();
                break;
            }
        }
    }

    // Method to select status from dropdown
    public void selectStatus(String status) {
        WebElement statusDropdown = wait.until(ExpectedConditions.elementToBeClickable(statusDropdownLocator));
        statusDropdown.click();

        // Select status from dropdown options
        List<WebElement> dropdownOptions = driver.findElements(dropdownOptionsLocator);
        for (WebElement option : dropdownOptions) {
            if (option.getText().equals(status)) {
                option.click();
                break;
            }
        }
    }

    // Method to click the search button
    public void clickSearch() {
        WebElement searchButton = driver.findElement(searchButtonLocator);
        searchButton.click();
    }

    // Method to click the reset button
    public void clickReset() {
        WebElement resetButton = driver.findElement(resetButtonLocator);
        resetButton.click();
    }

    // Method to get the "Records Found" text
    public String getRecordsFoundText() {
        // Explicitly wait for the element that contains the 'Records Found' text to be
        // visible
        WebElement recordsFound = wait.until(
                ExpectedConditions.visibilityOfElementLocated(recordsFoundLocator));

        // Return the text of the element
        return recordsFound.getText();
    }

    // Method to verify if filters are reset
    public boolean isFilterReset() {
        // Verify by checking the default values of the dropdowns
        WebElement userRoleDropdown = driver.findElement(userRoleDropdownLocator);
        WebElement statusDropdown = driver.findElement(statusDropdownLocator);

        return userRoleDropdown.getText().contains("Select") && statusDropdown.getText().contains("Select");
    }
}

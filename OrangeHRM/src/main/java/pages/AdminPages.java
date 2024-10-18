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

    public AdminPages(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Method to select user role from dropdown
    public void selectUserRole(String role) {
        WebElement userRoleDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[text()='User Role']/following::div[@class='oxd-select-text-input']")));
        userRoleDropdown.click();

        // Select role from dropdown options
        List<WebElement> dropdownOptions = driver.findElements(By.xpath("//div[@role='listbox']//span"));
        for (WebElement option : dropdownOptions) {
            if (option.getText().equals(role)) {
                option.click();
                break;
            }
        }
    }

    // Method to select status from dropdown
    public void selectStatus(String status) {
        WebElement statusDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[text()='Status']/following::div[@class='oxd-select-text-input']")));
        statusDropdown.click();

        // Select status from dropdown options
        List<WebElement> dropdownOptions = driver.findElements(By.xpath("//div[@role='listbox']//span"));
        for (WebElement option : dropdownOptions) {
            if (option.getText().equals(status)) {
                option.click();
                break;
            }
        }
    }

    // Method to click the search button
    public void clickSearch() {
        WebElement searchButton = driver.findElement(By.xpath("//button[@type='submit'][normalize-space()='Search']"));
        searchButton.click();
    }

    // Method to click the reset button
    public void clickReset() {
        WebElement resetButton = driver.findElement(By.xpath("//button[normalize-space()='Reset']"));
        resetButton.click();
    }

    // Method to get the number of records found
    // public String getRecordsFoundText() {
    // WebElement recordsFoundElement =
    // driver.findElement(By.xpath("class=\"oxd-text oxd-text--span\""));
    // return recordsFoundElement.getText();
    // }
    public String getRecordsFoundText() {
        // Explicitly wait for the element that contains the 'Records Found' text to be
        // visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Wait for up to 10 seconds
        WebElement recordsFound = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\"oxd-text oxd-text--span\"]")));

        // Return the text of the element
        return recordsFound.getText();
    }

    // Method to verify if filters are reset
    public boolean isFilterReset() {
        // Assuming if the dropdowns are reset to the default, we verify by checking the
        // default values
        WebElement userRoleDropdown = driver
                .findElement(By.xpath("//label[text()='User Role']/following::div[@class='oxd-select-text-input']"));
        WebElement statusDropdown = driver
                .findElement(By.xpath("//label[text()='Status']/following::div[@class='oxd-select-text-input']"));

        return userRoleDropdown.getText().contains("Select") && statusDropdown.getText().contains("Select");
    }
}

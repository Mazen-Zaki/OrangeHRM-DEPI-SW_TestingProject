package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class DirectoryPage {

    WebDriver directoryDriver;
    WebDriverWait wait;

    public DirectoryPage(WebDriver driver) {
        this.directoryDriver = driver;
        this.wait = new WebDriverWait(directoryDriver, Duration.ofSeconds(10));  // Initialize wait with 10 seconds
    }

    /**********************************      Locators      **********************************/

    By EmployeeName = By.xpath("//input[@placeholder=\"Type for hints...\"]");
    By directoryButton = By.xpath("//span[text()='Directory']");
    By searchBtn = By.xpath("//button[@type='submit']");
    By resetBtn = By.xpath("//button[@type='reset']");
    By recordsTable = By.cssSelector(".orangehrm-directory-card-header");
    By invalidMessage = By.xpath("//span[text()='Invalid']");
    By jobTitle = By.xpath("//div[@class='oxd-grid-4']//div[@class='oxd-grid-item oxd-grid-item--gutters']//div[contains(@class,'orangehrm-directory-card')]//p[contains(@class,'orangehrm-directory-card-subtitle')]");
    By jobTitleDropDownSearch = By.cssSelector(".oxd-select-text-input");
    /**********************************      Actions      **********************************/
    public void navigateToDirectory() {
        directoryDriver.findElement(directoryButton).click();
    }

    public void writeEmployeeName(String employeeName) {
        directoryDriver.findElement(EmployeeName).sendKeys(employeeName);
    }

    public void clickOnSearchBtn() {
        directoryDriver.findElement(searchBtn).click();
    }

    public WebElement isInvalidMessageIsShown() {
        return directoryDriver.findElement(invalidMessage);
    }

    public void clearSearch() {
        directoryDriver.findElement(resetBtn).click();
    }

    public String getWrittenValue() {
        return directoryDriver.findElement(EmployeeName).getAttribute("value");
    }

    public void chooseJobTitle(String status) {
        WebElement dropdown = directoryDriver.findElement(jobTitleDropDownSearch); // Adjust the XPath as necessary
        dropdown.click();
        WebElement dropdownOption = directoryDriver.findElement(By.xpath("//div[@role='option']//span[text()='" + status + "']"));
        dropdownOption.click();
    }

    public String[] getAllRecords() {
        // Wait for the table elements to be visible
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(recordsTable));
        List<WebElement> headers = directoryDriver.findElements(recordsTable);
        List<String> headerTexts = new ArrayList<>();
        for (WebElement header : headers) {
            // Extract the text from each element and add it to the list
            headerTexts.add(header.getText());
        }
        // Convert the list of header texts to a string array
        return headerTexts.toArray(new String[0]);
    }

    public String[] getJobTitles() {
        // Wait for all records to be visible
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(recordsTable));
        // Get all the cards
        List<WebElement> cards = directoryDriver.findElements(jobTitle);
        // Locate the internal scrollable div (replace 'scrollableDiv' with the actual class or id)
        WebElement scrollableDiv = directoryDriver.findElement(By.cssSelector(".orangehrm-container")); // Example class
        // Use JavaScript to scroll down 500 pixels within the div

        JavascriptExecutor jsExecutor = (JavascriptExecutor) directoryDriver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", scrollableDiv);

        // Create a list to store job title texts
        List<String> jobTexts = new ArrayList<>();

        // Loop through each card
        for (WebElement card : cards) {
            // Try to find the job title element inside the current card
            String temp = card.getText();
            if (!(temp.equals(""))) {
                jobTexts.add(card.getText());
            } // Add the job title to the list
            System.out.print(card.getText());
        }
        // Return the job titles as an array
        return jobTexts.toArray(new String[0]);
    }
}
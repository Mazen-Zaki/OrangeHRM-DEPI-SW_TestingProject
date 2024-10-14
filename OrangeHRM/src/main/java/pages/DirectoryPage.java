package pages;

import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Directory {

    WebDriver directoryDriver;
    WebDriverWait wait;
    public Directory(WebDriver driver) {
        this.directoryDriver = driver;
        this.wait = new WebDriverWait(directoryDriver, Duration.ofSeconds(10));  // Initialize wait with 10 seconds
    }

    /**********************************      Elements      **********************************/
    By EmployeeName= By.xpath("//input[@placeholder=\"Type for hints...\"]");
//    By Location=By.className("old-select-text oxd-select-text--active");
    By pageTitle=By.xpath("//h6[text()='Directory']");
    By directoryButton=By.xpath("//span[text()='Directory']");
    By searchBtn=By.xpath("//button[@type='submit']");
    By resetBtn= By.xpath("//button[@type='reset']");
    By recordsTable=By.cssSelector(".orangehrm-directory-card-header");
    By invalidMessage=By.xpath("//span[text()='Invalid']");
//    By jobTitle=By.xpath("//div[text()='-- Select --']");
By jobTitle=By.cssSelector(".oxd-select-text-input");
    /**********************************      Actions      **********************************/
    public void waitUntilPageIsLoaded(){
        try{
        WebDriverWait wait= new WebDriverWait(directoryDriver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));} catch (RuntimeException e) {
            System.out.println("Error ISSSSSSSS"+e.toString());
        }
    }
    public void navigateToDirectory(){
        directoryDriver.findElement(directoryButton).click();
    }

    public void writeEmployeeName(String employeeName){
         directoryDriver.findElement(EmployeeName).sendKeys(employeeName);
     }
     public void clickOnSearchBtn(){
        directoryDriver.findElement(searchBtn).click();
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
    public String getExistingRecord(){
        return getAllRecords()[0];
    }
    public WebElement isInvalidMessageIsShown(){
       return directoryDriver.findElement(invalidMessage);
    }
    public void clearSearch(){
        directoryDriver.findElement(resetBtn).click();
    }
    public String getWrittenValue(){
        return directoryDriver.findElement(EmployeeName).getAttribute("value");
    }
//    public void chooseJobTitle(){
//        WebElement dropdown = directoryDriver.findElement(jobTitle); // Adjust the XPath as necessary
//        dropdown.click();
//        //Software Engineer
//        WebElement selection=directoryDriver.findElement(By.xpath("//div[text()='Software Engineer']"));
//        selection.click();
//    }

}


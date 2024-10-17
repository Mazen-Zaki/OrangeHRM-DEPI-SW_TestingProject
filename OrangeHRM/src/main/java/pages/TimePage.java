package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TimePage {
    WebDriver timeDriver;
    WebDriverWait wait;
    public TimePage(WebDriver driver){
        timeDriver=driver;
    }
    By TimeButton=By.xpath("//span[text()=\"Time\"]");
    By employeeName=By.xpath("//input[@placeholder=\"Type for hints...\"]");
    By viewBtn=By.xpath("//button[@type=\"submit\"]");
    By employeeRecords=By.xpath("//div[@class=\"oxd-table-cell oxd-padding-cell\"][1][1]");
    By attendanceButton=By.xpath("(//span[@class='oxd-topbar-body-nav-tab-item'])[2]");
    By configButton=By.linkText("Configuration");
    By punchingInOutBtn=By.linkText("Punch In/Out");
    By changeCurrentTimeOption =By.xpath("//span[contains(@class, 'oxd-switch-input') and contains(@class, 'oxd-switch-input--active')]");
    By requiredMessage = By.xpath("//span[text()='Required']");
    By invalidMessage = By.xpath("//span[text()='Invalid']");
    By saveBtn = By.xpath("//button[@type='submit']");
    By setDate = By.xpath("//input[@placeholder='yyyy-dd-mm']");
    By setTime = By.xpath("//input[@placeholder='hh:mm']");
    By myRecordsBtn=By.linkText("My Records");
    By punchedInTime=By.xpath("//p[@class='oxd-text oxd-text--p oxd-text--subtitle-2']");
    By AddButton=By.xpath("//button[text()=\" Add \"]");
    By projectInfo=By.xpath("(//span[@class='oxd-topbar-body-nav-tab-item'])[4]");
    By customerInfo=By.linkText("Customers");
    By customerName=By.xpath("(//input[@class=\"oxd-input oxd-input--active\"])[2]");
    By recordNames=By.xpath("//div[@class=\"oxd-table-cell oxd-padding-cell\"][2]");
    By recordsFound=By.xpath("(//span[@class=\"oxd-text oxd-text--span\"])[1]");
    public By inTimeRecord=By.xpath("//div[@class=\"oxd-table-cell oxd-padding-cell\"][2]");
    public By outTimeRecord=By.xpath("//div[@class=\"oxd-table-cell oxd-padding-cell\"][4]");




    public void navigateToTimePage(){
        timeDriver.findElement(TimeButton).click();
        wait = new WebDriverWait(timeDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(viewBtn));
        timeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }
    public void writeEmployeeName(String empName){
        timeDriver.findElement(employeeName).sendKeys(empName);
    }
    public WebElement inValidMessage(){
        return timeDriver.findElement(invalidMessage);
    }
    public WebElement requiredMessage(){
        return timeDriver.findElement(requiredMessage);
    }


    public void clickOnSearchBtn(){
        timeDriver.findElement(viewBtn).click();
    }
    public String[] getAllNameRecords() {
        // Wait for the table elements to be visible
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(employeeRecords));
        List<WebElement> NameElement=timeDriver.findElements(employeeRecords);
        List<String> headerTexts = new ArrayList<>();
        for (WebElement header : NameElement) {
            // Extract the text from each element and add it to the list
            headerTexts.add(header.getText());
        }

        // Convert the list of header texts to a string array
        return headerTexts.toArray(new String[0]);
    }
    public void disableChangingCurrentTimeWhenPunching(){
        timeDriver.findElement(changeCurrentTimeOption).click();
    }
    public void moveToConfiguration(){
        timeDriver.findElement(attendanceButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(configButton));
        timeDriver.findElement(configButton).click();
    }
    public void ToggleCurrentTimeOption(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(changeCurrentTimeOption));
        timeDriver.findElement(changeCurrentTimeOption).click();
    }
    public void pressSaveBtn(){
        timeDriver.findElement(saveBtn).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(saveBtn));
    }
    public void clearDate(){
        timeDriver.findElement(setDate).sendKeys(Keys.SPACE, Keys.BACK_SPACE);
    }
    public void clearTime(){
        timeDriver.findElement(setTime).clear();
    }

    public WebElement punchDate(){
        timeDriver.findElement(attendanceButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(punchingInOutBtn));
        timeDriver.findElement(punchingInOutBtn).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'oxd-input-group') and .//label[text()='Date']]//input[@placeholder='yyyy-dd-mm']")));
        return timeDriver.findElement(setDate);
    }
    public void navigateToPinchInOut(){
        timeDriver.findElement(attendanceButton).click();
        timeDriver.findElement(punchingInOutBtn).click();
    }
    public void setPunchInDate(String date){
        timeDriver.findElement(setDate).sendKeys(date);
    }
    public void setPunchInTime(String time){
        timeDriver.findElement(setTime).sendKeys(time);
    }
    public void setPunchOutDate(String date){
        timeDriver.findElement(setDate).sendKeys(date);
    }
    public void setDate(String time){
        clearDate();
        wait.until(ExpectedConditions.visibilityOfElementLocated(setDate));
        timeDriver.findElement(setDate).sendKeys(time);
    }
    public void punchIn(String date, String time) throws InterruptedException {
        navigateToPinchInOut();
        wait.until(ExpectedConditions.presenceOfElementLocated(setDate));
        clearDate();
        setPunchInDate(date);
        wait.until(ExpectedConditions.presenceOfElementLocated(setTime));
        Thread.sleep(400);
        clearTime();
        Thread.sleep(400);
        wait.until(ExpectedConditions.presenceOfElementLocated(setTime));
        setPunchInTime(time);
        pressSaveBtn();  // Assuming this is the button to finalize punch in
    }
    public void punchOut(String date,String time) throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(setDate));
        clearDate();
        setPunchInDate(date);
        wait.until(ExpectedConditions.presenceOfElementLocated(setTime));
        Thread.sleep(400);
        clearTime();
        Thread.sleep(400);
        wait.until(ExpectedConditions.presenceOfElementLocated(setTime));
        setPunchInTime(time);
        pressSaveBtn();  // Assuming this is the button to finalize punch in

    }
    public void navigateToMyRecords(){
        timeDriver.findElement(attendanceButton).click();
        timeDriver.findElement(myRecordsBtn).click();

    }
    public String getPunchingInTime(){
        return timeDriver.findElement(punchedInTime).getText();
    }

    public void getAllRecords() throws InterruptedException {
        timeDriver.findElement(attendanceButton);
        navigateToMyRecords();
        wait.until(ExpectedConditions.presenceOfElementLocated(setDate));
        pressSaveBtn();
        wait.until(ExpectedConditions.visibilityOfElementLocated(recordsFound));
    }

    public void addName(String name){
        timeDriver.findElement(projectInfo).click();
        timeDriver.findElement(customerInfo).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(AddButton));
        timeDriver.findElement(AddButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(customerName));
        timeDriver.findElement(customerName).sendKeys(name);
    }
    public void clickSaveButton(){
        timeDriver.findElement(saveBtn).click();
    }
    public void navigateToCustomerNames(){
        timeDriver.findElement(projectInfo).click();
        timeDriver.findElement(customerInfo).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(recordsFound));
    }
    public String[] getAllNames(){
        List<WebElement> record=timeDriver.findElements(recordNames);
        List <String> names=new ArrayList<>();
        for(WebElement name:record){
            names.add(name.getText());
        }
        return names.toArray(new String[0]);
    }
    public String getCurrentURL(){
        return timeDriver.getCurrentUrl();
    }


}

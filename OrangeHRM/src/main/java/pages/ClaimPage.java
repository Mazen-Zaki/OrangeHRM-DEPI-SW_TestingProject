package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ClaimPage {
    WebDriver claimDriver;
    WebDriverWait wait;
    public ClaimPage(WebDriver driver){
        claimDriver=driver;
    }
    /**********************************      Locators      **********************************/
    By EmployeeName= By.xpath("//input[@placeholder=\"Type for hints...\"]");
    By claimButton=By.xpath("//span[text()='Claim']");
    By searchBtn=By.xpath("//button[@type='submit']");
    By resetBtn= By.xpath("//button[@type='reset']");
    By invalidMessage=By.xpath("//span[text()='Invalid']");
    By NamesLocator=By.xpath("(//input[@placeholder=\"Type for hints...\"])[1]");
    By claimRecordName=By.xpath("//div[@role='row']//div[@role='cell'][2]");
    By IDLocator=By.xpath("(//input[@placeholder=\"Type for hints...\"])[2]");
    By IDRecord=By.xpath("//div[@role='row']//div[@role='cell'][1]");
    By eventLocator=By.xpath("(//div[@class='oxd-select-text-input'])[1]");
    By statusLocator=By.xpath("(//div[@class='oxd-select-text-input'])[2]");
    By statusRecord=By.xpath("//div[@role='row']//div[@role='cell'][7]");
    By eventsRecord=By.xpath("//div[@role='row']//div[@role='cell'][3]");
    By recordsFound=By.xpath("//div[contains(@class, 'orangehrm-horizontal-padding')]//span");
    By datesFound=By.xpath("//div[@role='row']//div[@role='cell'][6]");
    By table=By.cssSelector(".orangehrm-container");
    By fromDate=By.xpath("(//input[@placeholder='yyyy-dd-mm'])[1]");
    By currencyLocator=By.xpath("(//div[@class='oxd-select-text-input'])[2]");
    By submitClaimOption=By.linkText("Submit Claim");
    By generatedID=By.xpath("(//input[@class=\"oxd-input oxd-input--active\"])[2]");
    By generatedStatus=By.xpath("(//input[@class=\"oxd-input oxd-input--active\"])[4]");
    By addExpenseBtn=By.xpath("(//button[@type='button' and contains(., 'Add')])[1]");
    By addExpenseTitle=By.xpath("//div[@class=\"orangehrm-modal-header\" and contains(.,'Add Expense')]");
    By saveBtn=By.xpath("//button[text()=' Save ']");
    By amountField=By.xpath("(//div[@data-v-957b4417]//input[@data-v-1f99f73c])[6]");
    By submitButton=By.xpath("//button[text()=' Submit ']");
    By ExpensesWindow=By.xpath("//div[@role=\"document\"]");
    By MyClaims=By.linkText("My Claims");
    By configurationBtn=By.xpath("//span[@class='oxd-topbar-body-nav-tab-item']");
    By expenseTypeBtn=By.linkText("Expense Types");
    By eventStatus=By.xpath("//div[@class=\"oxd-select-text-input\"]");
    By eventsFound=By.xpath("//div[@class=\"oxd-table-cell oxd-padding-cell\"][2]");

    /**********************************      Actions      **********************************/

    public void navigateToClaimPage(){
        claimDriver.findElement(claimButton).click();
        wait = new WebDriverWait(claimDriver, Duration.ofSeconds(10));
    }
    public void navigateToConfiguration(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(configurationBtn));
        claimDriver.findElement(configurationBtn).click();
    }
    public void chooseEvent(String event){
        claimDriver.findElement(eventStatus).click();
        WebElement eventOption = claimDriver.findElement(By.xpath("//div[@role='option']//span[text()='" + event + "']"));
        eventOption.click();

    }

    public void navigateToExpenseType(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(expenseTypeBtn));
        claimDriver.findElement(expenseTypeBtn).click();
    }
    public WebElement recordTable(){
        return claimDriver.findElement(table);
    }
    public WebElement navigateToMyClaims(){
        return claimDriver.findElement(MyClaims);
    }

    public void writeEventName(String newEvent){
        claimDriver.findElement(EmployeeName).sendKeys(newEvent);
    }
    public void clickOnSearchBtn(){
        claimDriver.findElement(searchBtn).click();
    }
    public String[] getAllNameRecords() {
        // Wait for the table elements to be visible
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(claimRecordName));
        List<WebElement> NameElement=claimDriver.findElements(claimRecordName);
        List<String> headerTexts = new ArrayList<>();
        for (WebElement header : NameElement) {
            // Extract the text from each element and add it to the list
            headerTexts.add(header.getText());
        }

        // Convert the list of header texts to a string array
        return headerTexts.toArray(new String[0]);
    }

    public String[] getAllIDRecords() {
        // Wait for the table elements to be visible
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(IDRecord));
        List<WebElement> NameElement=claimDriver.findElements(IDRecord);
        List<String> IDs = new ArrayList<>();
        for (WebElement header : NameElement) {
            // Extract the text from each element and add it to the list
            IDs.add(header.getText());
        }

        // Convert the list of header texts to a string array
        return IDs.toArray(new String[0]);
    }
    public WebElement expensesWindow(){
        return claimDriver.findElement(ExpensesWindow);
    }

    public String[] getAllEventRecords() {
        // Wait for the table elements to be visible
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(eventsRecord));
        List<WebElement> NameElement=claimDriver.findElements(eventsRecord);
        List<String> events = new ArrayList<>();
        for (WebElement header : NameElement) {
            // Extract the text from each element and add it to the list
            events.add(header.getText());
        }

        // Convert the list of header texts to a string array
        return events.toArray(new String[0]);
    }
    public String[] getAllStatusRecords() {
        // Wait for the table elements to be visible
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(recordsFound));
        List<WebElement> availableStatus=claimDriver.findElements(statusRecord);
        List<String> status = new ArrayList<>();
        if (!availableStatus.isEmpty())
            for (WebElement header : availableStatus) {
                // Extract the text from each element and add it to the list
                status.add(header.getText());
            }
        // Convert the list of header texts to a string array
        return status.toArray(new String[0]);
    }
    public String[] getAllDates(){
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(recordsFound));
        List<WebElement> recordDates=claimDriver.findElements(datesFound);
        List <String> dates=new ArrayList<>();
        if (!recordDates.isEmpty())
            for (WebElement header : recordDates) {
                // Extract the text from each element and add it to the list
                dates.add(header.getText());
            }
        return dates.toArray(new String[0]);
    }
    public String[] getEvents(){
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(recordsFound));
        List<WebElement> recordEvents=claimDriver.findElements(eventsFound);
        List <String> events=new ArrayList<>();
        if (!recordEvents.isEmpty())
            for (WebElement header : recordEvents) {
                // Extract the text from each element and add it to the list
                events.add(header.getText());
            }
        return events.toArray(new String[0]);
    }




    public WebElement isInvalidMessageIsShown(){
        return claimDriver.findElement(invalidMessage);
    }
    public WebElement RecordsFound(){
        wait.until(ExpectedConditions.presenceOfElementLocated(recordsFound));

        return claimDriver.findElement(recordsFound);
    }

    public void clearSearch(){
        claimDriver.findElement(resetBtn).click();
    }

    public String getWrittenValue(){
        return claimDriver.findElement(EmployeeName).getAttribute("value");
    }

    public void SearchByName(String name){
        wait.until(ExpectedConditions.visibilityOfElementLocated(NamesLocator));
        claimDriver.findElement(NamesLocator).sendKeys(name);
    }
    public void SearchByEvent(String event){
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(eventLocator));
        WebElement eventElement=claimDriver.findElement(eventLocator);
        eventElement.click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@role='option']//span[text()='" + event + "']")));
        WebElement eventOption = claimDriver.findElement(By.xpath("//div[@role='option']//span[text()='" + event + "']"));
        eventOption.click();
    }
    public void setCurrency(String currency){
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(currencyLocator));
        WebElement currencyElement=claimDriver.findElement(currencyLocator);
        currencyElement.click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@role='option']//span[text()='" + currency + "']")));
        WebElement currencyOption = claimDriver.findElement(By.xpath("//div[@role='option']//span[text()='" + currency + "']"));
        currencyOption.click();
    }
    public void clickOnSubmitButton(){
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        claimDriver.findElement(submitButton).click();
    }
    public void SearchByStatus(String status){
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(statusLocator));
        WebElement eventElement=claimDriver.findElement(statusLocator);
        eventElement.click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@role='option']//span[text()='" + status + "']")));
        WebElement eventOption = claimDriver.findElement(By.xpath("//div[@role='option']//span[text()='" + status + "']"));
        eventOption.click();
    }
    public void searchByFromDate(String date){
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(fromDate));
        WebElement eventElement=claimDriver.findElement(fromDate);
        eventElement.sendKeys(date);
    }
    public void searchByID(String id){
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(IDLocator));
        WebElement eventElement=claimDriver.findElement(IDLocator);
        eventElement.sendKeys(id);
    }
    public void submitClaim(String event,String currency){
        wait.until(ExpectedConditions.visibilityOfElementLocated(submitClaimOption));
        claimDriver.findElement(submitClaimOption).click();
        SearchByEvent(event);
        setCurrency(currency);
        claimDriver.findElement(searchBtn).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(recordsFound));
    }
    public String submittedID() {
        return claimDriver.findElement(generatedID).getAttribute("value");
    }public String submittedStatus() {
        return claimDriver.findElement(generatedStatus).getText();
    }
    public void addExpenses(String event, String date, double amount) {
        try {
            claimDriver.findElement(addExpenseBtn).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(addExpenseTitle));
            SearchByEvent(event);
            searchByFromDate(date);
            claimDriver.findElement(amountField).sendKeys(String.valueOf(amount));
            claimDriver.findElement(saveBtn).click();
        } catch (Exception e) {
            System.out.println("Error while adding expenses: " + e.getMessage());
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(recordsFound));
    }

}

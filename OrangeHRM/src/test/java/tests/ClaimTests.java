package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.ClaimPage;
import pages.LoginPage;

import java.time.Duration;

public class ClaimTests {
    BaseTest baseTest;
    String Username = "Admin";
    String Password = "admin123";
    String baseUrl = "https://opensource-demo.orangehrmlive.com/";
    WebDriver driver;
    String searchedEmployee="Clarence Lombardi";
    LoginPage loginPage;
    WebDriverWait wait;
    ClaimPage claimPage;
    String referenceID="202307180000003";
    String searchedEvent="Accommodation";
    String searchedStatus="Approved";
    String searchedFromDate="2023-18-07";
    String currencyClaim="Albanian Lek";
    String submittedID="";
    String submittedStatus="";
    String searchedEventExpense="Accommodation";
    String searchedStatusExpense="Active";


    @BeforeClass
    public void setUp(){
        driver=new ChromeDriver();
        driver.get(baseUrl);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        loginPage = new LoginPage(driver);
        try {
            claimPage = new ClaimPage(driver);
        } catch (Exception e) {
            System.out.println("Failed to initialize Directory: " + e.getMessage());
        }
        System.out.println("Loading Login page is done successfully");

    }
    @Test(priority = 1)
    public void login() {
        loginPage.enterUsername(Username);
        loginPage.enterPassword(Password);
        loginPage.clickLoginButton();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Dashboard']")));
        System.out.println("Login is done successfully");
        claimPage.navigateToClaimPage();
    }
    @Test(dependsOnMethods = "login")
    public void SearchByName(){
        boolean isExist=false;
        String[] originalNames;
        originalNames=claimPage.getAllNameRecords();
        for(String names:originalNames){
            if(names.equals(searchedEmployee))
                isExist=true;
            break;
        }
        claimPage.SearchByName(searchedEmployee);
        claimPage.clickOnSearchBtn();
        if(isExist)
            Assert.assertFalse(claimPage.isInvalidMessageIsShown().isDisplayed());
        else
            Assert.assertTrue(claimPage.isInvalidMessageIsShown().isDisplayed());
    }

    @Test(dependsOnMethods = "login")
    public void SearchByID(){
        boolean isExist=false;
        String[] originalIDs;
        originalIDs=claimPage.getAllIDRecords();
        for(String names:originalIDs){
            if(names.equals(referenceID)){
                isExist=true;
                break;}
        }
        claimPage.searchByID(referenceID);
        claimPage.clickOnSearchBtn();
        if(isExist){
            originalIDs=claimPage.getAllIDRecords();
            Assert.assertEquals(originalIDs[0],referenceID);
        }else
            Assert.assertEquals(claimPage.RecordsFound().getText(),"No Records Found");
    }
    @Test(dependsOnMethods = "login")
    public void searchByEvent(){
        int counter = 0, counter2 = 0;
        String[] originalEvents;
        originalEvents= claimPage.getAllEventRecords();
        for(String names:originalEvents)
            if(names.equals(searchedEvent))
                counter++;
        claimPage.SearchByEvent(searchedEvent);
        claimPage.clickOnSearchBtn();
        wait.until(ExpectedConditions.visibilityOf(claimPage.recordTable()));
        if (counter != 0) {
            originalEvents= claimPage.getAllEventRecords();
            for (String name : originalEvents)
                if (name.equals(searchedEvent))
                    counter2++;
            Assert.assertEquals(counter, counter2);
        }
        else{
            originalEvents = claimPage.getAllStatusRecords();
            for (String name : originalEvents)
                if (name.equals(searchedStatus))
                    counter2++;
            Assert.assertEquals(counter, counter2);
        }
    }
    @Test(dependsOnMethods = "login")
    public void searchByStatus() {
        int counter = 0, counter2 = 0;
        String[] originalStatus;
        originalStatus = claimPage.getAllStatusRecords();
        for (String names : originalStatus)
            if (names.equals(searchedStatus))
                counter++;
        claimPage.SearchByStatus(searchedStatus);
        claimPage.clickOnSearchBtn();
        wait.until(ExpectedConditions.visibilityOf(claimPage.recordTable()));
        if (counter != 0) {
            originalStatus = claimPage.getAllStatusRecords();
            for (String name : originalStatus)
                if (name.equals(searchedStatus))
                    counter2++;}
        Assert.assertEquals(counter, counter2);
    }
    @Test(dependsOnMethods = "login")
    public void searchByFromDate() {
        int counter = 0, counter2 = 0;
        String[] originalDates;
        originalDates = claimPage.getAllDates();
        for (String names : originalDates)
            if (names.equals(searchedFromDate))
                counter++;
        claimPage.searchByFromDate(searchedFromDate);
        claimPage.clickOnSearchBtn();
        wait.until(ExpectedConditions.visibilityOf(claimPage.recordTable()));
        if (counter != 0) {
            originalDates = claimPage.getAllDates();
            for (String name : originalDates)
                if (name.equals(searchedFromDate))
                    counter2++;}
        Assert.assertEquals(counter, counter2);
    }
    @Test(dependsOnMethods = "login")
    public void searchByEmployeeNameAndID() {
        int counter = 0, counter2 = 0;
        String[] originalNames;
        String[] originalIDs;
        originalIDs=claimPage.getAllIDRecords();
        originalNames=claimPage.getAllNameRecords();
        for(int i=0;i<originalIDs.length;i++){
            if((originalIDs[i].equals(referenceID))&&(originalNames[i].equals(searchedEmployee)))
                counter++;
        }
        claimPage.SearchByName(searchedEmployee);
        claimPage.searchByID(referenceID);
        claimPage.clickOnSearchBtn();
        wait.until(ExpectedConditions.visibilityOf(claimPage.recordTable()));
        if (counter != 0) {
            originalIDs=claimPage.getAllIDRecords();
            originalNames=claimPage.getAllNameRecords();
            for(int i=0;i<originalIDs.length;i++)
                if((originalIDs[i].equals(referenceID))&&(originalNames[i].equals(searchedEmployee)))
                    counter2++;
                else
                    Assert.assertTrue(false);
        }
        Assert.assertEquals(counter, counter2);
    }
    @Test(dependsOnMethods = "login")
    public void claimSubmission(){
        boolean isExist=false;
        claimPage.submitClaim(searchedEvent,currencyClaim);
        submittedID=claimPage.submittedID();
        System.out.println("submitted id is "+submittedID);
        submittedStatus=claimPage.submittedStatus();
        claimPage.addExpenses(searchedEvent,searchedFromDate,500);
        wait.until(ExpectedConditions.invisibilityOf(claimPage.expensesWindow()));
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        claimPage.clickOnSubmitButton();// Simulates pressing the PAGE DOWN key.
        claimPage.navigateToMyClaims().click();
        wait.until(ExpectedConditions.visibilityOf(claimPage.RecordsFound()));
        String[] originalIDs;
        originalIDs=claimPage.getAllIDRecords();
        for(String names:originalIDs){
            if(names.equals(submittedID))
                isExist=true;
        }
        if(isExist)
            Assert.assertTrue(true);
        else Assert.assertTrue(false);
    }
    @Test(dependsOnMethods = "login")
    public void searchExpenses(){
        claimPage.navigateToConfiguration();
        claimPage.navigateToExpenseType();
        boolean isExist=false;
        String []events;
        wait.until(ExpectedConditions.visibilityOf(claimPage.recordTable()));
        events=claimPage.getEvents();
        for(String event:events){
            System.out.println("Original events : "+ event);
            if(event.equals(searchedEventExpense)){
                isExist=true;break;}
        }
        claimPage.SearchByName(searchedEventExpense);
        claimPage.chooseEvent(searchedStatusExpense);
        claimPage.clickOnSearchBtn();
        wait.until(ExpectedConditions.visibilityOf(claimPage.recordTable()));
        events=claimPage.getEvents();

        for(String event:events)
            if(event.equals(searchedEventExpense))
                isExist=true;
        if(isExist)
            Assert.assertTrue(true);
        else Assert.assertTrue(false);

    }


}

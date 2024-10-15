package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.DirectoryPage;
import pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class DirectoryTests {
    WebDriver driver;
    String Username = "Admin";
    String Password = "admin123";
    String baseUrl = "https://opensource-demo.orangehrmlive.com/";
    DirectoryPage directory;
    LoginPage loginPage;
    WebDriverWait wait;
    String SearchedByEmployeeName="User1";

    @BeforeClass
    public void Setup(){
        driver = new EdgeDriver();  // Instantiate WebDriver
        driver.get(baseUrl);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        loginPage = new LoginPage(driver);
        try {
            directory = new DirectoryPage(driver);
        } catch (Exception e) {
            System.out.println("Failed to initialize Directory: " + e.getMessage());
        }
        System.out.println("Loading is done successfully");
    }

    @Test(priority = 1)
    public void login(){
        loginPage.enterUsername(Username);
        loginPage.enterPassword(Password);
        loginPage.clickLoginButton();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Dashboard']")));
        directory.navigateToDirectory();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Type for hints...']")));
        System.out.println("Login is done successfully");
    }


    @Test(priority = 2,dependsOnMethods = "login")
    public void SearchByEmployeeName() {
//        directory.clearSearch();
        String[] allNames = directory.getAllRecords();
        String specificName = allNames[0];
        directory.writeEmployeeName(specificName);
        //System.out.println(directory.getWrittenValue());
        directory.clickOnSearchBtn();
        String[] searchedNames = directory.getAllRecords();
        if (searchedNames[0].equals(specificName)) {////span[text()='Invalid']
            Assert.assertFalse(directory.isInvalidMessageIsShown().isDisplayed());
        }
    }

    @Test(priority = 3,dependsOnMethods = "login")
    public void SearchBySubEmployeeName() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".oxd-label")));
        directory.clearSearch();
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".orangehrm-container")));
        String[] allNames = directory.getAllRecords();
        String specificName = allNames[0];
        String subName=specificName.substring(0,3);
        directory.writeEmployeeName(subName);
        directory.clickOnSearchBtn();
        String[] searchedNames = directory.getAllRecords();
        if (searchedNames[0].equals(specificName)) {////span[text()='Invalid']
            Assert.assertFalse(directory.isInvalidMessageIsShown().isDisplayed());
        }}
        catch(Exception err){
            System.out.println(err.toString());
            }
    }
    @Test(priority = 4,dependsOnMethods = "login")
    public void resetButton() {
        // Write a name in the employee name field
        directory.writeEmployeeName("Hello");

        // Clear the search input
//        directory.clearSearch();
        driver.findElement(By.xpath("//button[@type='reset']")).click();

        // Get the written value (assumed to be the current value in the text field)
        String writtenValue = directory.getWrittenValue();

        // Assert that the value is empty after clearing
        Assert.assertTrue(writtenValue.isEmpty(), "The search input was not cleared.");
    }
//    @Test(priority =5,dependsOnMethods = "login")
//    public void writeJobTitle(){
//        directory.chooseJobTitle();
//    }
}




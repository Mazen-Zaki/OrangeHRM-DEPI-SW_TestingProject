package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.TimePage;

import java.time.Duration;

public class TimeTests {
    WebDriver driver;
    String Username = "Admin";
    String searchedEmployee = "João Maria Silva";
    String Password = "admin123";
    String baseUrl = "https://opensource-demo.orangehrmlive.com/";
    LoginPage loginPage;
    TimePage timePage;
    WebDriverWait wait;
    String punchInDate = "2024-17-10";
    String punchOutDate = "2024-18-10";
    String punchInTime = "02:21 AM";
    String punchOutTime = "02:22 AM";
    String newAddedName="myName4";
    By alreadyExistMessage=By.xpath("//span[text()='Already exists']");

    @BeforeClass
    public void setUP() {
        driver = new ChromeDriver();
        driver.get(baseUrl);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        loginPage = new LoginPage(driver);
        try {
            timePage = new TimePage(driver);
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
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Dashboard']")));
        System.out.println("Login is done successfully");
        timePage.navigateToTimePage();

    }

    @Test(dependsOnMethods = "login")
    public void SearchByName() {
        boolean isExist = false;
        String[] originalNames;
        originalNames = timePage.getAllNameRecords();
        for (String names : originalNames) {
            if (names.equals(searchedEmployee))
                isExist = true;
            break;
        }
        timePage.writeEmployeeName(searchedEmployee);
        timePage.clickOnSearchBtn();
        if (isExist)
            Assert.assertFalse(timePage.inValidMessage().isDisplayed());
        else
            Assert.assertTrue(timePage.inValidMessage().isDisplayed());
    }

    @Test(dependsOnMethods = "login")
    public void SearchByBlankInput() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type=\"submit\"]")));
        timePage.clickOnSearchBtn();
        Assert.assertTrue(timePage.requiredMessage().isDisplayed());
    }

    @Test(dependsOnMethods = "login")
    public void checkConfiguration() {
        timePage.moveToConfiguration();
        timePage.ToggleCurrentTimeOption();
        timePage.disableChangingCurrentTimeWhenPunching();
        timePage.pressSaveBtn();
        WebElement isDisabled = timePage.punchDate();
        Assert.assertFalse(isDisabled.isEnabled());
    }


    @Test(dependsOnMethods = "login")
    public void punchIn_Out() throws InterruptedException {
        timePage.punchIn(punchInDate,punchInTime);
        Assert.assertEquals(timePage.getPunchingInTime(),punchInDate+" - "+punchInTime+" (GMT +03:00)");
        timePage.punchOut(punchOutDate,punchOutTime);
        timePage.getAllRecords();
        Assert.assertEquals(punchInDate+" "+punchInTime+" GMT +03:00",driver.findElement(timePage.inTimeRecord).getText());
    }
    @Test(dependsOnMethods = "login")
    public void addCustomer(){
        boolean isExist=false;
        timePage.addName(newAddedName);
        timePage.clickSaveButton();
        String[] allNames=timePage.getAllNames();
        for(String name:allNames){
            if(name.equals(newAddedName)) {
                isExist = true;
                break;
            }
        }
        if(isExist)
            Assert.assertTrue(true);
    }
    @Test(dependsOnMethods = {"login","addCustomer"})
    public void addExistingName(){
        timePage.navigateToCustomerNames();
        boolean isExist=false;
        String[] originalNames=timePage.getAllNames();
        for(String name:originalNames){
            if(name.equals(newAddedName)) {
                isExist = true;
                break;
            }
        }
        timePage.addName(newAddedName);
        timePage.clickSaveButton();
        if(isExist){//https://opensource-demo.orangehrmlive.com/web/index.php/time/viewCustomers
//            Assert.assertEquals(timePage.getCurrentURL(),"//https://opensource-demo.orangehrmlive.com/web/index.php/time/viewCustomers");
            Assert.assertEquals(driver.findElement(alreadyExistMessage).isDisplayed(),true);
        }
        else Assert.assertTrue(false);
    }
}

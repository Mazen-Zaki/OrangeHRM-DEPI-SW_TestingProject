package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.TimePage;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class DashboardTests {
    WebDriver driver;
    String Username = "Admin";
    String Password = "admin123";
    String baseUrl = "https://opensource-demo.orangehrmlive.com/";
    LoginPage loginPage;
    WebDriverWait wait;
    DashboardPage dashboardPage;
    @BeforeClass
    public void setUP() {
        driver = new ChromeDriver();
        driver.get(baseUrl);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        loginPage = new LoginPage(driver);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        try {
            dashboardPage = new DashboardPage(driver);
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
        wait.until(ExpectedConditions.visibilityOf(dashboardPage.getDashboardTitle()));
        System.out.println("Login is done successfully");
    }
    @Test(priority =2 , dependsOnMethods = "login")
    public void AssertDashboardAfterLogin(){
        Assert.assertEquals(dashboardPage.getCurrentPageTitle(),"Dashboard");
    }
    @Test(priority =2 , dependsOnMethods = "login")
    public void AssertLeaveRequestsDashboard(){
        Assert.assertEquals(dashboardPage.isLeaveRequestsExist(),true);

    }
    @Test(priority = 3, dependsOnMethods = "login")
    public void AssertRecruitmentApplicationDashboard(){
        Assert.assertEquals(dashboardPage.isRecruitmentApplicationExist(),true);
    }
    @Test(priority = 4, dependsOnMethods = "login")
    public void AssertPerformanceMetricDashboard(){
        Assert.assertEquals(dashboardPage.isPerformanceMetricExist(),true);
    }



}

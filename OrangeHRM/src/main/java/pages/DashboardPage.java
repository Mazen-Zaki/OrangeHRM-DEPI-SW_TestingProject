package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DashboardPage {
    WebDriver dashBoardDriver;
    public DashboardPage(WebDriver driver){
        dashBoardDriver=driver;
    }
    By leaveRequests=By.xpath("//button[@title=\"Leave List\"]");
    By pageTitle=By.xpath("//div[@class=\"oxd-topbar-header-title\"]");
    By recruitmentApplication=By.xpath("//p[text()=\"My Actions\"]");
    By performanceMetric=By.xpath("//span[text()='Performance']");
    By dashboardTitle=By.xpath("//h6[text()='Dashboard']");

    public void navigateToLeaveRequests(){
        dashBoardDriver.findElement(leaveRequests).click();
    }
    public WebElement getDashboardTitle(){
        return dashBoardDriver.findElement(dashboardTitle);
    }
    public String getCurrentPageTitle(){
        return dashBoardDriver.findElement(pageTitle).getText();
    }
    public boolean isLeaveRequestsExist(){
        return dashBoardDriver.findElement(leaveRequests).isDisplayed();
    }
    public boolean isRecruitmentApplicationExist(){
        return dashBoardDriver.findElement(recruitmentApplication).isDisplayed();
    }
    public boolean isPerformanceMetricExist(){
        return dashBoardDriver.findElement(performanceMetric).isDisplayed();
    }



}

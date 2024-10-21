package tests;

import base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AdminPages;
import pages.LoginPage;
import pages.LogoutPage;

import java.time.Duration;

public class AdminTests extends BaseTest
{

    AdminPages adminPages;

    // override the setUp method to initialize the AdminPages
    @BeforeMethod
    public void setUp()
    {
        super.setUp();
        adminPages = new AdminPages(driver);
        login(AdminAccount, AdminPassword);

//         After login, navigate to the 'System Users' page
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
    }

    // Test case: Filter by User Role and Status, then click Search
    @Test(priority = 1, description = "Filter users by role and status")
    public void filterUsersByRoleAndStatus() {
        // Select "Admin" as the user role
        adminPages.selectUserRole("Admin");

        // Select "Enabled" as the status
        adminPages.selectStatus("Enabled");

        // Click Search
        adminPages.clickSearch();

        // Verify the filtered results
        String recordFoundText = adminPages.getRecordsFoundText();
        Assert.assertTrue(recordFoundText.contains("(1) Record Found"), "Filtered results are not correct.");
    }

    // Test case: Reset the filters to default values
    @Test(priority = 2, description = "Reset the user filters")
    public void resetUserFilter() {
        // Click Reset
        adminPages.clickReset();

        // Verify the filters are reset
        Assert.assertTrue(adminPages.isFilterReset(), "Filters were not reset correctly.");
    }
}

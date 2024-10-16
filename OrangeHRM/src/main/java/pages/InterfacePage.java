package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Set;

public class InterfacePage
{
    WebDriver driver;
    LoginPage loginPage;

    // Locators
    By userDropDownButton = By.xpath("//img[@class='oxd-userdropdown-img']");

    By upgradeButton = By.xpath("//a[@class=\"orangehrm-upgrade-link\"]");
    By sideBarButton = By.xpath("//button[@class=\"oxd-icon-button oxd-main-menu-button\"]");
    By searchField = By.xpath("//input[@class=\"oxd-input oxd-input--active\"]");

    By AboutButton = By.xpath("//ul[@class=\"oxd-dropdown-menu\"]//li[1]");
    By SupportButton = By.xpath("//ul[@class=\"oxd-dropdown-menu\"]//li[2]");
    By ChangePasswordButton = By.xpath("//ul[@class=\"oxd-dropdown-menu\"]//li[3]");
    By LogoutButton = By.xpath("//ul[@class=\"oxd-dropdown-menu\"]//li[4]");


    // Constructor
    public InterfacePage(WebDriver driver)
    {
        loginPage = new LoginPage(driver);
        this.driver = driver;
    }

    // Method to Login
    public void login(String username, String password)
    {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
    }

    public void clickUserDropDownButton()
    {
        WebElement userDropDownButtonElement = driver.findElement(userDropDownButton);
        userDropDownButtonElement.click();
    }

    public void clickUpgradeButton()
    {
        String originalWindow = driver.getWindowHandle();
        WebElement upgradeButtonElement = driver.findElement(upgradeButton);
        upgradeButtonElement.click();

        Set<String> windowHandles = driver.getWindowHandles();
        windowHandles.remove(originalWindow);

        String newTabHandle = windowHandles.iterator().next();
        driver.switchTo().window(newTabHandle);
    }

    public String getCurrentPageUrl() {return driver.getCurrentUrl();}

    public void clickSideBarButton()
    {
        WebElement sideBarButtonElement = driver.findElement(sideBarButton);
        sideBarButtonElement.click();
    }

    public boolean isDropdownListFull()
    {
        try
        {
            WebElement aboutButton = driver.findElement(AboutButton);
            WebElement supportButton = driver.findElement(SupportButton);
            WebElement changePasswordButton = driver.findElement(ChangePasswordButton);
            WebElement logoutButton = driver.findElement(LogoutButton);
        }
        catch (NoSuchElementException e)
        {
            // If NoSuchElementException is caught, the test should pass
            return false;
        }

        return true;
    }

    public void enterSearch(String searchKeyword)
    {
        WebElement searchElement = driver.findElement(searchField);
        searchElement.sendKeys(searchKeyword);
    }

    // Method to switch back to the original window
    public void switchBackToOriginalWindow(String originalWindow) {
        driver.close(); // Close the current tab
        driver.switchTo().window(originalWindow); // Switch back to the original tab
    }
}

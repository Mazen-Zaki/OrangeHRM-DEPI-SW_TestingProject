package pages;

import org.openqa.selenium.*;

import java.util.Set;

public class InterfacePage
{
    WebDriver driver;
    LoginPage loginPage;

    // Locators
    By userDropDownButton = By.xpath("//img[@class='oxd-userdropdown-img']");

    By upgradeButton = By.xpath("//a[@class=\"orangehrm-upgrade-link\"]");
    By sideBarButton = By.xpath("//button[@class=\"oxd-icon-button oxd-main-menu-button\"]");
    By searchField = By.xpath("//div[@class=\"oxd-main-menu --fixed\"]//div[@class=\"oxd-main-menu-search\"]//input[@class=\"oxd-input oxd-input--active\"]");

    By AboutButton = By.xpath("//ul[@class=\"oxd-dropdown-menu\"]//li[1]");
    By SupportButton = By.xpath("//ul[@class=\"oxd-dropdown-menu\"]//li[2]");
    By ChangePasswordButton = By.xpath("//ul[@class=\"oxd-dropdown-menu\"]//li[3]");
    By LogoutButton = By.xpath("//ul[@class=\"oxd-dropdown-menu\"]//li[4]");

    By searchSideBarField = By.xpath("//a[@class=\"oxd-main-menu-item\"]");

    By currentPasswordField = By.xpath("//div[@class=\"oxd-form-row\"]//div[@class=\"oxd-grid-item oxd-grid-item--gutters\"]//div[@class='oxd-input-group oxd-input-field-bottom-space']//input[@class='oxd-input oxd-input--active' and @type='password']");
    By newPasswordField = By.xpath("//div[@class=\"oxd-grid-item oxd-grid-item--gutters user-password-cell\"]//div[@class='oxd-input-group oxd-input-field-bottom-space']//input[@class='oxd-input oxd-input--active' and @type='password']");
    By confirmPasswordField = By.xpath("//div[@class=\"oxd-form-row user-password-row\"]//div[@class=\"oxd-grid-2 orangehrm-full-width-grid\"]//div[@class=\"oxd-grid-item oxd-grid-item--gutters\"]//div[@class='oxd-input-group oxd-input-field-bottom-space']//input[@class='oxd-input oxd-input--active' and @type='password']");
    By saveButton = By.xpath("//button[@class=\"oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space\"]");

    By aboutHeader = By.xpath("//div[@class=\"orangehrm-modal-header\"]//h6[@class=\"oxd-text oxd-text--h6 orangehrm-main-title\"]");
    By closeAboutButton = By.xpath("//button[@class=\"oxd-dialog-close-button oxd-dialog-close-button-position\"]");

    By supportHeader = By.xpath("//h6[@class=\"oxd-text oxd-text--h6 orangehrm-main-title\"]");

    // Constructor
    public InterfacePage(WebDriver driver)
    {
        loginPage = new LoginPage(driver);
        this.driver = driver;
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

    public boolean searchAndVerify(String searchKeyword)
    {
        try
        {
            WebElement searchElement = driver.findElement(searchField);
            WebElement searchSideBarElement;
            String resSearch;

            searchElement.sendKeys(Keys.CONTROL + "a");
            searchElement.sendKeys(Keys.DELETE);

            searchElement.sendKeys(searchKeyword);


            searchSideBarElement = driver.findElement(searchSideBarField);
            resSearch =  searchSideBarElement.getText();

            return resSearch.contains(searchKeyword);

        }
        catch (NoSuchElementException e)
        {
            return false;
        }

    }

    public void clickAboutButton()
    {
        WebElement aboutButtonElement = driver.findElement(AboutButton);
        aboutButtonElement.click();
    }

    public boolean isAboutHeaderDisplayed()
    {
        try
        {
            WebElement aboutHeaderElement = driver.findElement(aboutHeader);
        }
        catch (NoSuchElementException e)
        {
            return false;
        }

        return true;
    }

    public void clickCloseAboutButton()
    {
        WebElement closeAboutButtonElement = driver.findElement(closeAboutButton);
        closeAboutButtonElement.click();
    }

    public void clickSupportButton()
    {
        WebElement supportButtonElement = driver.findElement(SupportButton);
        supportButtonElement.click();
    }

    public boolean isSupportHeaderDisplayed()
    {
        try
        {
            WebElement aboutHeaderElement = driver.findElement(supportHeader);
        }
        catch (NoSuchElementException e)
        {
            return false;
        }

        return true;
    }

    public void clickChangePasswordButton()
    {
        WebElement changePasswordButtonElement = driver.findElement(ChangePasswordButton);
        changePasswordButtonElement.click();
    }

    public void setNewPassword(String currentPassword, String newPassword, String confirmPassword)
    {
        WebElement currentPasswordFieldElement = driver.findElement(currentPasswordField);
        WebElement newPasswordFieldElement = driver.findElement(newPasswordField);
        WebElement confirmPasswordFieldElement = driver.findElement(confirmPasswordField);

        currentPasswordFieldElement.sendKeys(currentPassword);
        newPasswordFieldElement.sendKeys(newPassword);
        confirmPasswordFieldElement.sendKeys(confirmPassword);
    }


    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }


    // Method to switch back to the original window
    public void switchBackToOriginalWindow(String originalWindow) {
        driver.close(); // Close the current tab
        driver.switchTo().window(originalWindow); // Switch back to the original tab
    }
}

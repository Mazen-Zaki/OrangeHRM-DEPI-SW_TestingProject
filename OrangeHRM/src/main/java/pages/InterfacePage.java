package pages;

import org.openqa.selenium.*;

public class InterfacePage
{
    WebDriver driver;
    LoginPage loginPage;

    // Locators
    By userDropDownButton = By.xpath("//img[@class='oxd-userdropdown-img']");

    By upgradeButton = By.xpath("//a[@class=\"orangehrm-upgrade-link\"]");
    By sideBarButton = By.xpath("//button[@class=\"oxd-icon-button oxd-main-menu-button\"]");
    By searchField = By.xpath("//input[contains(@class, 'input')]");

    By AboutButton = By.xpath("//ul[@class=\"oxd-dropdown-menu\"]//li[1]");
    By SupportButton = By.xpath("//ul[@class=\"oxd-dropdown-menu\"]//li[2]");
    By ChangePasswordButton = By.xpath("//ul[@class=\"oxd-dropdown-menu\"]//li[3]");
    By LogoutButton = By.xpath("//ul[@class=\"oxd-dropdown-menu\"]//li[4]");

    By searchSideBarField = By.xpath("//a[@class=\"oxd-main-menu-item\"]");

    By currentPasswordField = By.xpath("//div[@class=\"oxd-form-row\"]//div[@class=\"oxd-grid-item oxd-grid-item--gutters\"]//div[@class='oxd-input-group oxd-input-field-bottom-space']//input[@class='oxd-input oxd-input--active' and @type='password']");
    By newPasswordField = By.xpath("//div[@class=\"oxd-grid-item oxd-grid-item--gutters user-password-cell\"]//div[@class='oxd-input-group oxd-input-field-bottom-space']//input[@class='oxd-input oxd-input--active' and @type='password']");
    By confirmPasswordField = By.xpath("//div[@class=\"oxd-form-row user-password-row\"]//div[@class=\"oxd-grid-2 orangehrm-full-width-grid\"]//div[@class=\"oxd-grid-item oxd-grid-item--gutters\"]//div[@class='oxd-input-group oxd-input-field-bottom-space']//input[@class='oxd-input oxd-input--active' and @type='password']");
    By saveButton = By.xpath("//button[@class=\"oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space\"]");
    By successMessageChangePassword = By.xpath("//p[contains(@class, 'oxd-text--toast-title')]");
    By errorMessageChangePassword = By.xpath("//span[@class=\"oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message\"]");

    By aboutHeader = By.xpath("//div[@class=\"orangehrm-modal-header\"]//h6[@class=\"oxd-text oxd-text--h6 orangehrm-main-title\"]");
    By closeAboutButton = By.xpath("//button[@class=\"oxd-dialog-close-button oxd-dialog-close-button-position\"]");


    // Elements
    WebElement userDropDownButtonElement;
    WebElement upgradeButtonElement;

    WebElement sideBarButtonElement;
    WebElement searchFieldElement;
    WebElement searchSideBarElement;
    WebElement aboutButtonElement;
    WebElement supportButtonElement;
    WebElement changePasswordButtonElement;
    WebElement logoutButtonElement;
    WebElement currentPasswordFieldElement;
    WebElement newPasswordFieldElement;
    WebElement confirmPasswordFieldElement;
    WebElement saveButtonElement;
    WebElement successMessageChangePasswordElement;
    WebElement aboutHeaderElement;
    WebElement closeAboutButtonElement;
    WebElement errorMessageChangePasswordElement;


    // Constructor
    public InterfacePage(WebDriver driver)
    {
        loginPage = new LoginPage(driver);
        this.driver = driver;
    }

    public void clickUserDropDownButton()
    {
        userDropDownButtonElement = driver.findElement(userDropDownButton);
        userDropDownButtonElement.click();
    }

    public void clickUpgradeButton()
    {
        upgradeButtonElement = driver.findElement(upgradeButton);
        upgradeButtonElement.click();

        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
    }

    public String getCurrentPageUrl() {return driver.getCurrentUrl();}

    public void clickSideBarButton()
    {
        sideBarButtonElement = driver.findElement(sideBarButton);
        sideBarButtonElement.click();
    }

    public boolean isDropdownListFull()
    {
        try
        {
            aboutButtonElement = driver.findElement(AboutButton);
            supportButtonElement = driver.findElement(SupportButton);
            changePasswordButtonElement = driver.findElement(ChangePasswordButton);
            logoutButtonElement = driver.findElement(LogoutButton);
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
            searchFieldElement = driver.findElement(searchField);

            searchFieldElement.sendKeys(Keys.CONTROL + "a", Keys.DELETE);

            searchFieldElement.sendKeys(searchKeyword);

            searchSideBarElement = driver.findElement(searchSideBarField);
            String resSearch = searchSideBarElement.getText();

            return resSearch.contains(searchKeyword);
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public void clickAboutButton()
    {
        aboutButtonElement = driver.findElement(AboutButton);
        aboutButtonElement.click();
    }

    public boolean isAboutHeaderDisplayed()
    {
        try
        {
            aboutHeaderElement = driver.findElement(aboutHeader);
        }
        catch (NoSuchElementException e)
        {
            return false;
        }

        return true;
    }

    public void clickCloseAboutButton()
    {
        closeAboutButtonElement = driver.findElement(closeAboutButton);
        closeAboutButtonElement.click();
    }

    public void clickSupportButton()
    {
        supportButtonElement = driver.findElement(SupportButton);
        supportButtonElement.click();
    }

    public void clickChangePasswordButton()
    {
        changePasswordButtonElement = driver.findElement(ChangePasswordButton);
        changePasswordButtonElement.click();
    }


    public void setNewPassword(String currentPassword, String newPassword, String confirmPassword)
    {
        currentPasswordFieldElement = driver.findElement(currentPasswordField);
        newPasswordFieldElement = driver.findElement(newPasswordField);
        confirmPasswordFieldElement = driver.findElement(confirmPasswordField);
        saveButtonElement = driver.findElement(saveButton);


        currentPasswordFieldElement.sendKeys(currentPassword);
        newPasswordFieldElement.sendKeys(newPassword);
        confirmPasswordFieldElement.sendKeys(confirmPassword);
        saveButtonElement.click();
    }


    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }


    public boolean isPasswordChanged()
    {
        String errorMessage;
        try
        {
            successMessageChangePasswordElement = driver.findElement(successMessageChangePassword);
            errorMessage = successMessageChangePasswordElement.getText();

            return errorMessage.contains("Success");
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }
}

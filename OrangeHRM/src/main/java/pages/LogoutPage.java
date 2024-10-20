package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LogoutPage
{
    WebDriver driver;

    // Locators
    By userDropDownButton = By.xpath("//img[@class='oxd-userdropdown-img']");
    By logoutButton = By.xpath("//a[@href='/web/index.php/auth/logout']");

    // Element
    WebElement userDropDownButtonElement;
    WebElement logoutButtonElement;

    // Constructor
    public LogoutPage(WebDriver driver)
    {
        this.driver = driver;
    }

    public void clickUserDropDownButton()
    {
        userDropDownButtonElement = driver.findElement(userDropDownButton);
        userDropDownButtonElement.click();
    }

    public void clickLogoutButtonButton()
    {
        logoutButtonElement = driver.findElement(logoutButton);
        logoutButtonElement.click();
    }


}

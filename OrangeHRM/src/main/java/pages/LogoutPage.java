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

    // Constructor
    public LogoutPage(WebDriver driver)
    {
        this.driver = driver;
    }

    public void clickUserDropDownButton()
    {
        WebElement userDropDownButtonElement = driver.findElement(userDropDownButton);
        userDropDownButtonElement.click();
    }

    public void clickLogoutButtonButton()
    {
        WebElement logoutButtonElement = driver.findElement(logoutButton);
        logoutButtonElement.click();
    }


}

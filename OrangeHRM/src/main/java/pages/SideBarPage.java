package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SideBarPage
{
    WebDriver driver;
    InterfacePage interfacePage;

    // Locators
    By searchSideBarField = By.xpath("//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name']");

    // Elements
    WebElement searchSideBarElement;


    // Constructor
    public SideBarPage(WebDriver driver)
    {
        this.driver = driver;
        interfacePage = new InterfacePage(driver);
    }

    public boolean isThePageDisplayed(String pageName)
    {
        String resUrl;
        try {
            pageName = pageName.toLowerCase();
            interfacePage.searchAndVerify(pageName);

            if (pageName.contains("my inf"))
                pageName = "viewPersonalDetails";

            searchSideBarElement = driver.findElement(searchSideBarField);
            searchSideBarElement.click();

            resUrl = interfacePage.getCurrentUrl();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }

        return resUrl.contains(pageName);
    }
}

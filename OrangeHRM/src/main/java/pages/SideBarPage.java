package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SideBarPage
{
    WebDriver driver;
    InterfacePage interfacePage;


    By searchSideBarField = By.xpath("//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name']");
    By pageHeader = By.xpath("//h6[@class='oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']");

    public SideBarPage(WebDriver driver)
    {
        this.driver = driver;
        interfacePage = new InterfacePage(driver);
    }

    public boolean isThePageDisplayed(String pageName)
    {
        WebElement searchSideBarElement;
        String resUrl;

        try {
            pageName = pageName.toLowerCase();
            interfacePage.searchAndVerify(pageName);

            searchSideBarElement = driver.findElement(searchSideBarField);
            searchSideBarElement.click();

            resUrl = interfacePage.getCurrentUrl();
        } catch (NoSuchElementException e) {
            return false;
        }

        return resUrl.contains(pageName);
    }

    public void clickSideBarElement()
    {
        WebElement searchSideBarElement = driver.findElement(searchSideBarField);
        searchSideBarElement.click();
    }

    public void getSideBarElementText()
    {
        WebElement searchSideBarElement = driver.findElement(searchSideBarField);
        searchSideBarElement.getText();
    }

    public boolean isSideBarElementDisplayed()
    {
        try
        {
            WebElement searchSideBarElement = driver.findElement(searchSideBarField);
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
        return true;
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}

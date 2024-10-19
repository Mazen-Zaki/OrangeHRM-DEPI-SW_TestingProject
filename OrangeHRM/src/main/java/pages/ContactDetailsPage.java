package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactDetailsPage {
    private WebDriver driver;

    private By addressField = By.name("contact[street1]");
    private By cityField = By.name("contact[city]");
    private By saveButton = By.xpath("//button[text()='Save']");
    private By successMessage = By.className("oxd-toast-content");

    public ContactDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillContactDetails(String address, String city) {
        driver.findElement(addressField).clear();
        driver.findElement(addressField).sendKeys(address);
        driver.findElement(cityField).clear();
        driver.findElement(cityField).sendKeys(city);
        driver.findElement(saveButton).click();
    }

    public boolean isSuccessMessageDisplayed() {
        return driver.findElements(successMessage).size() > 0;
    }
}

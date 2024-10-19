package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EmergencyContactsPage {
    private WebDriver driver;

    private By addButton = By.xpath("//button[text()='Add']");
    private By contactNameField = By.name("emgcontacts[name]");
    private By relationshipField = By.name("emgcontacts[relationship]");
    private By saveButton = By.xpath("//button[text()='Save']");
    private By deleteButton = By.xpath("//button[text()='Delete']");
    private By successMessage = By.className("oxd-toast-content");

    public EmergencyContactsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addEmergencyContact(String name, String relationship) {
        driver.findElement(addButton).click();
        driver.findElement(contactNameField).clear();
        driver.findElement(contactNameField).sendKeys(name);
        driver.findElement(relationshipField).clear();
        driver.findElement(relationshipField).sendKeys(relationship);
        driver.findElement(saveButton).click();
    }

    public void deleteEmergencyContact() {
        driver.findElement(deleteButton).click();
    }

    public boolean isSuccessMessageDisplayed() {
        return driver.findElements(successMessage).size() > 0;
    }
}

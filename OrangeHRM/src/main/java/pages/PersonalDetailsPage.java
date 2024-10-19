package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalDetailsPage {
    private WebDriver driver;

    private By firstNameField = By.name("personal[txtEmpFirstName]");
    private By lastNameField = By.name("personal[txtEmpLastName]");
    private By saveButton = By.xpath("//button[text()='Save']");
    private By successMessage = By.className("oxd-toast-content");

    public PersonalDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void updatePersonalDetails(String firstName, String lastName) {
        driver.findElement(firstNameField).clear();
        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).clear();
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(saveButton).click();
    }

    public boolean isSuccessMessageDisplayed() {
        return driver.findElements(successMessage).size() > 0;
    }
}

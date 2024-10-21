package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalDetailsPage {
    private WebDriver driver;

    // Locators for fields to update
    private By firstNameField = By.name("firstName");
    private By lastNameField = By.name("lastName");
    private By saveButton = By.xpath("//button[text()='Save']");

    public PersonalDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void updateDetails(String newFirstName, String newLastName) {
        driver.findElement(firstNameField).clear();
        driver.findElement(firstNameField).sendKeys(newFirstName);
        driver.findElement(lastNameField).clear();
        driver.findElement(lastNameField).sendKeys(newLastName);
    }

    public void saveChanges() {
        driver.findElement(saveButton).click();
    }

    public boolean isUpdateSuccessful() {
        // Add validation logic if there's a success message or check if the new details are displayed.
        // Assuming success message appears:
        return driver.findElements(By.xpath("//div[@class='oxd-toast--success']")).size() > 0;
    }
}

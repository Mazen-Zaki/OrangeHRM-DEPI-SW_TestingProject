package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyInfoPage {
    private WebDriver driver;
    
    private By contactDetailsTab = By.xpath("//a[@href='/web/index.php/pim/contactDetails']");
    private By personalDetailsTab = By.xpath("//a[@href='/web/index.php/pim/viewPersonalDetails']");
    private By emergencyContactsTab = By.xpath("//a[@href='/web/index.php/pim/viewEmergencyContacts']");

    public MyInfoPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToContactDetails() {
        driver.findElement(contactDetailsTab).click();
    }

    public void navigateToPersonalDetails() {
        driver.findElement(personalDetailsTab).click();
    }

    public void navigateToEmergencyContacts() {
        driver.findElement(emergencyContactsTab).click();
    }
}


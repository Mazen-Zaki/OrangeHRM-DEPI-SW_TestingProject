package tests;

<<<<<<< HEAD
package tests;
import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.MyInfoPage;
import pages.ContactDetailsPage;
import pages.PersonalDetailsPage;
import pages.EmergencyContactsPage;
=======
import base.BaseTest2;
import pages.ContactDetailsPage;
import pages.EmergencyContactsPage;
import pages.MyInfoPage;
import pages.PersonalDetailsPage;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;
>>>>>>> 04dd1093898f43f4f8667f5510879fbcdef180b5

<<<<<<< HEAD

public class MyInfoTest extends BaseTest2 {
=======
public class MyInfoTests extends BaseTest2 {
>>>>>>> 04dd1093898f43f4f8667f5510879fbcdef180b5
    private MyInfoPage myInfoPage;
    private ContactDetailsPage contactDetailsPage;
    private PersonalDetailsPage personalDetailsPage;
    private EmergencyContactsPage emergencyContactsPage;

    @Test
    public void testSaveContactDetails() {
        myInfoPage = new MyInfoPage(driver);
        myInfoPage.navigateToContactDetails();

        contactDetailsPage = new ContactDetailsPage(driver);
        contactDetailsPage.fillContactDetails("1234 Elm St", "Gotham");

        assertTrue(contactDetailsPage.isSuccessMessageDisplayed(), "Contact details should be saved successfully.");
    }

    @Test
    public void testSavePersonalDetails() {
        myInfoPage = new MyInfoPage(driver);
        myInfoPage.navigateToPersonalDetails();

        personalDetailsPage = new PersonalDetailsPage(driver);
        personalDetailsPage.updatePersonalDetails("Bruce", "Wayne");

        assertTrue(personalDetailsPage.isSuccessMessageDisplayed(), "Personal details should be saved successfully.");
    }

    @Test
    public void testAddEmergencyContact() {
        myInfoPage = new MyInfoPage(driver);
        myInfoPage.navigateToEmergencyContacts();

        emergencyContactsPage = new EmergencyContactsPage(driver);
        emergencyContactsPage.addEmergencyContact("Alfred Pennyworth", "Butler");

        assertTrue(emergencyContactsPage.isSuccessMessageDisplayed(), "Emergency contact should be added successfully.");
    }

    @Test
    public void testDeleteEmergencyContact() {
        myInfoPage = new MyInfoPage(driver);
        myInfoPage.navigateToEmergencyContacts();

        emergencyContactsPage = new EmergencyContactsPage(driver);
        emergencyContactsPage.deleteEmergencyContact();

        assertTrue(emergencyContactsPage.isSuccessMessageDisplayed(), "Emergency contact should be deleted successfully.");
    }
}

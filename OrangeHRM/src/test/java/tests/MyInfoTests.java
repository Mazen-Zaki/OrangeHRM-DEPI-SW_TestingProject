package tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MyInfoTest extends BaseTest2 {
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

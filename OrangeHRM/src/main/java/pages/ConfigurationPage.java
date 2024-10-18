
package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfigurationPage {
    WebDriver driver;

    By configurationTab = By.xpath("//a[@href='/web/index.php/pim/configurePimModule']");
    By optionalFieldsTab = By.xpath("//a[@href='/web/index.php/pim/configurePim']");
    By customFieldsTab = By.xpath("//a[@href='/web/index.php/pim/listCustomFields']");
    By dataImportTab = By.xpath("//a[@href='/web/index.php/pim/pimCsvImport']");
    
    // Optional Fields
    By enableOptionalFields = By.id("configPim_chkShowSSN");
    By saveOptionalFieldsButton = By.id("btnSave");

    // Custom Fields
    By addCustomFieldButton = By.id("buttonAdd");
    By customFieldName = By.id("customField_name");
    By customFieldType = By.id("customField_type");
    By saveCustomFieldButton = By.id("btnSave");

    // Data Import
    By uploadFileInput = By.id("pimCsvImport_csvFile");
    By uploadButton = By.id("btnSave");

    public ConfigurationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToConfiguration() {
        driver.findElement(configurationTab).click();
    }

    public void navigateToOptionalFields() {
        driver.findElement(optionalFieldsTab).click();
    }

    public void navigateToCustomFields() {
        driver.findElement(customFieldsTab).click();
    }

    public void navigateToDataImport() {
        driver.findElement(dataImportTab).click();
    }

    public void enableOptionalFields() {
        driver.findElement(enableOptionalFields).click();
        driver.findElement(saveOptionalFieldsButton).click();
    }

    public void addCustomField(String name, String type) {
        driver.findElement(addCustomFieldButton).click();
        driver.findElement(customFieldName).sendKeys(name);
        driver.findElement(customFieldType).sendKeys(type);
        driver.findElement(saveCustomFieldButton).click();
    }

    public void uploadCsvFile(String filePath) {
        driver.findElement(uploadFileInput).sendKeys(filePath);
        driver.findElement(uploadButton).click();
    }
}

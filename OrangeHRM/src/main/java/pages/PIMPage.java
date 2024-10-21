package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PIMPage {
    private WebDriver driver;

    // Locator
    private By pimTab = By.xpath("//span[text()='PIM']");

    public PIMPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToPIM() {
        driver.findElement(pimTab).click();
    }
}

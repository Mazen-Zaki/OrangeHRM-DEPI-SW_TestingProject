package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Set;

public class LoginPage {
    WebDriver driver;

    // Define locators for elements on the login page
    By usernameField = By.name("username");
    By passwordField = By.name("password");
    By loginButton = By.xpath("//button[@type=\"submit\"]");
    By linkedInButton = By.xpath("//a[@href=\"https://www.linkedin.com/company/orangehrm/mycompany/\"]"); // Locator for
                                                                                                          // LinkedIn
                                                                                                          // button
    By facebookButton = By.xpath("//a[@href=\"https://www.facebook.com/OrangeHRM/\"]"); // Locator for LinkedIn button
    By twitterButton = By.xpath("//a[@href=\"https://twitter.com/orangehrm?lang=en\"]"); // Locator for LinkedIn button
    By youtubeButton = By.xpath("//a[@href=\"https://www.youtube.com/c/OrangeHRMInc\"]"); // Locator for LinkedIn button

    // Element

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to enter the username
    public void enterUsername(String username) {
        WebElement usernameElement = driver.findElement(usernameField);
        usernameElement.clear();
        usernameElement.sendKeys(username);
    }

    // Method to enter the password
    public void enterPassword(String password) {
        WebElement passwordElement = driver.findElement(passwordField);
        passwordElement.clear();
        passwordElement.sendKeys(password);
    }

    // Method to click the login button
    public void clickLoginButton() {
        WebElement loginButtonElement = driver.findElement(loginButton);
        loginButtonElement.click();
    }

    // Method to click on the LinkedIn button and handle new tab
    public void clickLinkedInButton() {
        String originalWindow = driver.getWindowHandle();
        WebElement linkedInButtonElement = driver.findElement(linkedInButton);
        linkedInButtonElement.click();

        // Switch to the new window or tab
        Set<String> allWindows = driver.getWindowHandles();
        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    public void clickFacebookButton() {
        String originalWindow = driver.getWindowHandle();
        WebElement facebookButtonElement = driver.findElement(facebookButton);
        facebookButtonElement.click();

        Set<String> allWindows = driver.getWindowHandles();
        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    public void clickTwitterButton() {
        String originalWindow = driver.getWindowHandle();
        WebElement twitterButtonElement = driver.findElement(twitterButton);
        twitterButtonElement.click();

        Set<String> allWindows = driver.getWindowHandles();
        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    // validate that youtube button working corrcetly
    public void clickYoutubeButton() {
        String originalWindow = driver.getWindowHandle();
        WebElement youtubeButtonElement = driver.findElement(youtubeButton);
        youtubeButtonElement.click();

        Set<String> allWindows = driver.getWindowHandles();
        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    // Method to get the URL of the current page
    public String getPageUrl() {
        return driver.getCurrentUrl();
    }

    // Method to get the LinkedIn page URL after redirection
    public String getLinkedInPageUrl() {
        return driver.getCurrentUrl();
    }

    public String getFacebookPageUrl() {
        return driver.getCurrentUrl();
    }

    public String getTwitterPageUrl() {
        return driver.getCurrentUrl();
    }

    public String getYoutubePageUrl() {
        return driver.getCurrentUrl();
    }

    // Method to switch back to the original window
    public void switchBackToOriginalWindow(String originalWindow) {
        driver.close(); // Close the current tab
        driver.switchTo().window(originalWindow); // Switch back to the original tab
    }
}

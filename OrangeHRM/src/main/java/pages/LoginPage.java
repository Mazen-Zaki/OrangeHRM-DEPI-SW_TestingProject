package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.Set;

public class LoginPage
{
    WebDriver driver;

    // Define locators for elements on the login page
    By usernameField = By.name("username");
    By passwordField = By.name("password");
    By loginButton = By.xpath("//button[@type=\"submit\"]");

    By linkedInButton = By.xpath("//a[@href=\"https://www.linkedin.com/company/orangehrm/mycompany/\"]");   // Locator for LinkedIn button                                                                                        // button
    By facebookButton = By.xpath("//a[@href=\"https://www.facebook.com/OrangeHRM/\"]");                     // Locator for facebook button
    By twitterButton = By.xpath("//a[@href=\"https://twitter.com/orangehrm?lang=en\"]");                    // Locator for twitter button
    By youtubeButton = By.xpath("//a[@href=\"https://www.youtube.com/c/OrangeHRMInc\"]");

    By forgotPasswordButton = By.xpath("//p[contains(@class, 'orangehrm-login-forgot-header')]");
    By resetPasswordButton = By.xpath("//button[@type=\"submit\"]");
    By cancelButton = By.xpath("//button[@class='oxd-button oxd-button--large oxd-button--ghost orangehrm-forgot-password-button orangehrm-forgot-password-button--cancel']");

    By resetPasswordSuccessfullyMessage = By.xpath("//h6[@class='oxd-text oxd-text--h6 orangehrm-forgot-password-title']");

    // Element

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to enter the username
    public void enterUsername(String username)
    {
        WebElement usernameElement = driver.findElement(usernameField);
        usernameElement.sendKeys(username);
    }

    // Method to enter the password
    public void enterPassword(String password)
    {
        WebElement passwordElement = driver.findElement(passwordField);
        passwordElement.sendKeys(password);
    }

    // Method to click the login button
    public void clickLoginButton()
    {
        WebElement loginButtonElement = driver.findElement(loginButton);
        loginButtonElement.click();
    }

    // Method to click on the LinkedIn button and handle new tab
    public void clickLinkedInButton()
    {
        String originalWindow = driver.getWindowHandle();
        WebElement linkedInButtonElement = driver.findElement(linkedInButton);
        linkedInButtonElement.click();


        Set<String> windowHandles = driver.getWindowHandles();
        windowHandles.remove(originalWindow);

        String newTabHandle = windowHandles.iterator().next();
        driver.switchTo().window(newTabHandle);
    }

    public void clickFacebookButton()
    {
        String originalWindow = driver.getWindowHandle();
        WebElement facebookButtonElement = driver.findElement(facebookButton);
        facebookButtonElement.click();

        Set<String> windowHandles = driver.getWindowHandles();
        windowHandles.remove(originalWindow);

        String newTabHandle = windowHandles.iterator().next();
        driver.switchTo().window(newTabHandle);
    }

    public void clickTwitterButton()
    {
        String originalWindow = driver.getWindowHandle();
        WebElement twitterButtonElement = driver.findElement(twitterButton);
        twitterButtonElement.click();

        Set<String> windowHandles = driver.getWindowHandles();
        windowHandles.remove(originalWindow);

        String newTabHandle = windowHandles.iterator().next();
        driver.switchTo().window(newTabHandle);
    }

    // validate that YouTube button working correctly
    public void clickYoutubeButton()
    {
        String originalWindow = driver.getWindowHandle();
        WebElement youtubeButtonElement = driver.findElement(youtubeButton);
        youtubeButtonElement.click();

        Set<String> windowHandles = driver.getWindowHandles();
        windowHandles.remove(originalWindow);

        String newTabHandle = windowHandles.iterator().next();
        driver.switchTo().window(newTabHandle);
    }

    public void clickForgotPasswordButton()
    {
        WebElement forgotPasswordButtonElement = driver.findElement(forgotPasswordButton);
        forgotPasswordButtonElement.click();
    }

    public void clickResetPasswordButton()
    {
        WebElement forgotPasswordButtonElement = driver.findElement(resetPasswordButton);
        forgotPasswordButtonElement.click();
    }

    public Boolean isResetPasswordSuccessfullyMessageShown()
    {
        try
        {
            WebElement successMessage = driver.findElement(resetPasswordSuccessfullyMessage);
            return true;
        }
        catch (NoSuchElementException e)
        {
            // If NoSuchElementException is caught, the test should pass
             return false;
        }
    }

    public void clickCancelButton()
    {
        WebElement forgotPasswordButtonElement = driver.findElement(cancelButton);
        forgotPasswordButtonElement.click();
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

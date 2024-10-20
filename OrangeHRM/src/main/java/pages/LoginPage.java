package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
    WebElement usernameElement;
    WebElement passwordElement;
    WebElement loginButtonElement;
    WebElement linkedInButtonElement;
    WebElement facebookButtonElement;
    WebElement twitterButtonElement;
    WebElement youtubeButtonElement;
    WebElement forgotPasswordButtonElement;
    WebElement forgotPasswordResetButtonElement;
    WebElement successMessage;
    WebElement forgotPasswordCancelButtonElement;

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to enter the username
    public void enterUsername(String username)
    {
        usernameElement = driver.findElement(usernameField);
        usernameElement.sendKeys(username);
    }

    // Method to enter the password
    public void enterPassword(String password)
    {
        passwordElement = driver.findElement(passwordField);
        passwordElement.sendKeys(password);
    }

    // Method to click the login button
    public void clickLoginButton()
    {
        loginButtonElement = driver.findElement(loginButton);
        loginButtonElement.click();
    }

    // Method to click on the LinkedIn button and handle new tab
    public void clickLinkedInButton()
    {
        linkedInButtonElement = driver.findElement(linkedInButton);
        linkedInButtonElement.click();
        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
    }

    public void clickFacebookButton()
    {
        facebookButtonElement = driver.findElement(facebookButton);
        facebookButtonElement.click();

        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
    }

    public void clickTwitterButton()
    {
        twitterButtonElement = driver.findElement(twitterButton);
        twitterButtonElement.click();

        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
    }

    // validate that YouTube button working correctly
    public void clickYoutubeButton()
    {
        youtubeButtonElement = driver.findElement(youtubeButton);
        youtubeButtonElement.click();

        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
    }

    public void clickForgotPasswordButton()
    {
        forgotPasswordButtonElement = driver.findElement(forgotPasswordButton);
        forgotPasswordButtonElement.click();
    }

    public void clickResetPasswordButton()
    {
        forgotPasswordResetButtonElement = driver.findElement(resetPasswordButton);
        forgotPasswordResetButtonElement.click();
    }

    public Boolean isResetPasswordSuccessfullyMessageShown()
    {
        try
        {
            successMessage = driver.findElement(resetPasswordSuccessfullyMessage);
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
        forgotPasswordCancelButtonElement = driver.findElement(cancelButton);
        forgotPasswordCancelButtonElement.click();
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
}

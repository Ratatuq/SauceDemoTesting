package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebDriver driver;

    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    //private By errorMessage = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        if (!driver.getTitle().contains("Swag Labs")) { // Умовно перевіряємо заголовок сторінки
            throw new IllegalStateException("This is not the Login Page. Current page: " + driver.getCurrentUrl());
        }
    }

    public ProductPage login(String username, String password) {
        WebElement usernameElement = driver.findElement(usernameField);
        WebElement passwordElement = driver.findElement(passwordField);
        WebElement loginButtonElement = driver.findElement(loginButton);

        usernameElement.sendKeys(username);
        passwordElement.sendKeys(password);
        loginButtonElement.click();

        return new ProductPage(driver);
    }

//    public boolean isErrorMessageDisplayed() {
//        try {
//            return driver.findElement(errorMessage).isDisplayed();
//        } catch (Exception e) {
//            return false;
//        }
//    }
}

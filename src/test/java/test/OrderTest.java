package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class OrderTest {

    WebDriver driver;

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com");
    }

    @Test(priority = 1)
    public void testLogin() {
        WebElement username = driver.findElement(By.id("user-name"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        username.sendKeys("standard_user");
        password.sendKeys("secret_sauce");
        loginButton.click();

        boolean isErrorDisplayed = isErrorMessageDisplayed();
        Assert.assertFalse(isErrorDisplayed, "Login failed. Incorrect username or password.");
    }

    @Test(priority = 2, dependsOnMethods = "testLogin")
    public void testAddTwoItemsToCart() {
        List<WebElement> addToCartButtons = driver.findElements(By.className("btn_inventory"));
        addToCartButtons.get(0).click();
        addToCartButtons.get(1).click();

        WebElement cartButton = driver.findElement(By.className("shopping_cart_link"));
        cartButton.click();
        List<WebElement> cartItems = driver.findElements(By.className("cart_item"));
        int cartItemCount = cartItems.size();

        Assert.assertEquals(cartItemCount, 2, "Expected 2 items in the cart.");

        WebElement checkoutButton = driver.findElement(By.className("checkout_button"));
        checkoutButton.click();

        WebElement firstNameInput = driver.findElement(By.id("first-name"));
        WebElement lastNameInput = driver.findElement(By.id("last-name"));
        WebElement postalCodeInput = driver.findElement(By.id("postal-code"));
        firstNameInput.sendKeys("John");
        lastNameInput.sendKeys("Doe");
        postalCodeInput.sendKeys("12345");

        WebElement continueButton = driver.findElement(By.className("btn_primary"));
        continueButton.click();

        List<WebElement> checkoutItems = driver.findElements(By.className("cart_item"));
        int checkoutItemCount = checkoutItems.size(); // Кількість товарів на сторінці Checkout Overview

        Assert.assertEquals(checkoutItemCount, cartItemCount, "Expected 2 items on the checkout overview page.");

        WebElement finishButton = driver.findElement(By.className("btn_action"));
        finishButton.click();

        WebElement confirmationMessage = driver.findElement(By.className("complete-header"));
        Assert.assertEquals(confirmationMessage.getText(), "Thank you for your order!", "Order confirmation message not displayed correctly.");

        WebElement backHomeButton = driver.findElement(By.className("btn_primary"));
        backHomeButton.click();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://www.saucedemo.com/inventory.html", "URL after clicking 'Back Home' is incorrect.");
    }

    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private boolean isErrorMessageDisplayed() {
        try {
            WebElement errorMessage = driver.findElement(By.cssSelector("[data-test='error']"));
            return errorMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}

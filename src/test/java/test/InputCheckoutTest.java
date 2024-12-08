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

public class InputCheckoutTest {

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
    public void testAddToCartAndProceedToCheckout() {
        List<WebElement> addToCartButtons = driver.findElements(By.className("btn_inventory"));
        addToCartButtons.get(0).click();

        WebElement cartButton = driver.findElement(By.className("shopping_cart_link"));
        cartButton.click();

        List<WebElement> cartItems = driver.findElements(By.className("cart_item"));
        Assert.assertEquals(cartItems.size(), 1, "Expected 1 item in the cart.");

        WebElement checkoutButton = driver.findElement(By.className("checkout_button"));
        checkoutButton.click();
    }

    @Test(priority = 3, dependsOnMethods = "testAddToCartAndProceedToCheckout")
    public void testCheckoutWithoutFillingFields() {
        WebElement continueButton = driver.findElement(By.className("btn_primary"));

        continueButton.click();

        WebElement errorMessage = driver.findElement(By.cssSelector("[data-test='error']"));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message not displayed.");
        Assert.assertEquals(errorMessage.getText(), "Error: First Name is required", "Incorrect error message displayed for First Name.");
    }

    @Test(priority = 4, dependsOnMethods = "testCheckoutWithoutFillingFields")
    public void testCheckoutWithFirstNameOnly() {
        WebElement firstNameInput = driver.findElement(By.id("first-name"));
        firstNameInput.sendKeys("John");

        WebElement continueButton = driver.findElement(By.className("btn_primary"));
        continueButton.click();

        WebElement errorMessage = driver.findElement(By.cssSelector("[data-test='error']"));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message not displayed for Last Name.");
        Assert.assertEquals(errorMessage.getText(), "Error: Last Name is required", "Incorrect error message displayed for Last Name.");
    }

    @Test(priority = 5, dependsOnMethods = "testCheckoutWithFirstNameOnly")
    public void testCheckoutWithFirstAndLastNameOnly() {
        WebElement firstNameInput = driver.findElement(By.id("first-name"));
        WebElement lastNameInput = driver.findElement(By.id("last-name"));
        lastNameInput.sendKeys("Doe");

        WebElement continueButton = driver.findElement(By.className("btn_primary"));
        continueButton.click();

        WebElement errorMessage = driver.findElement(By.cssSelector("[data-test='error']"));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message not displayed for Postal Code.");
        Assert.assertEquals(errorMessage.getText(), "Error: Postal Code is required", "Incorrect error message displayed for Postal Code.");
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

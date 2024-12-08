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

public class AddItemsToCartTest {
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
    public void testAddItemsToCart() {
        List<WebElement> addToCartButtons = driver.findElements(By.className("btn_inventory"));
        for (int i = 0; i < 3; i++) {
            addToCartButtons.get(i).click();
        }

        WebElement cartButton = driver.findElement(By.className("shopping_cart_link"));
        cartButton.click();

        List<WebElement> cartItems = driver.findElements(By.className("cart_item"));
        Assert.assertEquals(cartItems.size(), 3, "Expected 3 items in the cart.");
    }

    @Test(priority = 3, dependsOnMethods = "testAddItemsToCart")
    public void testRemoveAllItemsFromCart() {

        WebElement cartButton = driver.findElement(By.className("shopping_cart_link"));
        cartButton.click();

        List<WebElement> cartItemsBeforeRemove = driver.findElements(By.className("cart_item"));
        Assert.assertEquals(cartItemsBeforeRemove.size(), 3, "Expected 3 items in the cart before removal.");


        List<WebElement> removeButtons = driver.findElements(By.cssSelector(".cart_button"));
        for (WebElement removeButton : removeButtons) {
            removeButton.click();
        }

        List<WebElement> cartItemsAfterRemove = driver.findElements(By.className("cart_item"));
        Assert.assertEquals(cartItemsAfterRemove.size(), 0, "Expected 0 items in the cart after removal.");
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

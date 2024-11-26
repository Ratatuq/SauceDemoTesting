package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class SortingByPriceTest {

    WebDriver driver;

    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver\\chromedriver-win64\\chromedriver.exe");
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
    public void testSortProductsByPrice() {
        WebElement sortDropdown = driver.findElement(By.className("product_sort_container"));
        sortDropdown.click();
        WebElement lowToHighOption = driver.findElement(By.xpath("//option[@value='lohi']"));
        lowToHighOption.click();

        List<WebElement> productPrices = driver.findElements(By.className("inventory_item_price"));
        if (productPrices.size() > 1) {
            double firstPrice = extractPrice(productPrices.get(0).getText());
            double secondPrice = extractPrice(productPrices.get(1).getText());
            System.out.println("First price: " + firstPrice);
            System.out.println("Second price: " + secondPrice);
            Assert.assertTrue(firstPrice <= secondPrice, "Test Failed: The first product price is greater than the second product price.");
            System.out.println("Test Passed: The first product price is less than or equal to the second product price.");
        } else {
            Assert.fail("Test Failed: Not enough products to compare.");
        }
    }

    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private double extractPrice(String priceText) {
        return Double.parseDouble(priceText.replace("$", ""));
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
package test;

import io.github.bonigarcia.wdm.WebDriverManager;
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
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com");
        driver.manage().window().maximize();
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
    public void testSortProductsByPriceLowToHigh() {
        WebElement sortDropdown = driver.findElement(By.className("product_sort_container"));
        sortDropdown.click();
        WebElement lowToHighOption = driver.findElement(By.xpath("//option[@value='lohi']"));
        lowToHighOption.click();

        List<WebElement> productPrices = driver.findElements(By.className("inventory_item_price"));
        Assert.assertTrue(productPrices.size() >= 2, "Not enough products to compare.");

        for (int i = 0; i < productPrices.size() - 1; i++) {
            double currentPrice = extractPrice(productPrices.get(i).getText());
            double nextPrice = extractPrice(productPrices.get(i + 1).getText());
            Assert.assertTrue(currentPrice <= nextPrice, "Products are not sorted by price low to high.");
        }
    }

    @Test(priority = 3, dependsOnMethods = "testLogin")
    public void testSortProductsByPriceHighToLow() {
        WebElement sortDropdown = driver.findElement(By.className("product_sort_container"));
        sortDropdown.click();
        WebElement highToLowOption = driver.findElement(By.xpath("//option[@value='hilo']"));
        highToLowOption.click();

        List<WebElement> productPrices = driver.findElements(By.className("inventory_item_price"));
        Assert.assertTrue(productPrices.size() >= 2, "Not enough products to compare.");

        for (int i = 0; i < productPrices.size() - 1; i++) {
            double currentPrice = extractPrice(productPrices.get(i).getText());
            double nextPrice = extractPrice(productPrices.get(i + 1).getText());
            Assert.assertTrue(currentPrice >= nextPrice, "Products are not sorted by price high to low.");
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

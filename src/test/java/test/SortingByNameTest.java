package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class SortingByNameTest {
    private WebDriver driver;

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
    public void testSortProductsAlphabetically() {
        sortProducts("az");

        List<WebElement> productNames = driver.findElements(By.className("inventory_item_name"));

        boolean isSortedAlphabetically = true;
        for (int i = 0; i < productNames.size() - 1; i++) {
            String currentProductName = productNames.get(i).getText();
            String nextProductName = productNames.get(i + 1).getText();

            if (currentProductName.compareTo(nextProductName) > 0) {
                isSortedAlphabetically = false;
                break;
            }
        }

        Assert.assertTrue(isSortedAlphabetically, "Test Failed: Products are not sorted alphabetically (A to Z).");
        System.out.println("Test Passed: Products are sorted alphabetically (A to Z).");
    }

    @Test(priority = 3, dependsOnMethods = "testLogin")
    public void testSortProductsReverseAlphabetically() {
        sortProducts("za");

        List<WebElement> productNames = driver.findElements(By.className("inventory_item_name"));

        boolean isSortedReverseAlphabetically = true;
        for (int i = 0; i < productNames.size() - 1; i++) {
            String currentProductName = productNames.get(i).getText();
            String nextProductName = productNames.get(i + 1).getText();

            if (currentProductName.compareTo(nextProductName) < 0) {
                isSortedReverseAlphabetically = false;
                break;
            }
        }

        Assert.assertTrue(isSortedReverseAlphabetically, "Test Failed: Products are not sorted reverse alphabetically (Z to A).");
        System.out.println("Test Passed: Products are sorted reverse alphabetically (Z to A).");
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

    private void sortProducts(String sortOrder) {
        WebElement sortDropdown = driver.findElement(By.className("product_sort_container"));
        sortDropdown.click();
        WebElement sortOption = driver.findElement(By.xpath("//option[@value='" + sortOrder + "']"));
        sortOption.click();
    }
}

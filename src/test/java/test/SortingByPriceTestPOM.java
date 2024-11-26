package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.ProductPage;

public class SortingByPriceTestPOM {

    private WebDriver driver;
    private LoginPage loginPage;
    private ProductPage productPage;

    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com");

        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
    }

    @Test(priority = 1)
    public void testLogin() {
        loginPage = new LoginPage(driver);
        ProductPage productPage = loginPage.login("standard_user", "secret_sauce");

        boolean areProductsNotDisplayed = productPage.getProductNames().isEmpty();
        Assert.assertFalse(areProductsNotDisplayed, "Test Failed: No products displayed on the Product Page.");

        if (!areProductsNotDisplayed) {
            System.out.println("Test Passed: Products are displayed on the Product Page.");
        }
    }

    @Test(priority = 2, dependsOnMethods = "testLogin")
    public void testSortProductsByPrice() {
        productPage.sortProductsByPrice();

        Assert.assertTrue(productPage.arePricesSorted(), "Test Failed: Products are not sorted by price.");
        System.out.println("Test Passed: Products are sorted by price.");
    }

    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

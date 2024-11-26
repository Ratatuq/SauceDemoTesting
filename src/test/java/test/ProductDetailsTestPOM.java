package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;
import pages.ProductDetailsPage;

public class ProductDetailsTestPOM {
    private WebDriver driver;
    private LoginPage loginPage;
    private ProductPage productPage;
    private ProductDetailsPage productDetailsPage;

    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com");

        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
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
    public void testProductDetails() {
        productPage.clickFirstProduct();

        String productName = productDetailsPage.getProductName();
        String productPrice = productDetailsPage.getProductPrice();
        String productDescription = productDetailsPage.getProductDescription();

        System.out.println("Product Name: " + productName);
        System.out.println("Product Price: " + productPrice);
        System.out.println("Product Description: " + productDescription);

        Assert.assertTrue(productDetailsPage.isProductNameDisplayed(), "Product Name is not displayed.");
        Assert.assertTrue(productDetailsPage.isProductPriceDisplayed(), "Product Price is not displayed.");
        Assert.assertTrue(productDetailsPage.isProductDescriptionDisplayed(), "Product Description is not displayed.");
    }

    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

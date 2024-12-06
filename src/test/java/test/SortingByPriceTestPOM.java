package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.HomePage;

public class SortingByPriceTestPOM {

    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com");

        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
    }

    @Test(priority = 1)
    public void testLogin() {
        loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.login("standard_user", "secret_sauce");

        boolean areProductsNotDisplayed = homePage.getProductNames().isEmpty();
        Assert.assertFalse(areProductsNotDisplayed, "Test Failed: No products displayed on the Product Page.");

        if (!areProductsNotDisplayed) {
            System.out.println("Test Passed: Products are displayed on the Product Page.");
        }
    }

    @Test(priority = 2, dependsOnMethods = "testLogin")
    public void testSortProductsByPrice() {
        homePage.sortProductsByPrice();

        Assert.assertTrue(homePage.arePricesSorted(), "Test Failed: Products are not sorted by price.");
        System.out.println("Test Passed: Products are sorted by price.");
    }

    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

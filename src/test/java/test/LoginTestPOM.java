package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;

public class LoginTestPOM {
    WebDriver driver;
    LoginPage loginPage;

    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com");

    }

    @Test
    public void testLogin() {
        loginPage = new LoginPage(driver);
        ProductPage productPage = loginPage.login("standard_user", "secret_sauce");

        boolean areProductsNotDisplayed = productPage.getProductNames().isEmpty();
        Assert.assertFalse(areProductsNotDisplayed, "Test Failed: No products displayed on the Product Page.");

        if (!areProductsNotDisplayed) {
            System.out.println("Test Passed: Products are displayed on the Product Page.");
        }
    }


    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

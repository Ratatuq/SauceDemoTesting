package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.HomePage;
import pages.CartPage;
import pages.CheckoutInputPage;
import pages.CheckoutPage;
import pages.CheckoutConfirmPage;

public class OrderTestPOM {
    WebDriver driver;

    LoginPage loginPage;
    HomePage homePage;
    CartPage cartPage;
    CheckoutInputPage checkoutInputPage;
    CheckoutPage checkoutPage;
    CheckoutConfirmPage checkoutConfirmPage;

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com");

        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        cartPage = new CartPage(driver);
        checkoutInputPage = new CheckoutInputPage(driver);
        checkoutPage = new CheckoutPage(driver);
        checkoutConfirmPage = new CheckoutConfirmPage(driver);
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
    public void testOrder() {
        homePage.addFirstThreeProductsToCart();
        homePage.goToCart();

        Assert.assertEquals(cartPage.getCartItemCount(), 3, "Expected 3 items in the cart.");
        cartPage.clickCheckoutButton();

        checkoutInputPage.fillCheckoutForm("John", "Doe", "12345");
        checkoutInputPage.clickContinueButton();

        checkoutPage.clickFinishButton();

        String confirmationMessage = checkoutConfirmPage.getConfirmationMessage();
        Assert.assertEquals(confirmationMessage, "Thank you for your order!", "Order confirmation message not displayed correctly.");
    }

    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

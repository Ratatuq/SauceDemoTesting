package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;
import pages.CartPage;
import pages.CheckoutPage;
import pages.CheckoutOverviewPage;
import pages.OrderConfirmationPage;

public class OrderTestPOM {
    WebDriver driver;

    LoginPage loginPage;
    ProductPage productPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    CheckoutOverviewPage checkoutOverviewPage;
    OrderConfirmationPage orderConfirmationPage;

    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com");

        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        checkoutOverviewPage = new CheckoutOverviewPage(driver);
        orderConfirmationPage = new OrderConfirmationPage(driver);
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
    public void testOrder() {
        productPage.addFirstThreeProductsToCart();
        productPage.goToCart();

        Assert.assertEquals(cartPage.getCartItemCount(), 3, "Expected 3 items in the cart.");
        cartPage.clickCheckoutButton();

        checkoutPage.fillCheckoutForm("John", "Doe", "12345");
        checkoutPage.clickContinueButton();

        checkoutOverviewPage.clickFinishButton();

        String confirmationMessage = orderConfirmationPage.getConfirmationMessage();
        Assert.assertEquals(confirmationMessage, "Thank you for your order!", "Order confirmation message not displayed correctly.");
    }

    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

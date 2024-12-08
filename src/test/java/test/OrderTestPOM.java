package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class OrderTestPOM extends TestRunnerFirst {

    @Test
    public void orderTest() {
        LoginPage loginPage = loadApplication();
        HomePage homePage = loginPage.login("standard_user", "secret_sauce");
        Assert.assertNotNull(homePage, "Test failed: Wrong username or password.");

        homePage.addItemsToCart(2);

        CartPage cartPage = homePage.clickCartButton();
        int cartItemCount = cartPage.getCartItems().size();
        Assert.assertEquals(cartItemCount, 2, "Expected 2 items in the cart.");

        CheckoutInputPage checkoutInputPage = cartPage.clickCheckoutButton();
        checkoutInputPage.fillCheckoutForm("John", "Doe", "12345");
        CheckoutPage checkoutPage = checkoutInputPage.clickContinueButton();

        int checkoutItemCount = checkoutPage.getCheckoutPageItems().size();
        Assert.assertEquals(checkoutItemCount, cartItemCount, "Expected 2 items on the checkout overview page.");
        checkoutPage.clickCanselButton();

        homePage.clickCartButton();
        cartPage.clickCheckoutButton();
        checkoutInputPage.fillCheckoutForm("John", "Doe", "12345");
        checkoutInputPage.clickContinueButton();

        CheckoutConfirmPage checkoutConfirmPage = checkoutPage.clickFinishButton();
        String confirmationMessage = checkoutConfirmPage.getConfirmationMessage();
        Assert.assertEquals(confirmationMessage, "Thank you for your order!", "Order confirmation message not displayed correctly.");
        checkoutConfirmPage.clickBackHomeButton();
    }
}

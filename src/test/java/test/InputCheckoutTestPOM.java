package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class InputCheckoutTestPOM extends TestRunnerFirst {

    @Test
    public void checkoutInputErrorsTest() {
        LoginPage loginPage = loadApplication();
        HomePage homePage = loginPage.login("standard_user", "secret_sauce");
        Assert.assertNotNull(homePage, "Test failed: Wrong username or password.");

        homePage.addItemsToCart(1);
        CartPage cartPage = homePage.clickCartButton();

        Assert.assertEquals(cartPage.getCartItems().size(), 1, "Expected 1 item in the cart.");

        CheckoutInputPage checkoutInputPage = cartPage.clickCheckoutButton();

        checkoutInputPage.clickContinueButton();
        String errorMessage = checkoutInputPage.getErrorMessage();
        Assert.assertTrue(errorMessage.contains("First Name is required"), "Error message not displayed for First Name.");

        checkoutInputPage.fillCheckoutForm("John", "", "");
        checkoutInputPage.clickContinueButton();
        errorMessage = checkoutInputPage.getErrorMessage();
        Assert.assertTrue(errorMessage.contains("Last Name is required"), "Error message not displayed for Last Name.");

        checkoutInputPage.fillCheckoutForm("", "Doe", "");
        checkoutInputPage.clickContinueButton();
        errorMessage = checkoutInputPage.getErrorMessage();
        Assert.assertTrue(errorMessage.contains("Postal Code is required"), "Error message not displayed for Postal Code.");
    }

}

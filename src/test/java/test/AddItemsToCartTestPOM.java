package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class AddItemsToCartTestPOM extends TestRunnerFirst {

    @Test
    public void AddAndRemoveItemsToCart() {
        LoginPage loginPage = loadApplication();
        HomePage homePage = loginPage.login("standard_user", "secret_sauce");
        Assert.assertNotNull(homePage, "Login failed. Incorrect username or password.");

        homePage.addItemsToCart(3);

        CartPage cartPage = homePage.clickCartButton();
        Assert.assertEquals(cartPage.getCartItems().size(), 3, "Expected 3 items in the cart.");
        cartPage.removeAllItems();
        Assert.assertEquals(cartPage.getCartItems().size(), 0, "Expected 0 items in the cart after removal.");
    }
}

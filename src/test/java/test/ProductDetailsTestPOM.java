package test;

import components.PictureProductComponent;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class ProductDetailsTestPOM extends TestRunnerFirst {

    @Test
    public void productDetailsTest() {
        LoginPage loginPage = loadApplication();
        HomePage homePage = loginPage.login("standard_user", "secret_sauce");
        Assert.assertNotNull(homePage, "Test failed: Wrong username or password.");

        ProductPage productPage = homePage.clickFirstProduct();
        PictureProductComponent productComponent = productPage.getProductComponent();

        String productName = productComponent.getProductName();
        String productPrice = productComponent.getProductPrice();
        String productDescription = productComponent.getProductDescription();

        System.out.println("Product Name: " + productName);
        System.out.println("Product Price: " + productPrice);
        System.out.println("Product Description: " + productDescription);
        Assert.assertTrue(productComponent.isProductDisplayed(), "Product details are not fully displayed.");
    }
}

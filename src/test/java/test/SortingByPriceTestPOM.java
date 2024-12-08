package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class SortingByPriceTestPOM extends TestRunnerFirst {

    @Test
    public void sortingByPriceTest() {
        LoginPage loginPage = loadApplication();
        HomePage homePage = loginPage.login("standard_user", "secret_sauce");
        Assert.assertNotNull(homePage, "Test failed: Wrong username or password.");

        boolean isComparisonPossible = homePage.areAtLeastTwoProductsForPriceComparison();
        Assert.assertTrue(isComparisonPossible, "There are not enough products for price comparison.");

        homePage.sortProductsByHighToLowPrice();
        Assert.assertTrue(homePage.arePricesSortedHighToLowPrice(), "Test Failed: Products are not sorted by high to low price.");
        System.out.println("Test Passed: Products are sorted by high to low price.");

        homePage.sortProductsByLowToHighPrice();
        Assert.assertTrue(homePage.arePricesSortedLowToHighPrice(), "Test Failed: Products are not sorted by low to high price.");
        System.out.println("Test Passed: Products are sorted by low to high price.");
    }

}

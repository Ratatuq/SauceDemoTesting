package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class SortingByNameTestPOM extends TestRunnerFirst {

    @Test
    public void testLogin() {
        LoginPage loginPage = loadApplication();
        HomePage homePage = loginPage.login("standard_user", "secret_sauce");
        Assert.assertNotNull(homePage, "Test failed: Wrong username or password.");
        homePage.sortProductsAlphabeticallyAZ();
        boolean isSortedAlphabetically = homePage.isSortedAlphabeticallyAZ();
        Assert.assertTrue(isSortedAlphabetically, "Test Failed: Products are not sorted alphabetically.");
        System.out.println("Test Passed: Products are sorted alphabetically from A to Z.");
        homePage.sortProductsAlphabeticallyZA();
        isSortedAlphabetically = homePage.isSortedAlphabeticallyZA();
        Assert.assertTrue(isSortedAlphabetically, "Test Failed: Products are not sorted alphabetically.");
        System.out.println("Test Passed: Products are sorted alphabetically from Z to A.");
    }

}

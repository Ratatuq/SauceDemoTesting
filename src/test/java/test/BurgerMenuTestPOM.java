package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class BurgerMenuTestPOM extends TestRunnerFirst {

    @Test
    public void logoutButtonTest() {
        LoginPage loginPage = loadApplication();
        HomePage homePage = loginPage.login("standard_user", "secret_sauce");
        Assert.assertNotNull(homePage, "Test failed: Wrong username or password.");
        CartPage cartPage = homePage.clickCartButton();
        HamburgerMenuPage hamburgerMenuPage = cartPage.clickHamburgerButton();
        hamburgerMenuPage.clickLogOutButton();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://www.saucedemo.com/", "User did not log out correctly.");
    }
}

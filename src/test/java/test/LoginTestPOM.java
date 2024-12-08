package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class LoginTestPOM extends TestRunnerFirst {

    @Test
    public void loginTest() {
        LoginPage loginPage = loadApplication();
        HomePage homePage = loginPage.login("standard_user", "secret_sauce");
        Assert.assertNotNull(homePage, "Test failed: Wrong username or password.");
    }
}

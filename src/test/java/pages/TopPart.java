package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class TopPart {
    protected WebDriver driver;

    private By hamburgerButton = By.id("react-burger-menu-btn");
    private By siteTitle = By.className("app_logo");
    private By cartButton = By.className("shopping_cart_link");

    public TopPart(WebDriver driver) {
        this.driver = driver;
    }

    public void clickCartButton() {
        driver.findElement(cartButton).click();
    }

    public void clickHamburgerButton() {
        driver.findElement(hamburgerButton).click();
    }

    public String getSiteTitle() {
        return driver.findElement(siteTitle).getText();
    }


    public boolean isCartButtonDisplayed() {
        return driver.findElement(cartButton).isDisplayed();
    }
}

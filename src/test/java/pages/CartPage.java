package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private WebDriver driver;

    private By cartItemLocator = By.className("cart_item");
    private By checkoutButton = By.className("checkout_button");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public int getCartItemCount() {
        return driver.findElements(cartItemLocator).size();
    }

    public void clickCheckoutButton() {
        driver.findElement(checkoutButton).click();
    }
}

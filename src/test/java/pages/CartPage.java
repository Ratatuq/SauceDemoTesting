package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends TopPart {

    @FindBy(className = "cart_item")
    private WebElement cartItemLocator;

    @FindBy(className = "checkout_button")
    private WebElement checkoutButton;

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public int getCartItemCount() {
        return driver.findElements(By.className("cart_item")).size();
    }

    public void clickCheckoutButton() {
        checkoutButton.click();
    }
}

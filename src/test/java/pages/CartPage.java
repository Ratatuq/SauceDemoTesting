package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends TopPart {

    // Локатор для елементів у кошику (тепер використовуємо FindBy для кнопок)
    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    @FindBy(className = "checkout_button")
    private WebElement checkoutButton;

    @FindBy(css = ".cart_button")
    private List<WebElement> removeButtons;

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getCartItems() {
        return cartItems;
    }

    public void removeAllItems() {
        for (WebElement removeButton : removeButtons) {
            removeButton.click();
        }
    }

    public CheckoutInputPage clickCheckoutButton() {
        checkoutButton.click();
        return new CheckoutInputPage(driver);
    }
}

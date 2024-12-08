package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckoutPage extends TopPart {

    @FindBy(className = "btn_action")
    private WebElement finishButton;

    @FindBy(className = "cart_item")
    private List<WebElement> checkoutItems;

    @FindBy(className = "back")
    private WebElement canselButton;


    public CheckoutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public CheckoutConfirmPage clickFinishButton() {
        finishButton.click();
        return new CheckoutConfirmPage(driver);
    }

    public List<WebElement> getCheckoutPageItems() {
        return checkoutItems;
    }

    public void clickCanselButton() {
        canselButton.click();
    }
}

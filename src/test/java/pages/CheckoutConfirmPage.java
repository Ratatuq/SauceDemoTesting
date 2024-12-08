package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutConfirmPage extends TopPart {

    @FindBy(className = "complete-header")
    private WebElement confirmationMessage;

    @FindBy(id = "back-to-products")
    private WebElement backHomeButton;

    public CheckoutConfirmPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getConfirmationMessage() {
        return confirmationMessage.getText();
    }

    public void clickBackHomeButton() {
        backHomeButton.click();
    }
}

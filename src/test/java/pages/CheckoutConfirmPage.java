package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutConfirmPage extends TopPart {
    private By confirmationMessage = By.className("complete-header");

    public CheckoutConfirmPage(WebDriver driver) {
        super(driver);
    }

    public String getConfirmationMessage() {
        return driver.findElement(confirmationMessage).getText();
    }
}

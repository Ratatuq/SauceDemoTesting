package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends TopPart {
    private By finishButton = By.className("btn_action");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void clickFinishButton() {
        driver.findElement(finishButton).click();
    }
}

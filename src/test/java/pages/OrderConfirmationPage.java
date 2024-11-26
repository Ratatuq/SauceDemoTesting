package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderConfirmationPage {
    private WebDriver driver;

    private By confirmationMessage = By.className("complete-header");

    public OrderConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getConfirmationMessage() {
        WebElement message = driver.findElement(confirmationMessage);
        return message.getText();
    }
}

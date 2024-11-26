package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutOverviewPage {
    private WebDriver driver;

    private By finishButton = By.className("btn_action");

    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickFinishButton() {
        WebElement finishBtn = driver.findElement(finishButton);
        finishBtn.click();
    }
}

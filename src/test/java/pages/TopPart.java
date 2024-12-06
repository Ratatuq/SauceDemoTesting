package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class TopPart {
    protected WebDriver driver;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement hamburgerButton;

    @FindBy(className = "app_logo")
    private WebElement siteTitle;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartButton;

    public TopPart(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickCartButton() {
        cartButton.click();
    }

    public void clickHamburgerButton() {
        hamburgerButton.click();
    }

    public String getSiteTitle() {
        return siteTitle.getText();
    }

    public boolean isCartButtonDisplayed() {
        return cartButton.isDisplayed();
    }
}

package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HamburgerMenuPage {
    private WebDriver driver;

    @FindBy(id = "inventory_sidebar_link")
    private WebElement allItemsButton;

    @FindBy(id = "about_sidebar_link")
    private WebElement aboutButton;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutButton;

    @FindBy(id = "reset_sidebar_link")
    private WebElement resetAppStateButton;

    @FindBy(className = "bm-menu")
    private WebElement menuPanel;

    @FindBy(className = "bm-menu-wrap")
    private WebElement menuWrapper;

    public HamburgerMenuPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickAllItemsButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement allItems = wait.until(ExpectedConditions.elementToBeClickable(allItemsButton));
        allItems.click();
    }

    public void clickAboutButton() {
        aboutButton.click();
    }

    public void clickLogOutButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement burgerMenu = wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        burgerMenu.click();
    }

    public void clickResetAppStateButton() {
        resetAppStateButton.click();
    }
}

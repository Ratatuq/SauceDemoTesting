package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HamburgerMenuPage {
    private WebDriver driver;

    private By allItemsButton = By.id("inventory_sidebar_link");
    private By aboutButton = By.id("about_sidebar_link");
    private By logoutButton = By.id("logout_sidebar_link");
    private By resetAppStateButton = By.id("reset_sidebar_link");

    private By menuPanel = By.className("bm-menu");

    private By menuWrapper = By.className("bm-menu-wrap");

    public HamburgerMenuPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isMenuOpen() {
        try {
            WebElement menuWrapperElement = driver.findElement(menuWrapper);
            String ariaHiddenValue = menuWrapperElement.getAttribute("aria-hidden");

            return ariaHiddenValue != null && ariaHiddenValue.equals("false");
        } catch (Exception e) {
            return false;
        }
    }

    public void clickAllItemsButton() {
        driver.findElement(allItemsButton).click();
    }

    public void clickAboutButton() {
        driver.findElement(aboutButton).click();
    }

    public void clickLogoutButton() {
        driver.findElement(logoutButton).click();
    }

    public void clickResetAppStateButton() {
        driver.findElement(resetAppStateButton).click();
    }
}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailsPage {
    private WebDriver driver;

    private By productName = By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[1]");
    private By productPrice = By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[3]");
    private By productDescription = By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[2]");

    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getProductName() {
        return driver.findElement(productName).getText();
    }

    public String getProductPrice() {
        return driver.findElement(productPrice).getText();
    }

    public String getProductDescription() {
        return driver.findElement(productDescription).getText();
    }

    public boolean isProductNameDisplayed() {
        return driver.findElement(productName).isDisplayed();
    }

    public boolean isProductPriceDisplayed() {
        return driver.findElement(productPrice).isDisplayed();
    }

    public boolean isProductDescriptionDisplayed() {
        return driver.findElement(productDescription).isDisplayed();
    }
}

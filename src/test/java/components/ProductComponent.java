package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class ProductComponent {
    protected WebDriver driver;

    protected By productNameLocator;
    protected By productDescriptionLocator;
    protected By productPriceLocator;
    protected By actionButtonLocator;

    public ProductComponent(WebDriver driver,
                            By productNameLocator,
                            By productDescriptionLocator,
                            By productPriceLocator,
                            By actionButtonLocator) {
        this.driver = driver;
        this.productNameLocator = productNameLocator;
        this.productDescriptionLocator = productDescriptionLocator;
        this.productPriceLocator = productPriceLocator;
        this.actionButtonLocator = actionButtonLocator;
    }

    public String getProductName() {
        return driver.findElement(productNameLocator).getText();
    }

    public String getProductDescription() {
        return driver.findElement(productDescriptionLocator).getText();
    }

    public String getProductPrice() {
        return driver.findElement(productPriceLocator).getText();
    }

    public void clickActionButton() {
        driver.findElement(actionButtonLocator).click();
    }

    public boolean isProductDisplayed() {
        return driver.findElement(productNameLocator).isDisplayed() &&
                driver.findElement(productDescriptionLocator).isDisplayed() &&
                driver.findElement(productPriceLocator).isDisplayed();
    }
}

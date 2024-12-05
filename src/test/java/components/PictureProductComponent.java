package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PictureProductComponent extends ProductComponent {
    private By productImageLocator;

    public PictureProductComponent(WebDriver driver,
                                   By productNameLocator,
                                   By productDescriptionLocator,
                                   By productPriceLocator,
                                   By actionButtonLocator,
                                   By productImageLocator) {
        super(driver, productNameLocator, productDescriptionLocator, productPriceLocator, actionButtonLocator);
        this.productImageLocator = productImageLocator;
    }

    public boolean isProductImageDisplayed() {
        return driver.findElement(productImageLocator).isDisplayed();
    }

    public String getProductImageSrc() {
        return driver.findElement(productImageLocator).getAttribute("src");
    }
}

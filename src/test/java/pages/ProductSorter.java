package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductSorter {
    private WebDriver driver;

    @FindBy(className = "product_sort_container")
    private WebElement sortDropdown;

    @FindBy(xpath = "//option[@value='az']")
    private WebElement alphabeticallyOption;

    @FindBy(xpath = "//option[@value='lohi']")
    private WebElement priceLowToHighOption;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> productNames;

    @FindBy(className = "inventory_item_price")
    private List<WebElement> productPrices;

    public ProductSorter(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void sortAlphabetically() {
        sortDropdown.click();
        alphabeticallyOption.click();
    }

    public void sortByPriceLowToHigh() {
        sortDropdown.click();
        priceLowToHighOption.click();
    }

    public boolean isSortedAlphabetically() {
        for (int i = 0; i < productNames.size() - 1; i++) {
            String currentProductName = productNames.get(i).getText();
            String nextProductName = productNames.get(i + 1).getText();

            if (currentProductName.compareTo(nextProductName) > 0) {
                return false;
            }
        }
        return true;
    }

    public boolean arePricesSortedLowToHigh() {
        if (productPrices.size() > 1) {
            double firstPrice = extractPrice(productPrices.get(0).getText());
            double secondPrice = extractPrice(productPrices.get(1).getText());
            return firstPrice <= secondPrice;
        }
        return false;
    }

    private double extractPrice(String priceText) {
        return Double.parseDouble(priceText.replace("$", ""));
    }
}

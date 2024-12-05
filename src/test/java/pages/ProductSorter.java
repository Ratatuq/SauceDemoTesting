package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductSorter {
    private WebDriver driver;

    private By sortDropdown = By.className("product_sort_container");
    private By alphabeticallyOption = By.xpath("//option[@value='az']");
    private By priceLowToHighOption = By.xpath("//option[@value='lohi']");
    private By productNameLocator = By.className("inventory_item_name");
    private By productPriceLocator = By.className("inventory_item_price");

    public ProductSorter(WebDriver driver) {
        this.driver = driver;
    }

    public void sortAlphabetically() {
        WebElement dropdown = driver.findElement(sortDropdown);
        dropdown.click();
        WebElement alphabeticallyOptionElement = driver.findElement(alphabeticallyOption);
        alphabeticallyOptionElement.click();
    }

    public void sortByPriceLowToHigh() {
        WebElement dropdown = driver.findElement(sortDropdown);
        dropdown.click();
        WebElement priceLowToHighOptionElement = driver.findElement(priceLowToHighOption);
        priceLowToHighOptionElement.click();
    }

    public boolean isSortedAlphabetically() {
        List<WebElement> productNames = driver.findElements(productNameLocator);
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
        List<WebElement> productPrices = driver.findElements(productPriceLocator);
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

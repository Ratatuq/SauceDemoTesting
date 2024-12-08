package components;

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
    private WebElement alphabeticallyOptionAZ;

    @FindBy(xpath = "//option[@value='za']")
    private WebElement alphabeticallyOptionZA;

    @FindBy(xpath = "//option[@value='lohi']")
    private WebElement priceLowToHighOption;

    @FindBy(xpath = "//option[@value='hilo']")
    private WebElement priceHighToLowOption;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> productNames;

    @FindBy(className = "inventory_item_price")
    private List<WebElement> productPrices;

    public ProductSorter(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void sortAlphabeticallyAZ() {
        sortDropdown.click();
        alphabeticallyOptionAZ.click();
    }

    public void sortAlphabeticallyZA() {
        sortDropdown.click();
        alphabeticallyOptionZA.click();
    }

    public void sortByPriceLowToHigh() {
        sortDropdown.click();
        priceLowToHighOption.click();
    }

    public void sortByPriceHighToLow() {
        sortDropdown.click();
        priceHighToLowOption.click();
    }

    public boolean areProductsSortedAlphabeticallyAZ() {
        for (int i = 0; i < productNames.size() - 1; i++) {
            String currentProductName = productNames.get(i).getText();
            String nextProductName = productNames.get(i + 1).getText();

            if (currentProductName.compareTo(nextProductName) > 0) {
                return false;
            }
        }
        return true;
    }

    public boolean areProductsSortedAlphabeticallyZA() {
        for (int i = 0; i < productNames.size() - 1; i++) {
            String currentProductName = productNames.get(i).getText();
            String nextProductName = productNames.get(i + 1).getText();

            if (currentProductName.compareTo(nextProductName) < 0) {
                return false;
            }
        }
        return true;
    }

    public boolean arePricesSortedLowToHigh() {
        return arePricesSorted(true);
    }

    public boolean arePricesSortedHighToLow() {
        return arePricesSorted(false);
    }

    private boolean arePricesSorted(boolean ascending) {
        for (int i = 0; i < productPrices.size() - 1; i++) {
            double currentPrice = extractPrice(productPrices.get(i).getText());
            double nextPrice = extractPrice(productPrices.get(i + 1).getText());

            if (ascending && currentPrice > nextPrice) {
                return false;
            } else if (!ascending && currentPrice < nextPrice) {
                return false;
            }
        }
        return true;
    }

    private double extractPrice(String priceText) {
        return Double.parseDouble(priceText.replace("$", ""));
    }
}

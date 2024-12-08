package pages;

import components.ProductSorter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends TopPart {

    @FindBy(className = "btn_inventory")
    private List<WebElement> addToCartButtons;

    @FindBy(xpath = "//*[@id=\"item_4_img_link\"]")
    private WebElement firstProduct;

    private ProductSorter productSorter;

    public HomePage(WebDriver driver) {
        super(driver);
        productSorter = new ProductSorter(driver);
        PageFactory.initElements(driver, this);
    }


    public void addItemsToCart(int numberOfItems) {
        for (int i = 0; i < numberOfItems; i++) {
            addToCartButtons.get(i).click();
        }
    }

    public ProductPage clickFirstProduct() {
        firstProduct.click();
        return new ProductPage(driver);
    }

    public void sortProductsAlphabeticallyAZ() {
        productSorter.sortAlphabeticallyAZ();
    }

    public void sortProductsAlphabeticallyZA() {
        productSorter.sortAlphabeticallyZA();
    }

    public List<WebElement> getProductNames() {
        return driver.findElements(By.className("inventory_item_name"));
    }

    public boolean isSortedAlphabeticallyAZ() {
        return productSorter.areProductsSortedAlphabeticallyAZ();
    }

    public boolean isSortedAlphabeticallyZA() {
        return productSorter.areProductsSortedAlphabeticallyZA();
    }

    public void sortProductsByLowToHighPrice() {
        productSorter.sortByPriceLowToHigh();
    }

    public void sortProductsByHighToLowPrice() {
        productSorter.sortByPriceHighToLow();
    }

    public boolean arePricesSortedLowToHighPrice() {
        return productSorter.arePricesSortedLowToHigh();
    }

    public boolean arePricesSortedHighToLowPrice() {
        return productSorter.arePricesSortedHighToLow();
    }

    public boolean areAtLeastTwoProductsForPriceComparison() {
        List<WebElement> products = getProductNames();
        return products.size() >= 2;
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}

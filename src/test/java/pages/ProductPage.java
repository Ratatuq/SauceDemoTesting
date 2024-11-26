package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductPage {
    private WebDriver driver;

    private By addToCartButtonLocator = By.className("btn_inventory");
    private By cartButton = By.className("shopping_cart_link");
    private By firstProduct = By.xpath("//*[@id=\"item_4_img_link\"]");
    private By sortDropdown = By.className("product_sort_container");
    private By alphabeticallyOption = By.xpath("//option[@value='az']");
    private By productNameLocator = By.className("inventory_item_name");
    private By productPriceLocator = By.className("inventory_item_price");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addFirstThreeProductsToCart() {
        List<WebElement> addToCartButtonsList = driver.findElements(addToCartButtonLocator);
        for (int i = 0; i < 3; i++) {
            addToCartButtonsList.get(i).click();
        }
    }

    public void goToCart() {
        driver.findElement(cartButton).click();
    }

    public void clickFirstProduct() {
        WebElement firstProductElement = driver.findElement(firstProduct);
        firstProductElement.click();
    }

    public void sortProductsAlphabetically() {
        driver.findElement(sortDropdown).click();
        driver.findElement(alphabeticallyOption).click();
    }

    public List<WebElement> getProductNames() {
        return driver.findElements(productNameLocator);
    }

    public boolean isSortedAlphabetically() {
        List<WebElement> productNames = getProductNames();
        for (int i = 0; i < productNames.size() - 1; i++) {
            String currentProductName = productNames.get(i).getText();
            String nextProductName = productNames.get(i + 1).getText();

            if (currentProductName.compareTo(nextProductName) > 0) {
                return false;
            }
        }
        return true;
    }

    public void sortProductsByPrice() {
        WebElement dropdown = driver.findElement(sortDropdown);
        dropdown.click();
        WebElement lowToHighOption = driver.findElement(By.xpath("//option[@value='lohi']"));
        lowToHighOption.click();
    }

    public boolean arePricesSorted() {
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

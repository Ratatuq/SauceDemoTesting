package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage extends TopPart {
    private By addToCartButtonLocator = By.className("btn_inventory");
    private By firstProduct = By.xpath("//*[@id=\"item_4_img_link\"]");
    private ProductSorter productSorter;

    public HomePage(WebDriver driver) {
        super(driver);
        productSorter = new ProductSorter(driver);
    }

    public void addFirstThreeProductsToCart() {
        List<WebElement> addToCartButtonsList = driver.findElements(addToCartButtonLocator);
        for (int i = 0; i < 3; i++) {
            addToCartButtonsList.get(i).click();
        }
    }

    public void goToCart() {
        clickCartButton();
    }

    public void clickFirstProduct() {
        WebElement firstProductElement = driver.findElement(firstProduct);
        firstProductElement.click();
    }

    public void sortProductsAlphabetically() {
        productSorter.sortAlphabetically();
    }

    public List<WebElement> getProductNames() {
        return driver.findElements(By.className("inventory_item_name"));
    }

    public boolean isSortedAlphabetically() {
        return productSorter.isSortedAlphabetically();
    }

    public void sortProductsByPrice() {
        productSorter.sortByPriceLowToHigh();
    }

    public boolean arePricesSorted() {
        return productSorter.arePricesSortedLowToHigh();
    }
}

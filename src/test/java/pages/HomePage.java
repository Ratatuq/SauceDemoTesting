package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends TopPart {

    @FindBy(className = "btn_inventory")
    private List<WebElement> addToCartButtonList;

    @FindBy(xpath = "//*[@id=\"item_4_img_link\"]")
    private WebElement firstProduct;

    private ProductSorter productSorter;

    public HomePage(WebDriver driver) {
        super(driver);
        productSorter = new ProductSorter(driver);
        PageFactory.initElements(driver, this);
    }

    public void addFirstThreeProductsToCart() {
        for (int i = 0; i < 3; i++) {
            addToCartButtonList.get(i).click();
        }
    }

    public void goToCart() {
        clickCartButton();
    }

    public void clickFirstProduct() {
        firstProduct.click();
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

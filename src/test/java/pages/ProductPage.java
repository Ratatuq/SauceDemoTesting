package pages;

import components.PictureProductComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends TopPart {
    private PictureProductComponent productComponent;

    public ProductPage(WebDriver driver) {
        super(driver);

        this.productComponent = new PictureProductComponent(
                driver,
                By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[1]"), // Name
                By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[2]"), // Desc
                By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[3]"), // Price
                By.xpath("//*[contains(@class, 'btn_inventory')]"), // ActionBtn
                By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div[1]/img") // Img
        );
    }

    public PictureProductComponent getProductComponent() {
        return productComponent;
    }
}

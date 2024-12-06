package pages;

import components.PictureProductComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends TopPart {
    private PictureProductComponent productComponent;

    @FindBy(xpath = "//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[1]") // Name
    private WebElement productName;

    @FindBy(xpath = "//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[2]") // Desc
    private WebElement productDesc;

    @FindBy(xpath = "//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[3]") // Price
    private WebElement productPrice;

    @FindBy(xpath = "//*[contains(@class, 'btn_inventory')]") // ActionBtn
    private WebElement actionButton;

    @FindBy(xpath = "//*[@id=\"inventory_item_container\"]/div/div/div[1]/img") // Img
    private WebElement productImage;

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);

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

package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class InventoryPage {
    @FindBy(className = "inventory_item") private List<WebElement> products;
    @FindBy(className = "product_sort_container") private WebElement sortDropdown;
    @FindBy(className = "inventory_item_name") private List<WebElement> productNames;
    @FindBy(className = "inventory_item_price") private List<WebElement> productPrices;
    @FindBy(className = "btn_inventory") private List<WebElement> addToCartButtons;
    @FindBy(className = "shopping_cart_badge") private WebElement cartBadge;
    @FindBy(className = "inventory_item_img") private List<WebElement> productImages;

    public InventoryPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public int getProductCount() {
        return products.size();
    }

    public void selectSortOption(String sortType) {
        Select dropdown = new Select(sortDropdown);
        dropdown.selectByValue(sortType);
    }

    public String getFirstProductName() {
        return productNames.get(0).getText();
    }

    public String getSelectedSortOption() {
        Select dropdown = new Select(sortDropdown);
        return dropdown.getFirstSelectedOption().getText();
    }

    public double getFirstProductPrice() {
        return Double.parseDouble(productPrices.get(0).getText().replace("$", ""));
    }

    public double getLastProductPrice() {
        return Double.parseDouble(productPrices.get(productPrices.size() - 1).getText().replace("$", ""));
    }

    // New methods for additional test cases
    public void addFirstProductToCart() {
        System.out.println("Adding product to cart...");
        addToCartButtons.get(0).click();
    }

    public void removeFirstProductFromCart() {
        System.out.println("Removing product from cart...");
        addToCartButtons.get(0).click(); // Button toggles to "REMOVE"
    }

    public int getCartItemCount() {
        try {
            return Integer.parseInt(cartBadge.getText());
        } catch (Exception e) {
            return 0; // If badge isn't present
        }
    }

    public void clickFirstProductImage() {
        System.out.println("Clicking product image...");
        productImages.get(0).click();
    }
}
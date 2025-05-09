package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class CartPage {
    @FindBy(className = "cart_item") private List<WebElement> cartItems;
    @FindBy(className = "inventory_item_name") private List<WebElement> itemNames;
    @FindBy(className = "cart_quantity") private List<WebElement> itemQuantities;
    @FindBy(xpath = "//a[text()='CHECKOUT']") private WebElement checkoutBtn;
    @FindBy(xpath = "//a[text()='Continue Shopping']") private WebElement continueShoppingBtn;
    // Update the removeButtons selector in CartPage.java
    @FindBy(xpath = "//button[contains(@class,'btn_secondary') and text()='REMOVE']")
    private List<WebElement> removeButtons;

    public CartPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public int getItemCount() {
        return cartItems.size();
    }

    public String getItemName(int index) {
        return itemNames.get(index).getText();
    }

    public String getItemQuantity(int index) {
        return itemQuantities.get(index).getText();
    }

    public void clickContinueShopping() {
        continueShoppingBtn.click();
    }

    public void clickCheckout() {
        checkoutBtn.click();
    }
    public void removeItem(int index) {
        removeButtons.get(index).click();
    }
}
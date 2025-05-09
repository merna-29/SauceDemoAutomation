package tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.InventoryPage;
import pages.LoginPage;
import java.time.Duration;

public class InventoryTest extends BaseTest {
    private InventoryPage inventoryPage;
    private WebDriverWait wait;

    @BeforeClass
    public void globalSetup() {
        LoginPage loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.get("https://www.saucedemo.com/v1/index.html");
        loginPage.login("standard_user", "secret_sauce");
    }


    @Test(priority = 1, description = "Verify there are exactly 6 products displayed")
    public void testProductCount() {
        Assert.assertEquals(inventoryPage.getProductCount(), 6,
                "Product count mismatch!");
    }

    @DataProvider(name = "sortingOptions")
    public Object[][] provideSortingData() {
        return new Object[][]{
                {"az", "Sauce Labs Backpack", "Name (A to Z)"},
                {"za", "Test.allTheThings() T-Shirt (Red)", "Name (Z to A)"},
                {"lohi", "Sauce Labs Onesie", "Price (low to high)"},
                {"hilo", "Sauce Labs Fleece Jacket", "Price (high to low)"}
        };
    }

    @Test(priority = 2,
            dataProvider = "sortingOptions",
            description = "Verify products are sorted correctly by {0}")
    public void testProductSorting(String sortType, String expectedFirstProduct, String expectedSortText)
            throws InterruptedException {
        inventoryPage.selectSortOption(sortType);
        Thread.sleep(1000);
        Assert.assertEquals(inventoryPage.getFirstProductName(), expectedFirstProduct,
                "Sorting failed for '" + sortType + "'");
        Assert.assertEquals(inventoryPage.getSelectedSortOption(), expectedSortText,
                "Dropdown didn't update to show " + expectedSortText + " selection");
    }
    @Test(priority = 3, description = "INV-005: Add product to cart")
    public void testAddProductToCart() throws InterruptedException {
        System.out.println("Starting add to cart test...");
        int initialCount = inventoryPage.getCartItemCount();

        System.out.println("Clicking 'Add to Cart'...");
        inventoryPage.addFirstProductToCart();
        Thread.sleep(1500); // Pause to see the button change

        int newCount = inventoryPage.getCartItemCount();
        System.out.println("Cart count changed from " + initialCount + " to " + newCount);
        Assert.assertEquals(newCount, initialCount + 1,
                "Cart counter didn't increment after adding product");

        Thread.sleep(2000); // Pause after assertion to observe
    }

    @Test(priority = 4, description = "INV-006: Remove product from cart",
            dependsOnMethods = "testAddProductToCart")
    public void testRemoveProductFromCart() throws InterruptedException {
        System.out.println("Starting remove from cart test...");
        int initialCount = inventoryPage.getCartItemCount();

        System.out.println("Clicking 'Remove'...");
        inventoryPage.removeFirstProductFromCart();
        Thread.sleep(1500); // Pause to see the button change

        int newCount = inventoryPage.getCartItemCount();
        System.out.println("Cart count changed from " + initialCount + " to " + newCount);
        Assert.assertEquals(newCount, initialCount - 1,
                "Cart counter didn't decrement after removing product");

        Thread.sleep(2000); // Pause after assertion to observe
    }

    @Test(priority = 5, description = "INV-007: Product image navigation")
    public void testProductImageNavigation() throws InterruptedException {
        System.out.println("Starting product image navigation test...");
        String currentUrl = driver.getCurrentUrl();

        System.out.println("Clicking product image...");
        inventoryPage.clickFirstProductImage();
        Thread.sleep(1000);

        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(currentUrl)));
        Assert.assertNotEquals(driver.getCurrentUrl(), currentUrl,
                "Didn't navigate to product details page");

        System.out.println("Navigated to: " + driver.getCurrentUrl());
        Thread.sleep(2000); // Pause to see the details page

        System.out.println("Returning to inventory page...");
        driver.navigate().back();
        Thread.sleep(1000);
    }
}
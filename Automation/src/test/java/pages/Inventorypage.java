package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Inventorypage {
    // Sidebar menu elements
    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    @FindBy(className = "bm-menu-wrap")
    private WebElement menuWrapper;

    @FindBy(className = "bm-cross-button")
    private WebElement closeMenuButton;


    private final WebDriver driver;
    private final WebDriverWait wait;

    public Inventorypage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Methods for sidebar menu
    public void openMenu() {
        menuButton.click();
        wait.until(ExpectedConditions.visibilityOf(menuWrapper));
    }

    public void closeMenu() {
        closeMenuButton.click();
        wait.until(ExpectedConditions.invisibilityOf(menuWrapper));
    }

    public boolean isMenuOpen() {
        return menuWrapper.getAttribute("aria-hidden").equals("false");
    }




}
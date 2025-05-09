package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SidebarMenu {
    @FindBy(id = "inventory_sidebar_link")
    private WebElement allItemsLink;

    @FindBy(id = "about_sidebar_link")
    private WebElement aboutLink;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutLink;

    @FindBy(id = "reset_sidebar_link")
    private WebElement resetAppStateLink;

    @FindBy(id = "react-burger-cross-btn")
    private WebElement closeMenuButton;

    @FindBy(className = "bm-menu-wrap")
    private WebElement menuWrapper;

    private WebDriver driver;

    public SidebarMenu(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }




    public void closeMenu() {
        closeMenuButton.click();
    }

    public boolean isMenuDisplayed() {
        return menuWrapper.getAttribute("aria-hidden").equals("false");
    }

    public boolean isAllItemsLinkDisplayed() {
        return allItemsLink.isDisplayed();
    }

    public boolean isAboutLinkDisplayed() {
        return aboutLink.isDisplayed();
    }

    public boolean isLogoutLinkDisplayed() {
        return logoutLink.isDisplayed();
    }

    public boolean isResetAppStateLinkDisplayed() {
        return resetAppStateLink.isDisplayed();
    }
}

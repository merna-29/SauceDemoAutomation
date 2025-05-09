package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;

public class SidebarMenuTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\AESHA\\Downloads\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }



    @Test(priority = 1)
    public void testLogin() {
        // Navigate to Swag Labs
        driver.get("https://www.saucedemo.com/v1/index.html");


        WebElement username = driver.findElement(By.id("user-name"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginBtn = driver.findElement(By.className("btn_action"));

        username.sendKeys("standard_user");
        password.sendKeys("secret_sauce");
        loginBtn.click();


        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("inventory_list")));
    }

    @Test(priority = 2)
    public void testSidebarMenuCanBeOpened() {

        WebElement menuBtn = driver.findElement(By.className("bm-burger-button"));
        menuBtn.click();


        WebElement menuWrap = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("bm-menu-wrap")));
        Assert.assertTrue(menuWrap.isDisplayed(), "Sidebar menu should be visible after clicking menu button");
    }

    @Test(priority = 3)
    public void testSidebarMenuContainsCorrectItems() {

        WebElement menuBtn = driver.findElement(By.className("bm-menu-wrap"));
        menuBtn.click();

        WebElement menuWrap =wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("bm-menu-wrap")));


        List<WebElement> menuItems = driver.findElements(By.className("bm-item"));
        String[] expectedItems = {"All Items", "About", "Logout", "Reset App State"};

        Assert.assertEquals(menuItems.size(), expectedItems.length,
                "Number of menu items doesn't match expected");

        for (int i = 0; i < expectedItems.length; i++) {
            Assert.assertEquals(menuItems.get(i).getText(), expectedItems[i],
                    "Menu item text doesn't match expected");
        }

    }



    @Test(priority = 4)
    public void testSidebarMenuCanBeClosed() {

        WebElement menuBtn = By.className("bm-menu-wrap").findElement(driver);
        menuBtn.click();

        WebElement menuWrap = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("bm-menu-wrap")));

        WebElement closeBtn = driver.findElement(By.className("bm-cross-button"));
        closeBtn.click();

        wait.until(ExpectedConditions.invisibilityOf(menuWrap));

        Assert.assertFalse(menuWrap.isDisplayed(), "يجب أن تختفي القائمة الجانبية بعد النقر على زر الإغلاق");
    }



    @AfterClass
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}


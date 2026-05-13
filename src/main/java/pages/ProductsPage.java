package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductsPage extends BasePage {

    private final By TITLE = By.cssSelector("[data-test=title]");
    private final By MENU_BUTTON = By.id("react-burger-menu-btn");
    private final By SHOPPING_CART_BUTTON = By.id("shopping_cart_container");
    private final By PRODUCT_SORT_DROPDOWN = By.xpath("//*[@data-test='product-sort-container']");
    private final By ADD_TO_CART_BUTTON = By.xpath("//*[contains(@id, 'add-to-cart-')]");
    private final By REMOVE_BUTTON = By.xpath("//*[contains(@id, 'remove-')]");
    private final By PRODUCT_NAME = By.xpath("//*[@data-test='inventory-item-name']");
    private final By PRODUCT_DESCRIPTION = By.xpath("//*[@data-test='inventory-item-desc']");
    private final By PRODUCT_PRICE = By.xpath("//*[@data-test='inventory-item-price']");
    private final By COUNT_PRODUCTS = By.xpath("//*[@data-test='inventory-item']");
    private final By COUNT_ON_CART_ICON = By.xpath("//*[@data-test='shopping-cart-badge']");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }
    //открытие страницы продуктов
    public void open() {
        driver.get(BASE_URL + "/inventory.html");
    }
    //получение заголовка на странице продуктов
    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }
    //получение наименования продукта (по индексу)
    public String getProductName(int indexProduct) {
        List<WebElement> productName = driver.findElements(PRODUCT_NAME);
        return productName.get(indexProduct).getText();
    }
    //получение цена продукта (по индексу)
    public String getProductPrice(int indexPrice) {
        List<WebElement> productPrice = driver.findElements(PRODUCT_PRICE);
        return productPrice.get(indexPrice).getText();
    }
    //добавление товара в корзину по индексу
    public void addProductInCart(int indexProduct) {
        List<WebElement> products = driver.findElements(ADD_TO_CART_BUTTON);
        products.get(indexProduct).click();
    }
    //удаление продукта
    public  void removeProductFromProductsList(int indexProduct) {
        driver.findElements(REMOVE_BUTTON).get(indexProduct).click();
    }
    //переход в корзину по клику на иконку корзины
    public void goToCart() {
        driver.findElement(SHOPPING_CART_BUTTON).click();
    }
    //получение кол-ва продуктов на странице
    public Integer getCountProducts() {
        List<WebElement> countProducts = driver.findElements(COUNT_PRODUCTS);
        return countProducts.size();
    }
    //кол-во товаров в иконке корзины
    public String getCountOnCartIcon() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(COUNT_ON_CART_ICON));
        return driver.findElement(COUNT_ON_CART_ICON).getText();
        } catch (Exception e) {
            return "0";
        }
    }
}
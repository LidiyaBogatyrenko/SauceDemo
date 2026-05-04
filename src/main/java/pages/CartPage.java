package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends BasePage {

    private final By TITLE = By.xpath("//*[@data-test='title']");
    private final By CHECKOUT_BUTTON = By.xpath("//*[@data-test='checkout']");
    private final By CONTINUE_SHOPPING_BUTTON = By.xpath("//*[@id='continue-shopping']");
    private final By REMOVE_BUTTON = By.xpath("//*[contains(@id, 'remove-')]");
    private final By COUNT_PRODUCT_IN_CART = By.xpath("//*[@data-test='inventory-item']");
    private final By PRODUCT_NAME = By.xpath("//*[@data-test='inventory-item-name']");
    private final By PRODUCT_DESCRIPTION = By.xpath("//*[@data-test='inventory-item-desc']");
    private final By PRODUCT_PRICE = By.xpath("//*[@data-test='inventory-item-price']");

    public CartPage(WebDriver driver) {
        super(driver);
    }
    //получение заголовка корзины
    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }
    //кол-во продуктов в корзине
    public int countProductInCart() {
        return driver.findElements(COUNT_PRODUCT_IN_CART).size();
    }
    //получение наименования продукта в корзине
    public String getProductNameInCart(int indexProduct) {
        List<WebElement> productNameInCart = driver.findElements(PRODUCT_NAME);
        return productNameInCart.get(indexProduct).getText();
    }
    //получение цены продукта
    public String getProductPriceInCart(int indexProduct) {
        List<WebElement> productPriceInCart = driver.findElements(PRODUCT_PRICE);
        return productPriceInCart.get(indexProduct).getText();
    }
    //получение описания продукта
    public String getProductDescription(int indexProduct) {
        return driver.findElements(PRODUCT_DESCRIPTION).get(indexProduct).getText();
    }
    //удаление продукта из корзины (по индексу)
    public void removeProductInCart(int indexProduct) {
        List<WebElement> removeButton = driver.findElements(REMOVE_BUTTON);
        removeButton.get(indexProduct).click();
    }
    //переход к checkout
    public void goToCheckout() {
        driver.findElement(CHECKOUT_BUTTON).click();
    }
    //переход к списку продуктов нажатием на кнопку "Continue Shopping"
    public void goToProductsList() {
        driver.findElement(CONTINUE_SHOPPING_BUTTON).click();
    }
}
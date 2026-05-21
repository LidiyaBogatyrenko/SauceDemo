package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

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

    @Override
    @Step("Открытие страницы корзины")
    public CartPage openPage() {
        driver.get(BASE_URL + "/cart.html");
        return this;
    }

    @Override
    @Step("Проверка, что страница загружена")
    public CartPage isPageOpened() {
        wait.until(driver -> ((JavascriptExecutor) driver)
                .executeScript("return document.readyState").equals("complete"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        return this;
    }

    @Step("Получение заголовка корзины")
    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    @Step("Подсчёт кол-ва товаров в корзине")
    public int countProductInCart() {
        return driver.findElements(COUNT_PRODUCT_IN_CART).size();
    }

    @Step("Получение наименования товара в корзине")
    public String getProductNameInCart(int indexProduct) {
        List<WebElement> productNameInCart = driver.findElements(PRODUCT_NAME);
        return productNameInCart.get(indexProduct).getText();
    }

    @Step("Получение цены товара")
    public String getProductPriceInCart(int indexProduct) {
        List<WebElement> productPriceInCart = driver.findElements(PRODUCT_PRICE);
        return productPriceInCart.get(indexProduct).getText();
    }

    @Step("Получение описания товара")
    public String getProductDescription(int indexProduct) {
        return driver.findElements(PRODUCT_DESCRIPTION).get(indexProduct).getText();
    }

    @Step("Удаление товара из корзины (по индексу)")
    public CartPage removeProductInCart(int indexProduct) {
        List<WebElement> removeButton = driver.findElements(REMOVE_BUTTON);
        removeButton.get(indexProduct).click();
        return this;
    }

    @Step("Переход на страницу Checkout")
    public CheckoutPage goToCheckout() {
        driver.findElement(CHECKOUT_BUTTON).click();
        return new CheckoutPage(driver);
    }

    @Step("Переход к списку продуктов нажатием на кнопку \"Continue Shopping\"")
    public ProductsPage goToProductsList() {
        driver.findElement(CONTINUE_SHOPPING_BUTTON).click();
        return new ProductsPage(driver);
    }
}
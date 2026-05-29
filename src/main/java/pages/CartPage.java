package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

@Log4j2
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
        log.info("CartPage opening");
        driver.get(BASE_URL + "/cart.html");
        return this;
    }

    @Override
    @Step("Проверка, что страница загружена")
    public CartPage isPageOpened() {
        log.info("CartPage loading");
        wait.until(driver -> ((JavascriptExecutor) driver)
                .executeScript("return document.readyState").equals("complete"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        return this;
    }

    @Step("Получение заголовка корзины")
    public String getTitle() {
        log.info("Getting the title of CartPage");
        return driver.findElement(TITLE).getText();
    }

    @Step("Подсчёт кол-ва товаров в корзине")
    public int countProductInCart() {
        log.info("Calculating the number of products in the cart");
        return driver.findElements(COUNT_PRODUCT_IN_CART).size();
    }

    @Step("Получение наименования товара в корзине")
    public String getProductNameInCart(int indexProduct) {
        log.info("Getting the product name in the cart");
        List<WebElement> productNameInCart = driver.findElements(PRODUCT_NAME);
        return productNameInCart.get(indexProduct).getText();
    }

    @Step("Получение цены товара")
    public String getProductPriceInCart(int indexProduct) {
        log.info("Getting the product price");
        List<WebElement> productPriceInCart = driver.findElements(PRODUCT_PRICE);
        return productPriceInCart.get(indexProduct).getText();
    }

    @Step("Получение описания товара")
    public String getProductDescription(int indexProduct) {
        log.info("Getting the product description");
        return driver.findElements(PRODUCT_DESCRIPTION).get(indexProduct).getText();
    }

    @Step("Удаление товара из корзины (по индексу)")
    public CartPage removeProductInCart(int indexProduct) {
        log.info("Removing an item from the cart (by index)");
        List<WebElement> removeButton = driver.findElements(REMOVE_BUTTON);
        removeButton.get(indexProduct).click();
        return this;
    }

    @Step("Переход на страницу Checkout")
    public CheckoutPage goToCheckout() {
        log.info("Going to the CheckoutPage");
        driver.findElement(CHECKOUT_BUTTON).click();
        return new CheckoutPage(driver);
    }

    @Step("Переход к списку продуктов нажатием на кнопку \"Continue Shopping\"")
    public ProductsPage goToProductsList() {
        log.info("Go to the list of products by clicking on the \"Continue Shopping\" button");
        driver.findElement(CONTINUE_SHOPPING_BUTTON).click();
        return new ProductsPage(driver);
    }
}
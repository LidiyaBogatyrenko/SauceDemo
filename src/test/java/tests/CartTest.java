package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CartTest extends BaseTest {
    /*
    1. Продолжаем в проекте SauceDemo
    2. Закончить поиск локаторов после практики
    3. Создать отдельный Java-класс с тестом, сценарий:
    3a. Залогиниться
    3b. Добавить товар в корзину
    3c. Перейти в корзину
    3d. Проверить (assertEquals) стоимость товара и его имя в корзине
    4. Создать Pull Request и добавить ментора в коллабораторы
     */
    @Test //добавление одного продукта в корзину и проверка его наименования и цены
    public void addOneProductInCart() {
        SoftAssert softAssert = new SoftAssert();

        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.getTitle();

        //получаем наименование и цену продукта в списке товаров
        String nameItem = productsPage.getProductName(3);
        String priceItem = productsPage.getProductPrice(3);
        //добавляем продукта в корзину
        productsPage.addProductInCart(3);
        //переходим в корзину
        productsPage.goToCart();
        //получаем наименование и цену продукта
        String nameItemInCart = cartPage.getProductNameInCart(0);
        String priceItemInCart = cartPage.getProductPriceInCart(0);
        //сравниваем наименование продукта и цену в списке и в корзине
        softAssert.assertEquals(nameItemInCart, nameItem);
        softAssert.assertEquals(priceItemInCart, priceItem);
        driver.quit();
        softAssert.assertAll();
    }

    @Test //проверка удаления продукта из корзины
    public void removeProductFromCart() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        //достаём наименование первого продукта и добавляем его в корзину
        String productNameInList = productsPage.getProductName(0);
        productsPage.addProductInCart(0);
        //проверяем число продуктов в иконке корзины
        softAssert.assertEquals(productsPage.getCountOnCartIcon(), "1");
        //переходим в корзину один товар, который добавили
        productsPage.goToCart();
        softAssert.assertEquals(cartPage.countProductInCart(), 1);
        String productNameInCart = cartPage.getProductNameInCart(0);
        softAssert.assertEquals(productNameInCart, productNameInList);
        //удаляем продукт и проверяем, что корзина пустая и счётчик в иконке пустой
         productsPage.removeProductFromProductsList(0);
         softAssert.assertEquals(cartPage.countProductInCart(),0);
         softAssert.assertAll();
    }
}
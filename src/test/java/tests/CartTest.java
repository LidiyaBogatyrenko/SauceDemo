package tests;

import io.qameta.allure.*;
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
    @Test(testName = "Добавление одного товара в корзину",
            description = "Добавление одного товара в корзину и проверка его наименования и цены",
            groups = {"smoke"}
    )
    @Description("Проверка добавления одного товара в корзину и проверка его наименования и цены")
    @Severity(SeverityLevel.NORMAL)
    @Link("https://confluence.pflb.ru/")
    @TmsLink("CMCH-4")
    @Issue("CMCH-4")
    @Owner("Bogatyrenko Lidiya")
    public void addOneProductInCart() {
        SoftAssert softAssert = new SoftAssert();
        loginStep.possitiveAuth("standard_user", "secret_sauce");
        //получаем наименование и цену продукта в списке товаров
        String nameItem = productsPage.getProductName(3);
        String priceItem = productsPage.getProductPrice(3);
        //добавляем продукта в корзину и переходим в корзину
        productsPage.addProductInCart(3)
        .goToCart();
        //получаем наименование и цену продукта. Сравниваем их в корзине
        String nameItemInCart = cartPage.getProductNameInCart(0);
        String priceItemInCart = cartPage.getProductPriceInCart(0);
        softAssert.assertEquals(nameItemInCart, nameItem);
        softAssert.assertEquals(priceItemInCart, priceItem);
        driver.quit();
        softAssert.assertAll();
    }

    @Test(testName = "Удаление товара из корзины",
            description = "Удаление товара из корзины",
            groups = {"regress"}
    )
    @Description("Проверка удаления товара из корзины")
    @Severity(SeverityLevel.NORMAL)
    @Link("https://confluence.pflb.ru/")
    @TmsLink("CMCH-5")
    @Issue("CMCH-5")
    @Owner("Bogatyrenko Lidiya")
    public void removeProductFromCart() {
        SoftAssert softAssert = new SoftAssert();
        loginStep.possitiveAuth("standard_user", "secret_sauce");
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
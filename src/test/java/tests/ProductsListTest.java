package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ProductsListTest extends BaseTest {

    @Test(testName = "Добавление и удаление продуктов из корзины",
            description = "Позитивная проверка добавления и удаления продуктов из корзины",
            groups = {"regress"}
    )
    public void removeProductsFromProductsList() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.getTitle();
        //добавляем все товары в корзину, которые есть на странице
        int countProductsInProductsList = productsPage.getCountProducts();
        for (int i = 1; i <= countProductsInProductsList; i++) {
            productsPage.addProductInCart(0);
        }
        //проверяем число продуктов в иконке корзины
        String countOnCartIcon = productsPage.getCountOnCartIcon();
        int countProductOnCartIcon = Integer.parseInt(countOnCartIcon);
        softAssert.assertEquals(countProductOnCartIcon, countProductsInProductsList);
        //проверяем, что кол-во кнопок remove равно кол-ву продуктов в списке
        int countRemoveButton = driver.findElements(By.xpath("//*[contains(@id, 'remove-')]")).size();
        softAssert.assertEquals(countRemoveButton, countProductsInProductsList);
        //удаляем все продукты и проверяем, что все товары удалены (нет кнопок remove и счётчик в иконке пуст)
        for (int i = 1; i <= countRemoveButton; i++) {
            productsPage.removeProductFromProductsList(0);
        }
        softAssert.assertEquals(productsPage.getCountOnCartIcon(), "0");
        softAssert.assertAll();
    }
}
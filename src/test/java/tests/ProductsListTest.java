package tests;

import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ProductsListTest extends BaseTest {

    @Test(testName = "Добавление и удаление товаров из корзины",
            description = "Добавление и удаление товаров из корзины",
            groups = {"regress"}
    )
    @Description("Позитивная проверка добавления и удаления товаров из корзины")
    @Severity(SeverityLevel.NORMAL)
    @Link("https://confluence.pflb.ru/")
    @TmsLink("CMCH-3")
    @Issue("CMCH-3")
    @Owner("Bogatyrenko Lidiya")
    public void removeProductsFromProductsList() {
        SoftAssert softAssert = new SoftAssert();
        loginStep.possitiveAuth("standard_user", "secret_sauce");
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
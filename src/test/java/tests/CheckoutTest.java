package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CheckoutTest extends BaseTest {

    @Test(testName = "Успешное заполнение информации для оформления заказа",
            description = "Позитивная проверка заполнения информации для оформления заказа",
            groups = {"regress"}
    )
    public void fillPositiveCheckoutInformation () {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProductInCart(0);
        productsPage.goToCart();
        softAssert.assertEquals(cartPage.countProductInCart(), 1);
        cartPage.goToCheckout();
        softAssert.assertEquals(checkoutPage.getTitle(), "Checkout: Your Information");
        checkoutPage.fillCheckoutInformation("Max", "Power","123-456");
        checkoutPage.clickContinue();
        softAssert.assertEquals(checkoutOverviewPage.getTitle(), "Checkout: Overview");
        softAssert.assertAll();
    }

    @DataProvider(name = "Тестовые данные для негативных проверок заполнения информации для оформления заказа")
    public Object[][] CheckoutData() {
        return new  Object[][] {
                {"", "Power", "123-456", "Error: First Name is required"},
                {"Max", "", "123-456", "Error: Last Name is required"},
                {"Max", "Power", "", "Error: Postal Code is required"}
        };
    }
    @Test(dataProvider = "Тестовые данные для негативных проверок заполнения информации для оформления заказа",
    testName = "Ошибки при заполнении информации для оформления заказа",
    description = "Проверка получения ошибки при заполнении информации для оформления заказа с пустым именем/фамилией/индексом",
    groups = {"regress"})
    public  void negativeCheckoutInformation(String firstName, String lastName, String postalCode, String errorMessage) {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProductInCart(0);
        productsPage.goToCart();
        softAssert.assertEquals(cartPage.countProductInCart(), 1);
        cartPage.goToCheckout();
        softAssert.assertEquals(checkoutPage.getTitle(), "Checkout: Your Information");
        checkoutPage.fillCheckoutInformation(firstName, lastName,postalCode);
        checkoutPage.clickContinue();
        softAssert.assertEquals(checkoutPage.getErrorMessage(), errorMessage);
        softAssert.assertAll();
    }
}
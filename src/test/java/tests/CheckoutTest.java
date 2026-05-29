package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CheckoutTest extends BaseTest {

    @Test(testName = "Успешное заполнение информации для оформления заказа",
            description = "Успешное заполнение информации для оформления заказа",
            groups = {"regress"}
    )
    @Description("Позитивная проверка заполнения информации для оформления заказа")
    @Severity(SeverityLevel.NORMAL)
    @Link("https://confluence.pflb.ru/")
    @TmsLink("CMCH-6")
    @Issue("CMCH-6")
    @Owner("Bogatyrenko Lidiya")
    public void fillPositiveCheckoutInformation () {
        SoftAssert softAssert = new SoftAssert();
        loginStep.possitiveAuth("standard_user", "secret_sauce");
        productsPage.addProductInCart(0)
                .goToCart();
        softAssert.assertEquals(cartPage.countProductInCart(), 1);
        cartPage.goToCheckout();
        softAssert.assertEquals(checkoutPage.getTitle(), "Checkout: Your Information");
        checkoutPage.fillCheckoutInformation("Max", "Power","123-456")
                .clickContinue();
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
    description = "Ошибки при заполнении информации для оформления заказа",
    groups = {"regress"})
    @Description("Проверка получения ошибки при заполнении информации для оформления заказа с пустым именем/фамилией/индексом")
    @Severity(SeverityLevel.NORMAL)
    @Link("https://confluence.pflb.ru/")
    @TmsLink("CMCH-7")
    @Issue("CMCH-7")
    @Owner("Bogatyrenko Lidiya")
    public  void negativeCheckoutInformation(String firstName, String lastName, String postalCode, String errorMessage) {
        SoftAssert softAssert = new SoftAssert();
        loginStep.possitiveAuth("standard_user", "secret_sauce");
        productsPage.addProductInCart(0)
                .goToCart();
        softAssert.assertEquals(cartPage.countProductInCart(), 1);
        cartPage.goToCheckout();
        softAssert.assertEquals(checkoutPage.getTitle(), "Checkout: Your Information");
        checkoutPage.fillCheckoutInformation(firstName, lastName,postalCode)
                .clickContinue();
        softAssert.assertEquals(checkoutPage.getErrorMessage(), errorMessage);
        softAssert.assertAll();
    }
}
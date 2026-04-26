package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CheckoutTest extends BaseTest {

    @Test
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
    }

    @Test
    public void fillCheckoutInformationWithoutFirstName() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProductInCart(0);
        productsPage.goToCart();
        softAssert.assertEquals(cartPage.countProductInCart(), 1);
        cartPage.goToCheckout();
        softAssert.assertEquals(checkoutPage.getTitle(), "Checkout: Your Information");
        checkoutPage.fillCheckoutInformation("", "Power","123-456");
        checkoutPage.clickContinue();
        softAssert.assertEquals(checkoutPage.getErrorMessage(), "Error: First Name is required");
    }

    @Test
    public void fillCheckoutInformationWithoutLastName() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProductInCart(0);
        productsPage.goToCart();
        softAssert.assertEquals(cartPage.countProductInCart(), 1);
        cartPage.goToCheckout();
        softAssert.assertEquals(checkoutPage.getTitle(), "Checkout: Your Information");
        checkoutPage.fillCheckoutInformation("Max", "","123-456");
        checkoutPage.clickContinue();
        softAssert.assertEquals(checkoutPage.getErrorMessage(), "Error: Last Name is required");
    }

    @Test
    public void fillCheckoutInformationWithoutPostalCode() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProductInCart(0);
        productsPage.goToCart();
        softAssert.assertEquals(cartPage.countProductInCart(), 1);
        cartPage.goToCheckout();
        softAssert.assertEquals(checkoutPage.getTitle(), "Checkout: Your Information");
        checkoutPage.fillCheckoutInformation("Max", "Power","");
        checkoutPage.clickContinue();
        softAssert.assertEquals(checkoutPage.getErrorMessage(), "Error: Postal Code is required");
    }
}
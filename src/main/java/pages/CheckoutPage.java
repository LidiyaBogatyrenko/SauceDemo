package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends BasePage {

    private final By TITLE = By.xpath("//*[@data-test='title']");
    private final By FIRST_NAME = By.id("first-name");
    private final By LAST_NAME = By.id("last-name");
    private final By POSTAL_CODE = By.id("postal-code");
    private final By ERROR_MESSAGE = By.xpath("//*[@data-test='error']");
    private final By CANCEL_BUTTON = By.id("cancel");
    private final By CONTINUE_BUTTON = By.id("continue");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public CheckoutPage openPage() {
        driver.get(BASE_URL + "/checkout-step-one.html");
        return this;
    }

    @Override
    public CheckoutPage isPageOpened() {
        wait.until(driver -> ((JavascriptExecutor) driver)
                .executeScript("return document.readyState").equals("complete"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        return this;
    }

    @Step("Получение заголовка Checkout: Your Information")
    public String getTitle () {
        return driver.findElement(TITLE).getText();
    }

    @Step("Заполнение информации в Checkout с именем: '{firstName}, фамилией: '{lastName}' и индексом: '{postalCode}'")
    public CheckoutPage fillCheckoutInformation(String firstName, String lastName, String postalCode) {
        driver.findElement(FIRST_NAME).sendKeys(firstName);
        driver.findElement(LAST_NAME).sendKeys(lastName);
        driver.findElement(POSTAL_CODE).sendKeys(postalCode);
        return this;
    }
    @Step("Получение ошибки при вводе информации для оформления заказа")
    public String getErrorMessage() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }

    @Step("Нажатие на кнопку Continue")
    public CheckoutOverviewPage clickContinue () {
        driver.findElement(CONTINUE_BUTTON).click();
        return new CheckoutOverviewPage(driver);
    }

    @Step("Нажатие на кнопку Cancel")
    public CartPage clickCancel() {
        driver.findElement(CANCEL_BUTTON).click();
        return new CartPage(driver);
    }
}
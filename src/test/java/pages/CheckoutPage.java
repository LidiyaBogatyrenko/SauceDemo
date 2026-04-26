package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
    //получение заголовка Checkout: Your Information
    public String getTitle () {
        return driver.findElement(TITLE).getText();
    }
    //заполнить информацию в Checkout
    public void fillCheckoutInformation(String firstName, String lastName, String postalCode) {
        driver.findElement(FIRST_NAME).sendKeys(firstName);
        driver.findElement(LAST_NAME).sendKeys(lastName);
        driver.findElement(POSTAL_CODE).sendKeys(postalCode);
    }
    //получить текст ошибки
    public String getErrorMessage() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }
    //нажать на кнопку Continue
    public void clickContinue () {
        driver.findElement(CONTINUE_BUTTON).click();
    }
    //нажать на кнопку Cancel
    public  void clickCancel() {
        driver.findElement(CANCEL_BUTTON).click();
    }
}
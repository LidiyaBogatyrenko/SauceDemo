package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
    /*
    1. Описываем в классе элементы, с которыми мы взаимодействуем.
    2. Описываем методы взаимодействия с этими элементами.
     */
    private final By USERNAME_FIELD = By.xpath("//*[@id=\"user-name\"]");
    private final By PASSWORD_FIELD = By.xpath("//*[@id=\"password\"]");
    private final By LOGIN_BUTTON = By.xpath("//*[@id=\"login-button\"]");
    private final By ERROR_MESSAGE = By.xpath("//*[@data-test=\"error\"]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public LoginPage openPage() {
        driver.get(BASE_URL);
        return this;
    }

    @Override
    public LoginPage isPageOpened() {
        wait.until(driver -> ((JavascriptExecutor) driver)
                .executeScript("return document.readyState").equals("complete"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));
        return this;
    }

    @Step("Вход в систему с логином:'{user}' и паролем: '{password}'")
    public ProductsPage login(String user, String password) {
        driver.findElement(USERNAME_FIELD).sendKeys(user);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
        return new ProductsPage(driver);
    }

    @Step("Получение ошибки при входе в систему")
    public String getErrorMessage() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }
}
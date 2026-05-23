package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
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
        log.info("LoginPage opening");
        driver.get(BASE_URL);
        return this;
    }

    @Override
    public LoginPage isPageOpened() {
        log.info("LoginPage loading");
        wait.until(driver -> ((JavascriptExecutor) driver)
                .executeScript("return document.readyState").equals("complete"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));
        return this;
    }

    @Step("Вход в систему с логином:'{user}' и паролем: '{password}'")
    public ProductsPage login(String user, String password) {
        log.info("Log in with a username:'{}' and password: '{}'", user, password);
        driver.findElement(USERNAME_FIELD).sendKeys(user);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
        return new ProductsPage(driver);
    }

    @Step("Получение ошибки при входе в систему")
    public String getErrorMessage() {
        log.info("Getting the error message");
        return driver.findElement(ERROR_MESSAGE).getText();
    }
}
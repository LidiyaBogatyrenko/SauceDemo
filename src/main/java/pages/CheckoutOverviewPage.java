package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class CheckoutOverviewPage extends BasePage {

    private final By TITLE = By.xpath("//*[@data-test='title']");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public CheckoutOverviewPage openPage() {
        log.info("CheckoutOverviewPage opening");
        driver.get(BASE_URL + "/checkout-step-two.html");
        return this;
    }

    @Override
    public CheckoutOverviewPage isPageOpened() {
        log.info("CheckoutOverviewPage loading");
        wait.until(driver -> ((JavascriptExecutor) driver)
                .executeScript("return document.readyState").equals("complete"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        return this;
    }

    @Step("Получение заголовка Checkout: Overview")
    public String getTitle() {
        log.info("Getting the title of CheckoutOverviewPage");
        return driver.findElement(TITLE).getText();
    }
}
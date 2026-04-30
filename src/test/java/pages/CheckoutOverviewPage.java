package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOverviewPage extends BasePage {

    private final By TITLE = By.xpath("//*[@data-test='title']");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }
    //получение заголовка Checkout: Overview
    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }
}
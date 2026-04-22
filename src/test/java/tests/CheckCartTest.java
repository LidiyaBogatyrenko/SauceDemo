package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.HashMap;

public class CheckCartTest {
    /*
    1. Продолжаем в проекте SauceDemo
    2. Закончить поиск локаторов после практики
    3. Создать отдельный Java-класс с тестом, сценарий:
    3a. Залогиниться
    3b. Добавить товар в корзину
    3c. Перейти в корзину
    3d. Проверить (assertEquals) стоимость товара и его имя в корзине
    4. Создать Pull Request и добавить ментора в коллабораторы
     */
    @Test
    public void checkCart() {
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        WebDriver driver = new ChromeDriver(options);
        SoftAssert softAssert = new SoftAssert();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://www.saucedemo.com/");
        //Логинимся на сайте
        driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("standard_user");
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
        //получаем наименование товара и цену в списке товаров
        String nameItem = driver.findElement(By.xpath("//*[@id=\"item_5_title_link\"]")).getText();
        String priceItem = driver.findElement(By.xpath("//*[@id='item_5_title_link']/ancestor::div[@class='inventory_item']//descendant::div[@class='inventory_item_price']")).getText();
        //добавляем товар в корзину
        driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-fleece-jacket\"]")).click();
        //переходим в корзину
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]")).click();
        String nameItemInCart = driver.findElement(By.xpath("//*[@id=\"item_5_title_link\"]")).getText();
        String priceItemInCart = driver.findElement(By.xpath("//*[@class='inventory_item_price']")).getText();
        //сравниваем наименование товара и цену в списке и в корзине
        softAssert.assertEquals(nameItemInCart, nameItem);
        softAssert.assertEquals(priceItemInCart, priceItem);
        driver.quit();
        softAssert.assertAll();
    }
}
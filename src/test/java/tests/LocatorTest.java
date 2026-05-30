package tests;
import org.openqa.selenium.By;

import org.testng.annotations.Test;


public class LocatorTest extends BaseTest {

    @Test
    public void checkLocator() {

        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name"));
        driver.findElement(By.name("user-name"));
        driver.findElement(By.className("login_credentials_wrap"));
        driver.findElement(By.tagName("div"));
        //логинимся
        driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys(user);
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(password);
        driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
        //продолжаем поиск локаторов
        driver.findElement(By.linkText("Sauce Labs Backpack"));
        driver.findElement(By.partialLinkText("Bolt"));
        //xpath: поиск по атрибуту, например By.xpath("//tag[@attribute='value']");
        driver.findElement(By.xpath("//div[@class='app_logo']"));
        //xpath: поиск по тексту, например By.xpath("//tag[text()='text']");
        driver.findElement(By.xpath("//div[text()='Swag Labs']"));
        //xpath: поиск по частичному совпадению атрибута, например
        //By.xpath("//tag[contains(@attribute,'text')]");
        driver.findElement(By.xpath("//div[contains(@id,'page')]"));
        //xpath: поиск по частичному совпадению текста, например By.xpath("//tag[contains(text(),'text')]");
        driver.findElement(By.xpath("//div[contains(text(),'Swag')]"));
        //xpath: ancestor, например //*[text()='Enterprise Testing']//ancestor::div
        driver.findElement(By.xpath("//*[text()='Sauce Labs Backpack']//ancestor::div[@class='inventory_item_label']"));
        //xpath: descendant
        driver.findElement(By.xpath("//*[@id='inventory_container']//descendant::div[@class='inventory_item_img']"));
        //xpath: following
        driver.findElement(By.xpath("//div[@id='inventory_container']//following::div[@class='inventory_item']"));
        //xpath: parent
        driver.findElement(By.xpath("//div[@id='inventory_container']//parent::div[@id='contents_wrapper']"));
        //xpath: preceding
        driver.findElement(By.xpath("//div[@class='inventory_item_desc']//preceding::*[contains(text(), 'Sauce Labs Backpack')]"));
        //xpath: *поиск элемента с условием AND, например //input[@class='_2zrpKA_1dBPDZ'and @type='text']
        driver.findElement(By.xpath("//button[contains(@data-test, 'add-to-cart') and contains(@id, 'sauce-labs-backpack')] "));
        //css: .class
        driver.findElement(By.cssSelector(".bm-burger-button"));
        //css: .class1.class2
        driver.findElement(By.cssSelector(".btn.btn_primary.btn_small.btn_inventory"));
        //css: .class1 .class2
        driver.findElement(By.cssSelector(".shopping_cart_container .shopping_cart_link"));
        //css: #id
        driver.findElement(By.cssSelector("#item_4_title_link"));
        //css: tagname
        driver.findElement(By.cssSelector("button"));
        //css: tagname.class
        driver.findElement(By.cssSelector("select.product_sort_container"));
        //css: [attribute=value]
        driver.findElement(By.cssSelector("[class=pricebar]"));
        //css: [attribute~=value]
        driver.findElement(By.cssSelector("[class~=btn]"));
        //css: [attribute|=value]
        driver.findElement(By.cssSelector("[data-test|=secondary]"));
        //css: [attribute^=value]
        driver.findElement(By.cssSelector("[href^=https]"));
        //css: [attribute$=value]
        driver.findElement(By.cssSelector("[href$=\"saucelabs\"]"));
        //css: [attribute*=value]
        driver.findElement(By.cssSelector("[href*=\"sauce\"]"));
    }
}
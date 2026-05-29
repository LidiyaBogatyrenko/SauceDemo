package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(testName = "Проверка входа в систему с позитивными кредами",
            description = "Позитивная проверка входа в систему с корректными кредами",
            groups = {"smoke"})
    @Description("Проверка входа в систему с позитивными кредами")
    @Epic("E2E")
    @Feature("Login to SauceDemo")
    @Story("ЯКК хочу логиниться в SuaceDemo")
    @Severity(SeverityLevel.BLOCKER)
    @Link("https://confluence.pflb.ru/")
    @TmsLink("CMCH-1")
    @Issue("CMCH-1")
    @Owner("Bogatyrenko Lidiya")
    public void checkPositiveLogin() {
        loginStep.possitiveAuth(user, password);
        Assert.assertEquals(productsPage.getTitle(), "Products");
    }
    //Тестовые данные для негативного тестирования входа в систему
    @DataProvider(name = "Тестовые данные для негативного логина")
    public  Object[][] loginData() {
        return  new  Object[][] {
                {user, "", "Epic sadface: Password is required"}, // без логина
                {"", "secret_sauce", "Epic sadface: Username is required"}, // без пароля
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."}, // заблокированных пользователь
                {"test", "test", "Epic sadface: Username and password do not match any user in this service"} // с негативными кредами
        };
    }

    @Test(dataProvider = "Тестовые данные для негативного логина",
    testName = "Ошибки при входе в систему",
    description = "Проверка получения ошибок при входе в систему без логина/без пароля/под заблокированным пользователем/с негативными кредами",
    groups = {"smoke"})
    @Description("Проверка входа в систему с позитивными кредами")
    @Severity(SeverityLevel.NORMAL)
    @Link("https://confluence.pflb.ru/")
    @TmsLink("CMCH-2")
    @Issue("CMCH-2")
    @Owner("Bogatyrenko Lidiya")
    public void allTestNegativeLogin(String user, String password, String errorMessage) {
        loginStep.negativeAuth(user, password);
        Assert.assertEquals(loginPage.getErrorMessage(), errorMessage);
    }
}
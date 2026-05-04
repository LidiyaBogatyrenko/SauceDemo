package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(testName = "Проверка входа в систему с позитивными кредами",
            description = "Позитивная проверка входа в систему с корректными кредами",
            groups = {"smoke"})
    public void checkPositiveLogin() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertEquals(productsPage.getTitle(), "Products");
    }
    //Тестовые данные для негативного тестирования входа в систему
    @DataProvider(name = "Тестовые данные для негативного логина")
    public  Object[][] loginData() {
        return  new  Object[][] {
                {"standard_user", "", "Epic sadface: Password is required"}, // без логина
                {"", "secret_sauce", "Epic sadface: Username is required"}, // без пароля
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."}, // заблокированных пользователь
                {"test", "test", "Epic sadface: Username and password do not match any user in this service"} // с негативными кредами
        };
    }

    @Test(dataProvider = "Тестовые данные для негативного логина",
    testName = "Ошибки при входе в систему",
    description = "Проверка получения ошибок при входе в систему без логина/без пароля/под заблокированным пользователем/с негативными кредами",
    groups = {"smoke"})
    public void allTestNegativeLogin(String user, String password, String errorMessage) {
        loginPage.open();
        loginPage.login(user, password);
        Assert.assertEquals(loginPage.getErrorMessage(), errorMessage);
    }
}
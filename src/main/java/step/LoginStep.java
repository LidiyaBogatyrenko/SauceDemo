package step;

import org.openqa.selenium.WebDriver;
import pages.LoginPage;

public class LoginStep {
    WebDriver driver;
    LoginPage loginPage;

    public LoginStep(WebDriver driver) {
        this.driver = driver;
        loginPage = new LoginPage(driver);
    }

    public void possitiveAuth(String user, String password) {
        loginPage.openPage()
                .isPageOpened()
                .login(user, password)
                .getTitle();
    }

    public void negativeAuth(String user, String password) {
        loginPage.openPage()
                .isPageOpened()
                .login(user, password);
    }
}
package tests;

import org.testng.annotations.Test;
import tests.base.BaseTest;

public class LoginPageTest extends BaseTest {

    @Test
    public void successfulLogin() {
        loginPageSteps
                .goToLoginPage()
                .tryToLogin(getUsername(), getPassword());
    }
}
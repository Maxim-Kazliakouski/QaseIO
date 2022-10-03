package pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class LoginPage {

    public LoginPage openPage() {
        open("/login");
        return this;
    }

    public void isOpened() {
        $(By.id("btnLogin")).shouldBe(Condition.visible);
    }

    public void login(String username, String password) {
        $(By.id("inputEmail")).sendKeys(username);
        $(By.id("inputPassword")).sendKeys(password);
        $(By.id("btnLogin")).click();
    }
}
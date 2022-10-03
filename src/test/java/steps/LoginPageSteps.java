package steps;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import pages.LoginPage;
import pages.ProjectsPage;

@Log4j2
public class LoginPageSteps {

    LoginPage loginPage;
    ProjectsPage projectsPage;

    public LoginPageSteps() {
        loginPage = new LoginPage();
        projectsPage = new ProjectsPage();
    }

    @Step("Go to login page")
    public LoginPageSteps goToLoginPage() {
        log.info("Go to login page");
        loginPage
                .openPage()
                .isOpened();
        return this;
    }

    @Step("Trying to login")
    public void tryToLogin(String username, String password) {
        log.info("Trying to login");
        loginPage.login(username, password);
        projectsPage.isOpened();
    }
}
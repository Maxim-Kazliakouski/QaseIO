package steps;

import com.codeborne.selenide.Condition;
import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import pages.LoginPage;
import pages.ProjectsPage;
import utils.PropertyReader;

@Log4j2
public class LoginPageSteps {

    Faker faker;
    LoginPage loginPage;
    ProjectsPage projectsPage;
    String username;
    String password;

    public LoginPageSteps() {
        faker = new Faker();
        loginPage = new LoginPage();
        projectsPage = new ProjectsPage();
    }

    public LoginPageSteps goToLoginPage() {
        loginPage
                .openPage()
                .isOpened();
        return this;
    }

    public void tryToLogin(String username, String password) {
        loginPage.login(username, password);
    }

    @Step("Get error message")
    public void getErrorMessage(String message) {
        loginPage.getErrorMessage().shouldBe(Condition.visible);
        loginPage.getErrorMessage().shouldHave(Condition.text(message));
    }

    @Given("Valid username and password")
    public void validLoginAndPassword() {
        username = PropertyReader.getProperty("qase.username");
        password = PropertyReader.getProperty("qase.password");
    }

    @Then("User gets to the Projects page")
    public void userGetsToTheProjectsPage() {
        projectsPage.isOpened();
    }

    @Step("User logs in CUCUMBER")
    @When("User logs in")
    public void userLogs() {
        goToLoginPage()
                .tryToLogin(username, password);
    }

    @Given("Invalid username and password")
    public void invalidUsernameAndPassword() {
        username = faker.internet().emailAddress();
        password = faker.code().ean8().toLowerCase();
    }

    @Then("User gets error message {string}")
    public void userGetsErrorMessage(String message) {
        getErrorMessage(message);
    }
}
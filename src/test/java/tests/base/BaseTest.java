package tests.base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.*;
import steps.CreatedProjectPageSteps;
import steps.LoginPageSteps;
import steps.NewProjectPageSteps;
import steps.ProjectsPageSteps;
import utils.PropertyReader;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public abstract class BaseTest {
    public Faker faker;
    public LoginPageSteps loginPageSteps;
    public ProjectsPageSteps projectsPageSteps;
    public NewProjectPageSteps newProjectPageSteps;
    public CreatedProjectPageSteps createdProjectPageSteps;
    String username;
    String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Parameters({"browserType", "headlessMode"})
    @BeforeMethod(description = "start browser")
    public void setup(@Optional("chrome") String browserType, @Optional("false") Boolean headlessMode) {
        Configuration.baseUrl = System.getProperty("QASE_URL", PropertyReader.getProperty("qase.url"));
        username = System.getProperty("USERNAME", PropertyReader.getProperty("qase.username"));
        password = System.getProperty("PASSWORD", PropertyReader.getProperty("qase.password"));
        Configuration.browser = browserType;
        Configuration.headless = headlessMode;
        Configuration.timeout = 5000;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false));
        Configuration.reportsFolder = "target/screenshots";
        open();
        faker = new Faker();
        loginPageSteps = new LoginPageSteps();
        projectsPageSteps = new ProjectsPageSteps();
        newProjectPageSteps = new NewProjectPageSteps();
        createdProjectPageSteps = new CreatedProjectPageSteps();
        getWebDriver().manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true, description = "closing browser")
    public void close() {
        if (getWebDriver() != null) {
            getWebDriver().quit();
        }
    }
}
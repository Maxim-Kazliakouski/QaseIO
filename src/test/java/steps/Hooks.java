package steps;

import adapters.ProjectAPI;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Listeners;
import tests.api.moduls.Project.Entity;
import tests.api.moduls.Project.Root;
import tests.base.TestListener;
import utils.PropertyReader;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static java.lang.String.format;

@Log4j2
@Listeners(TestListener.class)
public class Hooks {
    String username;
    String password;
    public static String caseID;
    public static String scenarioName;

    @Before
    public void init(Scenario scenario) {
        if (Boolean.parseBoolean(PropertyReader.getProperty("api"))) {
        } else {
//      getting test case ID
            Collection<String> caseIDs = scenario.getSourceTagNames();
            for (String eachCaseID : caseIDs) {
                caseID = eachCaseID.substring(12);
            }
            scenarioName = scenario.getName();
            WebDriverManager.chromedriver().setup();
            username = System.getProperty("USERNAME", PropertyReader.getProperty("qase.username"));
            password = System.getProperty("PASSWORD", PropertyReader.getProperty("qase.password"));
            Configuration.baseUrl = System.getProperty("QASE_URL", PropertyReader.getProperty("qase.url"));
            Configuration.browser = PropertyReader.getProperty("browser");
            Configuration.headless = Boolean.parseBoolean(PropertyReader.getProperty("headless"));
            Configuration.timeout = 10000;
            Configuration.reportsFolder = "target/screenshots";
            SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
            open();
            getWebDriver().manage().window().maximize();
        }
    }

    @After
    public void close() {
        if (Boolean.parseBoolean(PropertyReader.getProperty("api"))) {
        } else if (getWebDriver() != null) {
            getWebDriver().quit();
        }
    }

    @AfterAll
    public static void clearingProjects() {
        ProjectAPI projectAPI = new ProjectAPI();
        Root result = projectAPI.getAllProjects();
        List<Entity> entities = result.result.entities;
        List<String> code = entities.stream().map(Entity::getCode).collect(Collectors.toList());
        for (String eachCode : code) {
            if (!eachCode.equals("QASE") && !eachCode.equals("SHARELANE") && !eachCode.equals("PFT")) {
                projectAPI.deleteProjectByCode(eachCode);
                System.out.printf("Project '%s' has been deleted\n", eachCode);
                log.info(format("Project '%s' has been deleted\n", eachCode));
            }
        }
    }
}
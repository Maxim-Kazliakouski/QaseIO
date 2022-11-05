package tests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {
                "src/test/resources/features/login.feature",
//                "src/test/resources/features/actionsOnProject.feature",
//                "src/test/resources/features/createTestCase.feature",
//                "src/test/resources/features/createTestPlan.feature"
        },
        glue = "steps",
        plugin = {"pretty", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "html:target/cucumber.html",
                "json:target/cucumber.json"}
)
public class FeatureLauncherTest extends AbstractTestNGCucumberTests {
}
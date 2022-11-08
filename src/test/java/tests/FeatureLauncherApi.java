package tests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/test/resources/features/apiTests.feature"},
        glue = "steps",
        monochrome = true,
        plugin = {"pretty", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "html:target/cucumber.html",
                "json:target/cucumber.json",}
)
public class FeatureLauncherApi extends AbstractTestNGCucumberTests {
}
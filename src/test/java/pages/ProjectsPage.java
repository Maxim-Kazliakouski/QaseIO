package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;

@Log4j2
public class ProjectsPage {

    public void isOpened() {
        $(By.id("createButton")).shouldBe(Condition.visible);
    }

    public void redirectionToTheNewProjectPage() {
        log.info("Go to create new project page");
        open("/project/create");
    }

    @Step("Delete project {projectName}")
    public void deleteProjectByName(String projectName) {
        open("/projects");
        String projectCode = $x(format("//a[text()='%s']", projectName)).getAttribute("href").substring(28);
        open(format("/project/%s/delete", projectCode));
        $x("//button[contains(text(),'Delete project')]").click();
    }
}
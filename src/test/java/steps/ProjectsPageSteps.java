package steps;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import pages.NewProjectPage;
import pages.ProjectsPage;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

@Log4j2
public class ProjectsPageSteps {

    ProjectsPage projectsPage;
    NewProjectPage newProjectPage;

    public ProjectsPageSteps() {
        projectsPage = new ProjectsPage();
        newProjectPage = new NewProjectPage();
    }

    @Step("Go to the New Project Page")
    public void goToTheNewProjectPage() {
        projectsPage.redirectionToTheNewProjectPage();
        newProjectPage.isOpened();
    }

    @Step("Go to created project page")
    public CreatedProjectPageSteps goToCreatedProjectPage(String projectName) {
        $x(format("//a[text()='%s']", projectName)).click();
        return new CreatedProjectPageSteps();
    }

    @Step("Deleting project '{projectName}'")
    public void deleteProject(String projectName) {
        log.info(format("Deleting project '%s'", projectName));
        projectsPage.deleteProjectByName(projectName);
        $x(format("//a[text()='%s']", projectName)).shouldNotBe(Condition.visible);
    }
}
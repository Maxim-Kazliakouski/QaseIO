package steps;

import adapters.ProjectAPI;
import com.codeborne.selenide.Condition;
import dto.Project;
import dto.factories.ProjectFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j2;
import pages.ProjectsPage;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

@Log4j2
public class ProjectsPageSteps {

    ProjectsPage projectsPage;
    ProjectAPI projectAPI;

    public ProjectsPageSteps() {
        projectsPage = new ProjectsPage();
        projectAPI = new ProjectAPI();
    }

    @When("User create new project {string} with params: project code: {string}, description: {string}, project access type: {string}")
    public void createProject(String title, String code, String descriptions, String accessType) {
        Project newProject = ProjectFactory.getByAccessType(title, code, descriptions, accessType);
        projectsPage
                .creatingProject(newProject);
        System.out.printf("Project '%s' has been created%n", newProject.getProjectName());
    }

    @Then("New project {string} displays on the Projects page")
    public void isNewProjectCreated(String title) {
        projectsPage
                .isNewProjectCreated(title);
    }

    @Given("Create project {string} via API with params: project code: {string}, description: {string}, project access type: {string}")
    public void createdProjectViaApi(String title, String code, String desc, String accessType) {
        Project project = new Project(title, code, desc, accessType);
        projectAPI.createProject(project);
    }

    @When("User deletes {string}")
    public void userDeletesTestProject(String title) {
        projectsPage.deleteProjectByName(title);
    }

    @Then("Project {string} should disappear from Projects page")
    public void isProjectDeletedFromProjectsPage(String title) {
        projectsPage.isProjectDeleted(title);
    }

    @When("User change the old project title {string} on a new title {string}")
    public void userChangeTheProjectTitleOnAnotherTitle(String oldTitle, String changeTitle) {
        projectsPage.changeProject(oldTitle, "Project name", changeTitle);
    }

    @Then("Confirmation message {string} is appeared")
    public void isToastAppeared(String confirmationMessage) {
        $x(format("//span[text()='%s']", confirmationMessage)).shouldBe(Condition.visible);
    }

    @Then("Project should change the name on {string}")
    public void isProjectNameChanged(String title) {
        $x(format("//div[text()='%s']", title)).shouldBe(Condition.visible);
    }
}
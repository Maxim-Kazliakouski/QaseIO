package steps;

import adapters.ProjectAPI;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import dto.Project;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j2;
import pages.CreatedProjectPage;
import pages.ProjectsPage;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

@Log4j2
public class CreatedProjectPageSteps {
    ProjectAPI projectAPI;
    CreatedProjectPage createdProjectPage;
    ProjectsPage projectsPage;

    public CreatedProjectPageSteps() {
        createdProjectPage = new CreatedProjectPage();
        projectsPage = new ProjectsPage();
        projectAPI = new ProjectAPI();
    }

    @Given("Project {string} via API with params: project code: {string}, description: {string}, project access type: {string}")
    public void createdProjectViaApi(String title, String code, String desc, String accessType) {
        Project project = new Project(title, code, desc, accessType);
        projectAPI.createProject(project);
    }

    @When("User goes to created project {string}")
    public void goToCreatedProject(String titleProject) {
        Selenide.refresh();
        createdProjectPage
                .goToCreatedProjectPage(titleProject);
    }

    @And("Delete project by code {string}")
    public void deleteProjectProjectForCases(String code) {
        projectAPI
                .deleteProjectByCode(code);
    }

    @When("User change test case name on {string} in project {string}")
    public void userChangeTestCase(String newTestCaseName, String projectCode) {
        createdProjectPage
                .changeTestCase(projectCode, "Title", newTestCaseName);
    }

    @Then("Test case has a new title {string}")
    public void testCaseHasANewTitleNewTestCase(String newTitle) {
        $x(format("//div[text()='%s']", newTitle)).shouldBe(Condition.visible);
    }
}
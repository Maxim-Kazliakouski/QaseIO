package steps;

import adapters.ProjectAPI;
import com.codeborne.selenide.Selenide;
import dto.TestCase;
import dto.TestPlan;
import dto.factories.TestCaseFactory;
import dto.factories.TestPlanFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CreateTestPlanPage;
import pages.CreatedProjectPage;
import pages.ProjectsPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateTestPlanSteps {
    ProjectsPage projectsPage;
    CreatedProjectPage createdProjectPage;
    ProjectAPI projectAPI;
    CreateTestPlanPage createTestPlanPage;

    public CreateTestPlanSteps() {
        projectsPage = new ProjectsPage();
        createdProjectPage = new CreatedProjectPage();
        createTestPlanPage = new CreateTestPlanPage();
        projectAPI = new ProjectAPI();
    }

    @Given("Create test case {string} via api in project with code {string}")
    public void createTestCaseViaApi(String title, String codeProject) {
        TestCase testCase = TestCaseFactory.getTestCase("API", title);
        projectAPI
                .createNewTestCase(testCase, codeProject);
    }

    @When("User create new test plan {string} in project {string}")
    public void userCreateNewTestPlanNewTestPlan(String title, String titleProject) {
        Selenide.refresh();
        TestPlan testPlan = TestPlanFactory.getTestPlan(title, "new test plan description");
        createdProjectPage
                .goToCreatedProjectPage(titleProject)
                .goToTestPlanPage();
        createTestPlanPage
                .createTestPlan(testPlan);
    }

    @Then("Test plan {string} is displayed in Test Plans section")
    public void isTestPlanDisplayedInTestPlansSection(String testPlanName) {
        createTestPlanPage
                .isTestPlanDisplayedOnTestPlanPage(testPlanName);
    }

    @Given("Create test plan {string} with cases id: {string}, project code {string} via api")
    public void createTestPlanViaApi(String testPlanName, String ids, String code) {
        List<String> idsToArray = new ArrayList<>(Arrays.asList(ids.split(",")));
        List<Integer> casesID = new ArrayList<>();
        System.out.println(casesID);
        for (String fav : idsToArray) {
            casesID.add(Integer.parseInt(fav.trim()));
        }
        projectAPI
                .createTestPlan(testPlanName, casesID, code);
    }

    @When("User change the test plan name on {string}, project code is {string}")
    public void userChangeTheTestPlanName(String newName, String projectCode) {
        Selenide.refresh();
        createTestPlanPage
                .changeTestPlanName(newName, projectCode);
    }

    @Then("New test plan name {string} is displayed on Test plan page")
    public void isNewTestPlanNamesDisplayed(String newTestPlanName) {
        createTestPlanPage
                .isNewTestPlanNameDisplayedOnTestPlanPage(newTestPlanName);
    }
}
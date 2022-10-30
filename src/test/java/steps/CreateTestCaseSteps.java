package steps;

import adapters.ProjectAPI;
import com.codeborne.selenide.Selenide;
import dto.TestCase;
import dto.factories.TestCaseFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import pages.CreatedProjectPage;
import pages.ProjectsPage;

public class CreateTestCaseSteps {
    ProjectAPI projectAPI;
    CreatedProjectPage createdProjectPage;
    ProjectsPage projectsPage;

    public CreateTestCaseSteps() {
        createdProjectPage = new CreatedProjectPage();
        projectsPage = new ProjectsPage();
        projectAPI = new ProjectAPI();
    }

    @When("User create new test case with title {string}")
    public void createTestCase(String title) {
        Selenide.refresh();
        TestCase testCase = TestCaseFactory.getTestCase("UI", title);
        createdProjectPage
                .createNewCase(testCase);
    }

    @And("New test case {string} is created")
    public void newTestCaseNewTestCaseIsCreated(String title) {
        createdProjectPage
                .isNewTestCaseCreated(title);
    }

    @When("User delete test case {string}")
    public void userDeleteTestCaseTestCase(String titleTestCase) {
        createdProjectPage
                .deleteTestCase(titleTestCase);
    }

    @And("Test case {string} is deleted")
    public void testCaseTestCaseIsDeleted(String titleTestCase) {
        createdProjectPage
                .isTestCaseDeleted(titleTestCase);
    }
}
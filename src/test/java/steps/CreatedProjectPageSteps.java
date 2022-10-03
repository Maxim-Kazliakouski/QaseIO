package steps;

import dto.NewTestCase;
import factories.NewTestCaseFactory;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import pages.CreatedProjectPage;
import pages.ProjectsPage;

@Log4j2
public class CreatedProjectPageSteps {

    CreatedProjectPage createdProjectPage;
    ProjectsPage projectsPage;

    public CreatedProjectPageSteps() {
        createdProjectPage = new CreatedProjectPage();
        projectsPage = new ProjectsPage();
    }

    @Step("created new test case")
    public void createCase() {
        NewTestCase newTestCase = NewTestCaseFactory.getTestCase();
        log.info("Creating new test case...");
        createdProjectPage
                .createNewCase(newTestCase);
        log.info("Checking confirmation message about new created test case");
        createdProjectPage
                .isNewCreatedTestCaseMessageAppeared();
        createdProjectPage.isNewTestCaseCreated(newTestCase.getTitle());
    }

    @Step("delete new created test case '{testCaseName}'")
    public void deleteCase(String testCaseName) {
        log.info("Deleting test case...");
        createdProjectPage
                .deleteTestCase(testCaseName);
        log.info("Checking confirmation message about deleting test case");
        createdProjectPage
                .isDeletingTestCaseMessageAppeared();
    }
}
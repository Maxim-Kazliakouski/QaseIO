package tests;

import org.testng.annotations.Test;
import tests.base.BaseTest;

public class NewProjectPageTest extends BaseTest {

    @Test(description = "Creating new project")
    public void createNewProject() {
        String projectName = faker.funnyName().name();
        loginPageSteps
                .goToLoginPage()
                .tryToLogin(getUsername(), getPassword());
        projectsPageSteps.goToTheNewProjectPage();
        newProjectPageSteps.createNewProject(projectName);
        //postCondition
        projectsPageSteps.deleteProject(projectName);
    }
}
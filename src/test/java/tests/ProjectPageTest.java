package tests;

import org.testng.annotations.Test;
import tests.base.BaseTest;

public class ProjectPageTest extends BaseTest {

    @Test(description = "Deleting project")
    public void deleteProject() {
        String projectName = faker.funnyName().name();
        loginPageSteps
                .goToLoginPage()
                .tryToLogin(getUsername(), getPassword());
        projectsPageSteps
                .goToTheNewProjectPage();
        newProjectPageSteps
                .createNewProject(projectName);
        projectsPageSteps
                .deleteProject(projectName);
    }
}
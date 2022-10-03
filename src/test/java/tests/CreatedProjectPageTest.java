package tests;

import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CreatedProjectPageTest extends BaseTest {

    @Test
    public void createNewTestCase() {
        String testCaseName = "New test case";
        loginPageSteps
                .goToLoginPage()
                .tryToLogin(getUsername(), getPassword());
        projectsPageSteps
                .goToCreatedProjectPage("ProjectForTest")
                .createCase();
        createdProjectPageSteps.deleteCase(testCaseName);
    }
}
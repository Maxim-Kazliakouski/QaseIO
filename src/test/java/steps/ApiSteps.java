package steps;

import adapters.ProjectAPI;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.Project;
import dto.TestCase;
import dto.TestRun;
import dto.factories.ProjectFactory;
import dto.factories.TestCaseFactory;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.hamcrest.Matchers;
import org.testng.Assert;
import tests.api.moduls.APIResponse;
import tests.api.moduls.Project.Entity;

import java.util.*;

import static org.testng.Assert.*;

@Log4j2
@Data
public class ApiSteps {
    Faker faker;
    Gson gson;
    ProjectAPI projectAPI;
    String projectName;
    String projectCode;
    String description;
    static String projectCodeForTest;
    static String codeProject;
    static Integer caseID;
    static Integer testRunID;
    static Integer testCaseID;
    static JsonPath jsonPath;
    static Response response;
    static ValidatableResponse valResp;
    static Project project;

    public ApiSteps() {
        gson = new GsonBuilder().setPrettyPrinting().create();
        faker = new Faker();
        projectAPI = new ProjectAPI();
        this.projectName = faker.funnyName().name();
        this.projectCode = faker.code().asin();
    }

    @When("making request for getting projects list")
    public void getProjectList() {
        response = projectAPI.getAllProjects();
    }

    @Then("getting project list")
    public void gettingProjectList() {
        APIResponse<Project> result = gson.fromJson(response.asString(),
                new TypeToken<APIResponse<Project>>() {
                }.getType());
        List<Entity> entities = result.getResult().entities;
        entities.stream().map(Entity::getTitle).forEach(Assert::assertNotNull);
    }

    @When("making request for creating new project")
    public void createNewProject() {
        project = ProjectFactory.getByAccessType(projectName, projectCode, description, "private");
        response = projectAPI.createProject(project);
        projectCodeForTest = response.path("result.code").toString();
    }

    @Then("new project is created")
    public void newProjectIsCreated() {
        assertEquals(response.jsonPath().get("result.code").toString(), project.getProjectCode());
    }

    @When("deleting project by projectCode")
    public void deleteProject() {
        valResp = projectAPI.deleteProjectByCode(projectCodeForTest);
    }

    @Then("project is deleted")
    public void projectIsDeleted() {
        valResp
                .body("status", Matchers.is(true));
    }

    @When("making request for getting certain test case")
    public void getTestCase() {
        // test data
        codeProject = "PFT";
        testCaseID = 1;
        response = projectAPI.getCertainTestCase(codeProject, testCaseID);
    }

    @Then("certain test case is received")
    public void certainTestCaseIsReceived() {
        String testCaseTitle = "Authorization";
        APIResponse<TestCase> result = gson.fromJson(response.asString(),
                new TypeToken<APIResponse<TestCase>>() {
                }.getType());
        System.out.println(result.getStatus());
        assertTrue(result.getStatus(), "'false' status has been received");
        assertEquals(result.getResult().getId(), testCaseID);
        assertEquals(result.getResult().getTitle(), testCaseTitle);
    }

    @When("making request for creating new test case")
    public void createTestCase() {
        String codeProject = "PFT";
        TestCase testCase = TestCaseFactory.getTestCase("API", "Login");
        response = projectAPI.createNewTestCase(testCase, codeProject)
                .body("status", Matchers.is(true))
                .body("result.id", Matchers.notNullValue())
                .extract().response();
        caseID = response.path("result.id");
    }

    @Then("new test case is created")
    public void newTestCaseIsCreated() {
        assertTrue(response.path("status"), "Test case hasn't been created!");
    }

    @When("making request for deleting test case")
    public void deleteTestCase() {
        codeProject = "PFT";
        valResp = projectAPI.deleteTestCase(codeProject, caseID);
    }

    @Then("test case is deleted")
    public void testCaseIsDeleted() {
        valResp
                .body("status", Matchers.is(true));
    }

    @When("making request for getting list of test cases")
    public void getTestCases() {
        codeProject = "PFT";
        response = projectAPI.getAllTestCases(codeProject);
        jsonPath = response.jsonPath();
    }

    @Then("getting test cases list")
    public void gettingTestCasesList() {
        List<Integer> ids = jsonPath.get("result.entities.id");
        List<String> titles = jsonPath.get("result.entities.title");
        Map<Integer, String> dataCase = new HashMap<>();
        for (
                int i = 0; i < ids.size(); i++) {
            dataCase.put(ids.get(i), titles.get(i));
        }
        for (
                Map.Entry entry : dataCase.entrySet()) {
            assertNotNull(entry.getKey());
            assertNotNull(entry.getValue());
        }
    }

    @When("making request for creating new test run")
    public void createTestRun() {
        response = projectAPI.createTestRun("PFT", "testRun123");
    }

    @Then("new test run is created")
    public void newTestRunIsCreated() {
        assertTrue(response.path("status"), "Status false");
        testRunID = response.path("result.id");
    }

    @When("making request for getting all tests run of the project")
    public void getRuns() {
        response = projectAPI.getAllRuns("PFT");
    }

    @Then("getting list of all tests runs")
    public void gettingListOfAllTestsRuns() {
        APIResponse<TestRun> result = gson.fromJson(response.asString(),
                new TypeToken<APIResponse<TestRun>>() {
                }.getType());
        assertTrue(result.getStatus(), "Fail status");
    }

    @When("making request for deleting test run")
    public void deleteTestRun() {
        valResp = projectAPI.deleteTestRun("PFT", testRunID);
    }

    @Then("test run is deleted")
    public void testRunIsDeleted() {
        valResp
                .statusCode(200)
                .body("status", Matchers.is(true));
    }

    @When("making request for creating test plan")
    public void creatingTestPlan() {
        List<Integer> casesIDs = new ArrayList<>();
        casesIDs.add(2);
        response = projectAPI.createTestPlan("123 test plan", casesIDs, "PFT");
    }

    @Then("test plan is created")
    public void testPlanIsCreated() {
        assertTrue(response.path("status"));
        assertNotNull(response.path("result.id"));
    }
}
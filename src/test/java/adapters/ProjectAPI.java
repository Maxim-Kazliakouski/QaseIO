package adapters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.Project;
import dto.TestCase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import lombok.Data;
import tests.api.Specifications;
import tests.api.moduls.TestCase.Root;
import utils.PropertyReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;

@Data
public class ProjectAPI {
    Gson gson;
    Specifications spec;
    public static final String URL_API = PropertyReader.getProperty("qase.url.api");


    public ProjectAPI() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public tests.api.moduls.Project.Root getAllProjects() {
        Specifications.installSpecification(
                Specifications.requestSpec(URL_API),
                Specifications.responseSpecStatusCode(200)
        );
        return RestAssured
                .given()
                .when()
                .get("/v1/project")
                .then()
                .statusCode(200)
                .extract().as(tests.api.moduls.Project.Root.class);
    }

    public Response createProject(Project project) {
        Specifications.installSpecification(
                Specifications.requestSpec(URL_API),
                Specifications.responseSpecStatusCode(200));
        String data = gson.toJson(project);
        return
                given()
                        .body(data)
                        .when()
                        .post("/v1/project")
                        .then().log().all()
                        .statusCode(200)
                        .extract().response();
    }

    public ValidatableResponse deleteProjectByCode(String code) {
        Specifications.installSpecification(
                Specifications.requestSpec(URL_API),
                Specifications.responseSpecStatusCode(200));
        return
                given()
                        .when()
                        .delete("/v1/project/" + code)
                        .then().log().all();
    }

    public Root getCertainTestCase(String codeProject, Integer id) {
        Specifications.installSpecification(
                Specifications.requestSpec(URL_API),
                Specifications.responseSpecStatusCode(200));
        return RestAssured
                .given()
                .when()
                .get(format("/v1/case/%s/%s", codeProject, id))
                .then().log().all()
                .extract().as(Root.class);
    }

    public ValidatableResponse createNewTestCase(TestCase testCase, String codeProject) {
        Specifications.installSpecification(
                Specifications.requestSpec(URL_API),
                Specifications.responseSpecStatusCode(200));
        String data = gson.toJson(testCase);
        System.out.println(data);
        return
                given()
                        .body(data)
                        .when()
                        .post("/v1/case/" + codeProject)
                        .then().log().all();
    }

    public ValidatableResponse deleteTestCase(String codeProject, Integer testCaseID) {
        Specifications.installSpecification(
                Specifications.requestSpec(URL_API),
                Specifications.responseSpecStatusCode(200));
        return
                given()
                        .when()
                        .delete(format("/v1/case/%s/%s", codeProject, testCaseID))
                        .then().log().all();
    }

    public Response getAllTestCases(String codeProject) {
        Specifications.installSpecification(
                Specifications.requestSpec(URL_API),
                Specifications.responseSpecStatusCode(200));
        return RestAssured.given()
                .when()
                .get("/v1/case/" + codeProject)
                .then().log().all()
                .extract().response();
    }

    public void setStatus(String status,
                          String codeProject,
                          Integer idTestRun,
                          String idTestCase,
                          Long time) {
        Specifications.installSpecification(
                Specifications.requestSpec(URL_API),
                Specifications.responseSpecStatusCode(200));
        Map<String, Object> data = new HashMap<>();
        data.put("case_id", idTestCase);
        data.put("status", status);
        data.put("time", time);
        given()
                .body(data)
                .when()
                .post(format("v1/result/%s/%s", codeProject, idTestRun))
                .then();
    }

    public Response createTestRun(String codeProject, String testRunName) {
        Specifications.installSpecification(
                Specifications.requestSpec(URL_API),
                Specifications.responseSpecStatusCode(200));
        Map<String, Object> data = new HashMap<>();
        data.put("title", testRunName);
        data.put("include_all_cases", true);
        return given()
                .body(data)
                .when()
                .post("/v1/run/" + codeProject)
                .then()
                .extract().response();
    }

    public ValidatableResponse deleteTestRun(String codeProject, Integer idTestRun) {
        Specifications.installSpecification(
                Specifications.requestSpec(URL_API),
                Specifications.responseSpecStatusCode(200));
        return
                given()
                        .when()
                        .delete(format("/v1/run/%s/%s", codeProject, idTestRun))
                        .then();
    }

    public tests.api.moduls.TestRun.Root getAllRuns(String projectCode) {
        Specifications.installSpecification(
                Specifications.requestSpec(URL_API),
                Specifications.responseSpecStatusCode(200));
        return RestAssured
                .when()
                .get("/v1/run/" + projectCode)
                .then()
                .extract().as(tests.api.moduls.TestRun.Root.class);
    }

    public Response createTestPlan(String testPlanName, List<Integer> casesID, String projectCode) {
        Specifications.installSpecification(
                Specifications.requestSpec(URL_API),
                Specifications.responseSpecStatusCode(200));
        Map<String, Object> data = new HashMap<>();
        data.put("title", testPlanName);
        data.put("cases", casesID);
        return RestAssured
                .given()
                .body(data)
                .when()
                .post("/v1/plan/" + projectCode)
                .then().log().all()
                .extract().response();
    }
}
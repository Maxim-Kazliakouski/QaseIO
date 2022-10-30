package utils;

import adapters.ProjectAPI;
import lombok.extern.log4j.Log4j2;
import tests.api.moduls.TestRun.Root;
@Log4j2
public class TestRunsChecker {
    public void isTestRunCreated(){
        boolean isTestRun = Boolean.parseBoolean(PropertyReader.getProperty("testRun"));
        if (isTestRun) {
            ProjectAPI projectAPI = new ProjectAPI();
            Root response = projectAPI.getAllRuns(PropertyReader.getProperty("codeProject"));
            int count = response.result.count;
            if (count != 0) {
                int testRunID = response.result.entities.get(0).id;
                projectAPI.deleteTestRun(PropertyReader.getProperty("codeProject"), testRunID);
                System.out.println("Test run has been deleted!");
            } else {
                System.out.println("There is no created test runs");
                log.info("Test run hasn't been created yet!");
            }
        }
        else {
            System.out.println("There is no reason to create new test run");
        }
    }
}
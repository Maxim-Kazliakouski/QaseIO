package pages;

import com.codeborne.selenide.Condition;
import dto.Project;
import dto.UpdateProject;
import dto.factories.UpdateProjectFactory;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import wrappers.Input;
import wrappers.RadioButton;
import wrappers.Textarea;

import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;

@Log4j2
public class ProjectsPage {

    public void isOpened() {
        $(By.id("createButton")).shouldBe(Condition.visible);
    }

    @Step("Delete project {projectName}")
    public void deleteProjectByName(String projectName) {
        open("/projects");
        String projectCode = $x(format("//a[text()='%s']", projectName)).getAttribute("href").substring(28);
        open(format("/project/%s/delete", projectCode));
        $x("//button[contains(text(),'Delete project')]").click();
    }

    public void changeProject(String projectName, String whatChange, String changeValue) {
        UpdateProject updateProject = UpdateProjectFactory.getUpdateProject(whatChange, changeValue);
        open("/projects");
        String projectCode = $x(format("//a[text()=\"%s\"]", projectName)).getAttribute("href").substring(28);
        open(format("/project/%s/settings/general", projectCode));
        if (whatChange.equals("Project name")) {
            new Input("Project name").clear();
            new Input("Project name").input(changeValue);
            $(By.id("update")).click();
        } else if (whatChange.equals("Project code")) {
            new Input("Project Code").clear();
            new Input("Project Code").input(changeValue);
            $(By.id("update")).click();
        } else if (whatChange.equals("Description")) {
            new Textarea("Description").clear();
            new Textarea("Description").input(changeValue);
            $(By.id("update")).click();
        } else if (whatChange.equals("Project access type")) {
            new RadioButton("Project access type").choose(changeValue);
            $(By.id("update")).click();
        } else {
            throw new Error(format("Incorrect value 'whatChange'= %s", whatChange));
        }
    }

    @Step("Creating new project")
    public ProjectsPage creatingProject(Project newProject) {
        isOpened();
        $(By.id("createButton")).click();
        new Input("Project name").input(newProject.getProjectName());
        new Input("Project code").clear();
        new Input("Project code").input(newProject.getProjectCode());
        new Textarea("Description").input(newProject.getDescription());
        new RadioButton("Project access type").choose(newProject.getProjectAccessType());
        if (newProject.getProjectAccessType().equals("private")) {
            new RadioButton("Members access").choose("Add all members to this project");
        } else {
            System.out.println("Has been chosen public access type");
        }
        $x("//span[text()='Create project']//ancestor::button").click();
        return this;
    }

    @Step("Checking that new project '{projectName}' has been created")
    public void isNewProjectCreated(String projectName) {
        $x(format("//div[text()='%s']", projectName)).shouldBe(Condition.visible);
    }

    public void isProjectDeleted(String projectName) {
        $x(format("//div[text()='%s']", projectName)).shouldNotBe(Condition.visible);
    }
}
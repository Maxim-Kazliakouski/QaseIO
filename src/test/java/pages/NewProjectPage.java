package pages;

import com.codeborne.selenide.Condition;
import dto.NewProject;
import io.qameta.allure.Step;
import lombok.Data;
import tests.wrappers.Input;
import tests.wrappers.RadioButton;
import tests.wrappers.Textarea;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

@Data
public class NewProjectPage {

    public void isOpened() {
        $x("//h1[text()='New Project']").shouldBe(Condition.visible);
    }

    @Step("Creating new project")
    public NewProjectPage creatingProject(NewProject newProject) {
        new Input("Project name").input(newProject.getProjectName());
        new Input("Project Code").input(newProject.getProjectCode());
        new Textarea("Description").input(newProject.getDescription());
        new RadioButton("Project access type").choose(newProject.getProjectAccessType());
        if (newProject.getProjectAccessType().equals("Private")) {
            new RadioButton("Members access").choose("Add all members to this project");
        } else {
            System.out.println("Has been chosen public access type");
        }
        $x("//button[text()='Create project']").click();
        return this;
    }

    @Step("Checking that new project '{projectName}' has been created")
    public void isNewProjectCreated(String projectName) {
        $x(format("//div[text()='%s']", projectName)).shouldBe(Condition.visible);
    }
}
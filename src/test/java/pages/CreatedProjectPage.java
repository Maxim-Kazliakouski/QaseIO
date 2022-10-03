package pages;

import com.codeborne.selenide.Condition;
import dto.NewTestCase;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import tests.wrappers.Dropdown;
import tests.wrappers.Input;

import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;

public class CreatedProjectPage {

    @Step("Creating new test case")
    public CreatedProjectPage createNewCase(NewTestCase newTestCase) {
        $(By.id("create-case-button")).click();
        $x("//div[text()='Basic']").shouldBe(Condition.visible);
        new Input("Title").input(newTestCase.getTitle());
        new Dropdown("Status").choose(newTestCase.getStatus());
        new Input("Description").input(newTestCase.getDescription());
        new Dropdown("Suite").choose(newTestCase.getSuite());
        new Dropdown("Severity").choose(newTestCase.getSeverity());
        new Dropdown("Priority").choose(newTestCase.getPriority());
        new Dropdown("Type").choose(newTestCase.getType());
        new Dropdown("Layer").choose(newTestCase.getLayer());
        new Dropdown("Is flaky").choose(newTestCase.getIsFlaky());
        new Dropdown("Behavior").choose(newTestCase.getBehaviour());
        new Dropdown("Automation status").choose(newTestCase.getAutomationStatus());
        new Input("Pre-conditions").input(newTestCase.getPreConditions());
        new Input("Post-conditions").input(newTestCase.getPostConditions());
        $(By.id("save-case")).click();
        return this;
    }

    @Step("Checking that new test case '{projectName}' has been created")
    public void isNewTestCaseCreated(String testCaseName) {
        $x(format("//div[text()='%s']", testCaseName)).shouldBe(Condition.visible);
    }

    public void isNewCreatedTestCaseMessageAppeared() {
        $x("//span[text()='Test case was created successfully!']").shouldBe(Condition.visible);
    }

    public void isDeletingTestCaseMessageAppeared() {
        $x("//span[contains(text(),'test case was successfully deleted')]").shouldBe(Condition.visible);
    }

    @Step("Deleting created test case '{testCaseName}'")
    public CreatedProjectPage deleteTestCase(String testCaseName) {
        $x(format("//div[text()='%s']//..//input[@type='checkbox']", testCaseName)).click();
        $x("//button[text()=' Delete']").click();
        $x("//input[@name='confirm']").setValue("CONFIRM");
        $x("//span[text()='Delete']").click();
        return this;
    }
}
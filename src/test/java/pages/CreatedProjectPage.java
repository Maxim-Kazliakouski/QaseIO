package pages;

import com.codeborne.selenide.Condition;
import dto.TestCase;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import wrappers.Dropdown;
import wrappers.Input;

import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;

public class CreatedProjectPage {

    @Step("Creating new test case")
    public CreatedProjectPage createNewCase(TestCase<String> testCase) {
        $(By.id("create-case-button")).click();
        $x("//div[text()='Basic']").shouldBe(Condition.visible);
        new Input("Title").input(testCase.getTitle());
        new Dropdown("Status").choose(testCase.getStatus());
        $x("//p").sendKeys(testCase.getDescription());
        new Dropdown("Suite").choose(testCase.getSuite());
        new Dropdown("Severity").choose(testCase.getSeverity());
        new Dropdown("Priority").choose(testCase.getPriority());
        new Dropdown("Type").choose(testCase.getType());
        new Dropdown("Layer").choose(testCase.getLayer());
        new Dropdown("Is flaky").choose(testCase.getIsFlaky());
        new Dropdown("Behavior").choose(testCase.getBehaviour());
        new Dropdown("Automation status").choose(testCase.getAutomationStatus());
        $x("//label[contains(text(), 'Pre-conditions')]//..//../input").sendKeys(testCase.getPreConditions());
        $x("//label[contains(text(), 'Post-conditions')]//..//../input").sendKeys(testCase.getPostConditions());
        $(By.id("save-case")).click();
        return this;
    }

    @Step("Checking that new test case '{projectName}' has been created")
    public CreatedProjectPage isNewTestCaseCreated(String testCaseName) {
        $x(format("//div[text()='%s']", testCaseName)).shouldBe(Condition.visible);
        return this;
    }

    @Step("Deleting created test case '{testCaseName}'")
    public CreatedProjectPage deleteTestCase(String testCaseName) {
        $x(format("//div[text()='%s']//..//input[@type='checkbox']", testCaseName)).click();
        $x("//button[text()=' Delete']").click();
        $x("//input[@name='confirm']").setValue("CONFIRM");
        $x("//span[text()='Delete']").click();
        return this;
    }

    @Step("Go to created project page")
    public CreatedProjectPage goToCreatedProjectPage(String projectName) {
        $x(format("//a[text()='%s']", projectName)).click();
        return this;
    }

    @Step("Go to test plan page")
    public CreateTestPlanPage goToTestPlanPage() {
        $x("//span[text()='Test Plans']").click();
        return new CreateTestPlanPage();
    }

    public void isTestCaseDeleted(String testCaseName) {
        $x(format("//div[contains(text(),'%s')]", testCaseName)).shouldNotBe(Condition.visible);
    }

    public void changeTestCase(String projectCode, String whatChange, String changeValue) {
        open(format("https://app.qase.io/case/%s/edit/1", projectCode));
        switch (whatChange) {
            case "Title":
                new Input("Title").clear();
                new Input("Title").input(changeValue);
                break;
            case "Status":
                new Dropdown("Status").choose(changeValue);
                break;
            case "Description":
                $x("//label[text()='Description']//..//div[@contenteditable]/p").clear();
                $x("//label[text()='Description']//..//div[@contenteditable]/p").sendKeys(changeValue);
                break;
            case "Suite":
                new Dropdown("Suite").choose(changeValue);
                break;
            case "Severity":
                new Dropdown("Severity").choose(changeValue);
                break;
            case "Priority":
                new Dropdown("Priority").choose(changeValue);
                break;
            case "Type":
                new Dropdown("Type").choose(changeValue);
                break;
            case "Layer":
                new Dropdown("Layer").choose(changeValue);
                break;
            case "Is flaky":
                new Dropdown("Is flaky").choose(changeValue);
                break;
            case "Behaviour":
                new Dropdown("Behavior").choose(changeValue);
                break;
            case "Automation status":
                new Dropdown("Automation status").choose(changeValue);
                break;
            case "Pre-conditions":
                $x("//label[contains(text(), 'Pre-conditions')]//..//../input").sendKeys(changeValue);
                break;
            case "Post-conditions":
                $x("//label[contains(text(), 'Post-conditions')]//..//../input").sendKeys(changeValue);
                break;
        }
        $(By.id("save-case")).click();
    }
}
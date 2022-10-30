package pages;

import com.codeborne.selenide.Condition;
import dto.TestPlan;
import org.openqa.selenium.By;
import wrappers.CheckBox;
import wrappers.Input;

import static com.codeborne.selenide.Selenide.*;

public class CreateTestPlanPage {

    public void createTestPlan(TestPlan testPlan) {
        $(By.id("createButton")).click();
        new Input("Title").input(testPlan.getTitle());
        $x("//label[text()='Description']//..//div[@contenteditable]/p").sendKeys(testPlan.getDescription());
        $(By.id("edit-plan-add-cases-button")).click();
        new CheckBox("Test cases without suite").chooseCheckbox();
        $(By.id("select-cases-done-button")).click();
        $(By.id("save-plan")).click();
    }

    public void isTestPlanDisplayedOnTestPlanPage(String testPlanName) {
        $x("//a[@class='defect-title']").shouldBe(Condition.visible);
        $x("//a[@class='defect-title']").shouldHave(Condition.text(testPlanName));
    }

    public void changeTestPlanName(String newTestPlanName, String projectCode) {
        open("https://app.qase.io/plan/" + projectCode);
        $x("//a[@class='btn btn-dropdown']").click();
        $x("//a[text()='Edit']").click();
        new Input("Title").input(newTestPlanName);
        sleep(5000);
        $(By.id("save-plan")).click();
    }

    public void isNewTestPlanNameDisplayedOnTestPlanPage(String newTestPlanName) {
        $x("//a[@class='defect-title']").shouldBe(Condition.visible);
        $x("//a[@class='defect-title']").shouldHave(Condition.text(newTestPlanName));
    }
}
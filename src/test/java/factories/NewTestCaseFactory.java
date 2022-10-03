package factories;

import dto.NewTestCase;

public class NewTestCaseFactory {
    public static NewTestCase getTestCase() {
        return NewTestCase.builder()
                .title("New test case")
                .status("Draft")
                .description("new test case description")
                .suite("Test cases without suite")
                .severity("Critical")
                .priority("High")
                .type("Functional")
                .layer("API")
                .isFlaky("No")
                .behaviour("Positive")
                .automationStatus("Automated")
                .preConditions("start browser")
                .postConditions("close browser")
                .build();
    }
}
package tests.wrappers;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class Dropdown {
    String label;

    public Dropdown(String label) {
        this.label = label;
    }

    public void choose(String option) {
        $x(format("//label[text()='%s']//..//button", label)).click();
        $x(format("//div[text()='%s']", option)).click();
    }
}

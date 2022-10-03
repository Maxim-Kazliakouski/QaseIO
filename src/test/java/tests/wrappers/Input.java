package tests.wrappers;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class Input {
    String label;

    public Input(String label) {
        this.label = label;
    }

    public void input(String text) {
        $x(format("//label[contains(text(), '%s')]//..//input", label)).sendKeys(text);
    }
}
package tests.wrappers;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class RadioButton {
    String label;

    public RadioButton(String label) {
        this.label = label;
    }

    public void choose(String radioButton) {
        $x(format("//label[contains(text(), '%s')]//following::div/label[contains(text(),'%s')]", label, radioButton)).click();
    }
}
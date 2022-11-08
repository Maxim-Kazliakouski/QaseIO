package wrappers;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class CheckBox {
    String checkBoxName;

    public CheckBox(String checkBoxName) {
        this.checkBoxName = checkBoxName;
    }

    public void chooseCheckbox(){
        $x(format("//p[text()='%s']//..//label", checkBoxName)).click();
    }
}

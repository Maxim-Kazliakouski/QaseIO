package wrappers;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class Textarea {
    String label;

    public Textarea(String label) {
        this.label = label;
    }

    public void input(String text) {
/*      $x(format("//label[contains(text(), '%s')]//..//input | //label[contains(text(), '%s')]//..//textarea", label, label)).clear();
        the string above for cleaning Project code field, because Project field
        fills automatically by first letters of Project name (use if necessary enter your own project code)
*/
        $x(format("//label[contains(text(), '%s')]//following::textarea", label)).sendKeys(text);
    }

    public void clear() {
        $x(format("//label[contains(text(), '%s')]//following::textarea", label)).clear();
    }
}
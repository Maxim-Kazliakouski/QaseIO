package wrappers;

import org.openqa.selenium.JavascriptExecutor;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static java.lang.String.format;

public class Input {
    String label;

    public Input(String label) {
        this.label = label;
    }

    public void input(String text) {
        JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
        js.executeScript("arguments[0].value = '';", $x(format("//label[contains(text(), '%s')]//..//..//input", label)));
//        $x(format("//label[contains(text(), '%s')]//..//..//input", label)).sendKeys(text);
        $x(format("//label[contains(text(), '%s')]//..//..//input", label)).sendKeys(text);

    }

    public void clear() {
        $x(format("//label[contains(text(), '%s')]//..//..//input", label)).clear();
    }
}
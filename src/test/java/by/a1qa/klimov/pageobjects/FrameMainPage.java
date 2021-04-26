package by.a1qa.klimov.pageobjects;

import by.a1qa.klimov.framework.browser.BrowserActions;
import by.a1qa.klimov.framework.elements.Button;
import by.a1qa.klimov.framework.elements.Label;
import by.a1qa.klimov.framework.elements.TextField;
import by.a1qa.klimov.framework.models.BaseForm;
import org.openqa.selenium.By;

public class FrameMainPage extends BaseForm {
    private final static String XPATH_IFRAME_TEXT = "//body[@id='tinymce']//p";
    private final static String XPATH_IFRAME_BOLD_TEXT = "//body[@id='tinymce']//strong";
    private final static String XPATH_BOLD_BUTTON = "//button[@aria-label='Bold']";

    private Button boldButton = new Button(By.xpath(XPATH_BOLD_BUTTON), "Bold button.");

    public FrameMainPage(By locator, String name) {
        super(new Label(locator, name));
    }

    public void frameSetText(String text) {
        getFrameTextField().sendText(text);
    }

    public void frameClearText() {

        getFrameTextField().clear();
    }

    public String frameGetText() {
        return getFrameTextField().getText();
    }

    public void highlightFrameText() {
        getFrameTextField().highlightText();
    }

    public void boldButtonClick() {
        boldButton.click();
    }

    public String getBoldFrameText() {
        return getFrameBoldTextField().getText();
    }

    private TextField getFrameTextField() {
        return new TextField(By.xpath(XPATH_IFRAME_TEXT), "Frame text field.");
    }

    private TextField getFrameBoldTextField() {
        return new TextField(By.xpath(XPATH_IFRAME_BOLD_TEXT), "Frame text field.");
    }
}

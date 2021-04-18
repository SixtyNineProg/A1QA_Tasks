package by.a1qa.klimov.pageobjects;

import by.a1qa.klimov.framework.browser.BrowserActions;
import by.a1qa.klimov.framework.elements.Button;
import by.a1qa.klimov.framework.elements.Frame;
import by.a1qa.klimov.framework.elements.TextField;
import by.a1qa.klimov.framework.models.BaseForm;
import org.openqa.selenium.By;

public class FrameMainPage extends BaseForm {
    private final static String XPATH_IFRAME_TEXT = "//body[@id='tinymce']//p";
    private final static String XPATH_IFRAME_BOLD_TEXT = "//body[@id='tinymce']//strong";
    private final static String XPATH_BOLD_BUTTON = "//button[@aria-label='Bold']";

    private Button boldButton;

    public FrameMainPage(By locator, String name) {
        super(new Frame(locator, name));
    }

    public void frameSetText(String text, String frameXpath) {
        BrowserActions.switchToFrame(By.xpath(frameXpath));
        getFrameTextField().sendText(text);
        BrowserActions.switchToDefaultContent();
    }

    public void frameClearText(String frameXpath) {
        BrowserActions.switchToFrame(By.xpath(frameXpath));
        getFrameTextField().clear();
        BrowserActions.switchToDefaultContent();
    }

    public String frameGetText(String frameXpath) {
        BrowserActions.switchToFrame(By.xpath(frameXpath));
        String text = getFrameTextField().getText();
        BrowserActions.switchToDefaultContent();
        return text;
    }

    public void highlightFrameText(String frameXpath) {
        BrowserActions.switchToFrame(By.xpath(frameXpath));
        getFrameTextField().highlightText();
        BrowserActions.switchToDefaultContent();
    }

    public void boldButtonClick() {
        getBoldButton().click();
    }

    public boolean isBoldFrameText(String text, String frameXpath) {
        BrowserActions.switchToFrame(By.xpath(frameXpath));
        boolean textIsBolded = getFrameBoldTextField().getText().equals(text);
        BrowserActions.switchToDefaultContent();
        return textIsBolded;
    }

    private TextField getFrameTextField() {
        return new TextField(By.xpath(XPATH_IFRAME_TEXT), "Frame text field.");
    }

    private TextField getFrameBoldTextField() {
        return new TextField(By.xpath(XPATH_IFRAME_BOLD_TEXT), "Frame text field.");
    }

    private Button getBoldButton() {
        return boldButton == null ?
                boldButton = new Button(By.xpath(XPATH_BOLD_BUTTON), "Bold button.") :
                boldButton;
    }
}

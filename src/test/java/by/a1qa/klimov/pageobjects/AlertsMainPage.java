package by.a1qa.klimov.pageobjects;

import by.a1qa.klimov.elements.Button;
import by.a1qa.klimov.elements.Label;
import by.a1qa.klimov.elements.TextField;
import by.a1qa.klimov.model.BaseForm;
import org.openqa.selenium.By;

public class AlertsMainPage extends BaseForm {
    private static final String XPATH_BUTTON_ALERT = "//button[contains(@onclick,'jsAlert')]";
    private static final String XPATH_BUTTON_CONFIRM = "//button[contains(@onclick,'jsConfirm')]";
    private static final String XPATH_BUTTON_PROMPT = "//button[contains(@onclick,'jsPrompt')]";
    private static final String XPATH_TEXT_RESULT = "//p[@id='result']";

    private Button buttonAlert;
    private Button buttonConfirm;
    private Button buttonPrompt;
    private Label labelResult;

    public AlertsMainPage(By locator, String name) {
        super(new Button(locator, name));
    }

    public void buttonAlertClick() {
        getButtonAlert().click();
    }

    public void buttonConfirmClick() {
        getButtonConfirm().click();
    }

    public void buttonPromptClick() {
        getButtonPrompt().click();
    }

    public String getTextResult() {
        return getLabelResult().getText();
    }

    private Button getButtonAlert() {
        return buttonAlert == null ?
                buttonAlert = new Button(By.xpath(XPATH_BUTTON_ALERT), "Button generate alert.") :
                buttonAlert;
    }

    private Label getLabelResult() {
        return labelResult == null ?
                labelResult = new Label(By.xpath(XPATH_TEXT_RESULT), "Result text field.") :
                labelResult;
    }

    private Button getButtonConfirm() {
        return buttonConfirm == null ?
                buttonConfirm = new Button(By.xpath(XPATH_BUTTON_CONFIRM), "Button generate confirm.") :
                buttonConfirm;
    }

    private Button getButtonPrompt() {
        return buttonPrompt == null ?
                buttonPrompt = new Button(By.xpath(XPATH_BUTTON_PROMPT), "Button generate prompt.") :
                buttonPrompt;
    }
}

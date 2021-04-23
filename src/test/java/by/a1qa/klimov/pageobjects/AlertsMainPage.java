package by.a1qa.klimov.pageobjects;

import by.a1qa.klimov.framework.elements.Button;
import by.a1qa.klimov.framework.elements.Label;
import by.a1qa.klimov.framework.models.BaseForm;
import org.openqa.selenium.By;

public class AlertsMainPage extends BaseForm {
    private static final String XPATH_BUTTON_ALERT = "//button[contains(@onclick,'jsAlert')]";
    private static final String XPATH_BUTTON_CONFIRM = "//button[contains(@onclick,'jsConfirm')]";
    private static final String XPATH_BUTTON_PROMPT = "//button[contains(@onclick,'jsPrompt')]";
    private static final String XPATH_TEXT_RESULT = "//p[@id='result']";

    private Button buttonAlert = new Button(By.xpath(XPATH_BUTTON_ALERT), "Button generate alert.");
    private Button buttonConfirm = new Button(By.xpath(XPATH_BUTTON_CONFIRM), "Button generate confirm.");
    private Button buttonPrompt = new Button(By.xpath(XPATH_BUTTON_PROMPT), "Button generate prompt.");
    private Label labelResult = new Label(By.xpath(XPATH_TEXT_RESULT), "Result text field.");

    public AlertsMainPage(By locator, String name) {
        super(new Button(locator, name));
    }

    public void buttonAlertClick() {
        buttonAlert.click();
    }

    public void buttonConfirmClick() {
        buttonConfirm.click();
    }

    public void buttonPromptClick() {
        buttonPrompt.click();
    }

    public String getTextResult() {
        return labelResult.getText();
    }
}

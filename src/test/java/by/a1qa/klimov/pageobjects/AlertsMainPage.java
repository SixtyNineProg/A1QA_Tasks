package by.a1qa.klimov.pageobjects;

import by.a1qa.klimov.elements.Button;
import by.a1qa.klimov.model.BaseForm;
import org.openqa.selenium.By;

public class AlertsMainPage extends BaseForm {
    private static final String XPATH_BUTTON_ALERT = "//button[contains(@onclick,'jsAlert')]";

    private Button buttonAlert;

    public AlertsMainPage(By locator, String name) {
        super(new Button(locator, name));
    }

    public void buttonAlertClick() {
        getButtonAlert().click();
    }

    private Button getButtonAlert() {
        return buttonAlert == null ?
                buttonAlert = new Button(By.xpath(XPATH_BUTTON_ALERT), "Button generate alert.") :
                buttonAlert;
    }
}

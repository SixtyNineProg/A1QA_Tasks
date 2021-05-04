package by.a1qa.klimov.pageobjects;

import by.a1qa.klimov.framework.elements.Label;
import by.a1qa.klimov.framework.models.BaseForm;
import org.openqa.selenium.By;

public class ResultAuthPage extends BaseForm {
    private static final String XPATH_TEXT_AUTH = "//*[contains(text(),'Congratulations! You must have the proper credentials.')]";

    private Label textSussesAuth = new Label(By.xpath(XPATH_TEXT_AUTH), "Result text auth.");

    public ResultAuthPage(By locator, String name) {
        super(new Label(locator, name));
    }

    public String getTextAfterAuth() {
        return textSussesAuth.getText();
    }
}

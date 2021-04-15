package by.a1qa.klimov.pageobjects;

import by.a1qa.klimov.elements.Label;
import by.a1qa.klimov.model.BaseForm;
import org.openqa.selenium.By;

public class ResultAuthPage extends BaseForm {
    private static final String XPATH_TEXT_AUTH = "//*[contains(text(),'Congratulations! You must have the proper credentials.')]";

    private Label textSussesAuth;

    public ResultAuthPage(By locator, String name) {
        super(new Label(locator, name));
    }

    public String getTextAfterAuth() {
        return getLabelWithResultAuth().getText();
    }

    private Label getLabelWithResultAuth() {
        return textSussesAuth == null ?
                textSussesAuth = new Label(By.xpath(XPATH_TEXT_AUTH), "Result text auth.") :
                textSussesAuth;
    }
}

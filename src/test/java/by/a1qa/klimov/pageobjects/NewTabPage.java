package by.a1qa.klimov.pageobjects;

import by.a1qa.klimov.framework.elements.Label;
import by.a1qa.klimov.framework.models.BaseForm;
import org.openqa.selenium.By;

public class NewTabPage extends BaseForm {
    private static final String XPATH_TEXT_NEW_WINDOW = "//div[@class='example']//*[contains(text(),'New Window')]";

    private Label textNewWindow = new Label(By.xpath(XPATH_TEXT_NEW_WINDOW), "Result text new window.");

    public NewTabPage(By locator, String name) {
        super(new Label(locator, name));
    }

    public String getTextNewWindow() {
        return textNewWindow.getText();
    }
}

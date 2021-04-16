package by.a1qa.klimov.pageobjects;

import by.a1qa.klimov.framework.elements.Label;
import by.a1qa.klimov.framework.model.BaseForm;
import org.openqa.selenium.By;

public class HoverMainPage extends BaseForm {
    public HoverMainPage(By locator, String name) {
        super(new Label(locator, name));
    }

    public boolean atPage() {
        return isOpened();
    }
}

package by.a1qa.klimov.pageobjects;

import by.a1qa.klimov.elements.Label;
import by.a1qa.klimov.model.BaseForm;
import org.openqa.selenium.By;

public class HoverMainPage extends BaseForm {
    public HoverMainPage(By locator, String name) {
        super(new Label(locator, name));
    }

    public boolean atPage() {
        return isOpened();
    }
}

package by.a1qa.klimov.framework.elements;

import by.a1qa.klimov.framework.models.BaseElement;
import org.openqa.selenium.By;

public class Label extends BaseElement {
    public Label(By locator, String name) {
        super(locator, name);
    }
}

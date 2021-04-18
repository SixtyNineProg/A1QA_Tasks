package by.a1qa.klimov.framework.elements;

import by.a1qa.klimov.framework.models.BaseElement;
import org.openqa.selenium.By;

public class Frame extends BaseElement {
    public Frame(By locator, String name) {
        super(locator, name);
    }

    public void setText(String text) {
        findElement().sendKeys(text);
    }
}

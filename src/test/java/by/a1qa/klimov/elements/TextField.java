package by.a1qa.klimov.elements;

import by.a1qa.klimov.model.BaseElement;
import org.openqa.selenium.By;

public class TextField extends BaseElement {
    public TextField(By locator, String name) {
        super(locator, name);
    }

    public void sendText(String text) {
        findElement().sendKeys(text);
    }
}

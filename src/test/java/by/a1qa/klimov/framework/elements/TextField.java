package by.a1qa.klimov.framework.elements;

import by.a1qa.klimov.framework.models.BaseElement;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

@Log4j
public class TextField extends BaseElement {
    public TextField(By locator, String name) {
        super(locator, name);
    }

    public void sendText(String text) {
        findElement().sendKeys(text);
    }

    public void highlightText() {
        findElement().click();
        findElement().sendKeys(Keys.CONTROL, "a");
    }
}

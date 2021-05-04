package by.a1qa.klimov.framework.elements;

import by.a1qa.klimov.framework.models.BaseElement;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;

@Log4j
public class Label extends BaseElement {
    public Label(By locator, String name) {
        super(locator, name);
    }
}

package by.a1qa.klimov.framework.elements;

import by.a1qa.klimov.framework.models.BaseElement;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;

@Log4j
public class Image extends BaseElement {
    public Image(By locator, String name) {
        super(locator, name);
    }
}

package by.a1qa.klimov.elements;

import by.a1qa.klimov.model.BaseElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Container extends BaseElement {
    public Container(By locator, String name) {
        super(locator, name);
    }

    public List<WebElement> getElementsAsList(String xpath) {
        return findElement().findElements(By.xpath(xpath));
    }

    public List<WebElement> getElementsAsListWithLimit(String xpath, int limit) {
        return findElement().findElements(By.xpath(xpath + "[position()<=" + limit + "]"));
    }
}

package by.a1qa.klimov.elements;

import by.a1qa.klimov.model.BaseElement;
import by.a1qa.klimov.property.ConfigurationProperties;
import by.a1qa.klimov.webdriversetting.WebDriverSinglton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Properties;

public class Container extends BaseElement { ;
    public Container(By locator, String name) {
        super(locator, name);
    }

    public List<WebElement> getElementsAsList(String xpathTag) {
        return findElement().findElements(By.xpath(xpathTag));
    }

    public List<WebElement> getElementsAsListWithLimit(String xpathTag, int limit) {
        return findElement().findElements(By.xpath(xpathTag + "[position()<=" + limit + "]"));
    }
}

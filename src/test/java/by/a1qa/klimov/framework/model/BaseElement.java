package by.a1qa.klimov.framework.model;

import by.a1qa.klimov.framework.property.ConfigurationProperties;
import by.a1qa.klimov.framework.webdriversetting.WebDriverSinglton;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static by.a1qa.klimov.framework.utils.Constants.*;

@Log4j
public abstract class BaseElement {
    private WebDriver driver = WebDriverSinglton.getWebDriver();
    private Actions actions = new Actions(driver);
    private By locator;
    private String name;

    public BaseElement(By locator, String name) {
        log.info(ELEMENT_CREATED + name);
        this.locator = locator;
        this.name = name;
    }

    public boolean isDisplayed() {
        log.info(ELEMENT_DISPLAYED + name);
        try {
            waitForDisplayed();
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void click() {
        log.info(ELEMENT_CLICKED + name);
        findElement().click();
    }

    public void actionsClick() {
        log.info(ELEMENT_CLICKED + name);
        actions.click(findElement());
    }

    public String getAttribute(String name) {
        log.info(ELEMENT_GET_ATTRIBUTE + this.name);
        return findElement().getAttribute(name);
    }

    public String getText() {
        log.info(ELEMENT_GET_TEXT + this.name);
        return findElement().getText();
    }

    public void waitForDisplayed() {
        log.info(WAIT_PRESENCE_OF_ELEMENT + name);
        new WebDriverWait(driver, Long.parseLong(
                ConfigurationProperties.getConfigurationPropertyByKey("waitLoadingPageSeconds")))
                .until(ExpectedConditions.presenceOfElementLocated(locator)).isDisplayed();
    }

    public WebElement findElement() {
        log.info(FIND_ELEMENT + name);
        return driver.findElement(locator);
    }

    public List<WebElement> findElements() {
        log.info(FIND_ELEMENT + name);
        return driver.findElements(locator);
    }

    public List<WebElement> getElementsAsList(By locator) {
        return driver.findElements(locator);
    }

    public List<WebElement> getElementsAsListWithLimit(String xpath, int limit) {
        return driver.findElements(By.xpath(xpath + "[position()<=" + limit + "]"));
    }

    public void moveToElement() {
        log.info(MOVE_TO_ELEMENT + name);
        actions.moveToElement(findElement());
    }

    public Point getLocation() {
        log.info(MOVE_TO_ELEMENT + name);
        return findElement().getLocation();
    }
}

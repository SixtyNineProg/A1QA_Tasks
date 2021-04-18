package by.a1qa.klimov.framework.models;

import by.a1qa.klimov.framework.action.ActionsSingleton;
import by.a1qa.klimov.framework.property.ConfigurationProperties;
import by.a1qa.klimov.framework.webdriversetting.WebDriverSingleton;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Log4j
public abstract class BaseElement {
    public static final String ELEMENT_CREATED = "Element created with name: ";
    public static final String ELEMENT_DISPLAYED = "Element displayed with name: ";
    public static final String ELEMENT_CLICKED = "Element clicked with name: ";
    public static final String ELEMENT_GET_ATTRIBUTE = "Get attribute from an element with name: ";
    public static final String ELEMENT_GET_TEXT = "Get text from an element with name: ";
    public static final String WAIT_PRESENCE_OF_ELEMENT = "Wait presence of element with name: ";
    public static final String FIND_ELEMENT = "Find element with name: ";
    public static final String MOVE_TO_ELEMENT = "Move to element with name: ";
    public static final String CLEAR_ELEMENT = "Clear element with name: ";
    public static final String GET_WIGHT = "Take length of element with name: ";

    private WebDriver driver = WebDriverSingleton.getWebDriver();
    private Actions actions = ActionsSingleton.getActions();
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

    public void moveToElement() {
        log.info(MOVE_TO_ELEMENT + name);
        actions.moveToElement(findElement()).perform();
    }

    public void clear() {
        log.info(CLEAR_ELEMENT + name);
        findElement().clear();
    }

    public int getWight() {
        log.info(GET_WIGHT + name);
        return findElement().getSize().width;
    }
}

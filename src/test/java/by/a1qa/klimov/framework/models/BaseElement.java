package by.a1qa.klimov.framework.models;

import by.a1qa.klimov.framework.property.ConfigurationProperties;
import by.a1qa.klimov.framework.webdriversetting.WebDriverSingleton;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Log4j
public abstract class BaseElement {

    private Actions actions = new Actions(WebDriverSingleton.getWebDriver());
    private By locator;
    private String name;

    public BaseElement(By locator, String name) {
        log.info("Element created with name: " + name);
        this.locator = locator;
        this.name = name;
    }

    public boolean isDisplayed() {
        log.info("Element displayed with name: " + name);
        try {
            waitForDisplayed();
            return findElement().isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void click() {
        log.info("Element clicked with name: " + name);
        findElement().click();
    }

    public String getAttribute(String name) {
        log.info("Get attribute from an element with name: " + this.name);
        return findElement().getAttribute(name);
    }

    public String getText() {
        log.info("Get text from an element with name: " + this.name);
        return findElement().getText();
    }

    public void waitForDisplayed() {
        log.info("Wait presence of element with name: " + name);
        new WebDriverWait(WebDriverSingleton.getWebDriver(), Long.parseLong(
                ConfigurationProperties.getConfigurationPropertyByKey("waitLoadingPageSeconds")))
                .until(ExpectedConditions.presenceOfElementLocated(locator)).isDisplayed();
    }

    public WebElement findElement() {
        log.info("Find element with name: " + name);
        return WebDriverSingleton.getWebDriver().findElement(locator);
    }

    public void moveToElement() {
        log.info("Move to element with name: " + name);
        actions.moveToElement(findElement()).perform();
    }

    public void clear() {
        log.info("Clear element with name: " + name);
        findElement().clear();
    }

    public int getWight() {
        log.info("Take length of element with name: " + name);
        return findElement().getSize().width;
    }
}

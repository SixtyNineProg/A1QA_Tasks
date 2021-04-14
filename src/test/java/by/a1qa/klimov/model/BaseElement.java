package by.a1qa.klimov.model;

import by.a1qa.klimov.property.ConfigurationProperties;
import by.a1qa.klimov.webdriversetting.WebDriverSinglton;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Properties;

import static by.a1qa.klimov.utils.Constants.*;

@Log4j
public abstract class BaseElement {
    private Properties configProperties = ConfigurationProperties.getConfigurationProperties();
    private WebDriver driver = WebDriverSinglton.getWebDriver();
    private By locator;
    private String name;

    public BaseElement(By locator, String name) {
        log.info(ELEMENT_CREATED + name);
        this.locator = locator;
        this.name = name;
    }

    public boolean isDisplayed() {
        log.info(ELEMENT_DISPLAYED + name);
        return findElement().isDisplayed();
    }

    public void click() {
        log.info(ELEMENT_CLICKED + name);
        findElement().click();
    }

    public String getAttribute(String name) {
        log.info(ELEMENT_GET_ATTRIBUTE + this.name);
        return findElement().getAttribute(name);
    }

    public void waitForOpen() {
        log.info(WAIT_PRESENCE_OF_ELEMENT + name);
        new WebDriverWait(
                driver, Long.parseLong(configProperties.getProperty("waitLoadingPageSeconds")))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public WebElement findElement() {
        log.info(FIND_ELEMENT + name);
        return driver.findElement(locator);
    }

    public List<WebElement> getElementsAsList(By locator) {
        return driver.findElements(locator);
    }

    public List<WebElement> getElementsAsList(String xpathTag) {
        return driver.findElements(By.xpath(xpathTag));
    }

    public List<WebElement> getElementsAsListWithLimit(String xpath, int limit) {
        return driver.findElements(By.xpath(xpath + "[position()<=" + limit + "]"));
    }
}

package by.a1qa.klimov.model;

import by.a1qa.klimov.property.ConfigurationProperties;
import by.a1qa.klimov.webdriversetting.WebDriverSinglton;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
        log.info(name + ELEMENT_DISPLAYED);
        return findElement().isDisplayed();
    }

    public void click() {
        log.info(name + ELEMENT_CLICKED);
        findElement().click();
    }

    public String getAttribute(String name) {
        log.info(ELEMENT_GET_ATTRIBUTE + name);
        return findElement().getAttribute(name);
    }

    public void waitForOpen() {
        new WebDriverWait(
                driver, Long.parseLong(configProperties.getProperty("waitLoadingPageSeconds")))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public WebElement findElement() {
        return driver.findElement(locator);
    }
}

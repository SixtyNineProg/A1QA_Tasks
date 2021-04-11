package by.a1qa.klimov.model;

import by.a1qa.klimov.property.ConfigurationProperties;
import by.a1qa.klimov.webdriversetting.WebDriverSinglton;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Properties;

@Slf4j
public abstract class BaseElement {
    private Properties configProperties = ConfigurationProperties.getConfigurationProperties();
    private WebDriver driver = WebDriverSinglton.getWebDriver();
    private By locator;
    private String name;

    public BaseElement(By locator, String name) {
        this.locator = locator;
        this.name = name;
    }

    public boolean isDisplayed() {
        return findElement().isDisplayed();
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

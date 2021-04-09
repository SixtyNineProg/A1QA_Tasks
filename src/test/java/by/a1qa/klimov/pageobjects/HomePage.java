package by.a1qa.klimov.pageobjects;

import by.a1qa.klimov.property.DataProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

import static by.a1qa.klimov.utils.Constants.*;

public class HomePage {
    private Properties dataProperties;
    protected WebDriver driver;

    public HomePage(WebDriver driver) throws Exception {
        this.driver = driver;
        dataProperties = DataProperties.getProperties();
    }

    public void getSearchFieldAndInsertText(String text) {
        driver.findElement(By.xpath(XPATH_HOME_SEARCH_FORM)).sendKeys(text);
    }

    public void getSearchButtonAndClick() {
        driver.findElement(By.xpath(XPATH_HOME_BUTTON_SEARCH_FORM)).click();
    }

    public boolean atPage() {
        try {
            return driver.findElement(By.xpath(XPATH_HOME_CLUSTER)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void open() {
        String url = dataProperties.getProperty("steamShopUrl");
        driver.get(url);
    }
}

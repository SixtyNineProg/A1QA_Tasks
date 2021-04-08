package by.a1qa.klimov.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

import static by.a1qa.klimov.utils.Constants.*;

public class HomePage {
    protected Properties properties;
    protected WebDriver driver;

    public HomePage(WebDriver driver, Properties properties) {
        this.driver = driver;
        this.properties = properties;
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
        driver.get(STEAM_SHOP_URL);
    }
}

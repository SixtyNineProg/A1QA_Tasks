package by.a1qa.klimov.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static by.a1qa.klimov.utils.Constants.*;

public class HomePage {
    protected WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getUniqueHomeElement() {
        return driver.findElement(By.xpath(XPATH_HOME_CLUSTER));
    }

    public void getSearchFieldAndInsertText(String text) {
        driver.findElement(By.xpath(XPATH_HOME_SEARCH_FORM)).sendKeys(text);
    }

    public void getSearchButtonAndClick() {
        driver.findElement(By.xpath(XPATH_HOME_BUTTON_SEARCH_FORM)).click();
    }
}

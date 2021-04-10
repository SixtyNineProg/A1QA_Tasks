package by.a1qa.klimov.model;

import by.a1qa.klimov.webdriversetting.WebDriverSinglton;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@AllArgsConstructor
public abstract class BaseElement {
    private WebDriver driver = WebDriverSinglton.getWebDriver();
    private By locator;
    private String name;

    public BaseElement(By locator, String name) {
        this.locator = locator;
        this.name = name;
    }

    public boolean isDisplayed() {
        return true;
    }

    public void waitForOpen() {
    }

    public WebElement findElement() {
        return driver.findElement(locator);
    }
}

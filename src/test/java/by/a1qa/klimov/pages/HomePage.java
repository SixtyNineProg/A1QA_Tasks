package by.a1qa.klimov.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static by.a1qa.klimov.utils.Constants.XPATH_HOME_PAGE_BODY;

public class HomePage {
    protected WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public WebElement getBodyElement() {
        return driver.findElement(By.xpath(XPATH_HOME_PAGE_BODY));
    }
}

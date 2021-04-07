package by.a1qa.klimov.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static by.a1qa.klimov.utils.Constants.*;

public class SearchResultPage {
    protected WebDriver driver;

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getSearchResults() {
        return driver.findElements(By.xpath(XPATH_SEARCH_RESULT_ELEMENTS));
    }

    public List<WebElement> getPrices() {
        return driver.findElements(By.xpath(XPATH_SEARCH_PRICE));
    }

    public WebElement getUniqueSearchElement() {
        return driver.findElement(By.xpath(XPATH_SEARCH_RESULT_ELEMENT));
    }

    public void sortGamesPriceAsc() {
        driver.findElement(By.xpath(XPATH_SEARCH_BUTTON_SORT)).click();
        driver.findElement(By.xpath(XPATH_SEARCH_BUTTON_SORT_PRICE_ASC)).click();
    }
}

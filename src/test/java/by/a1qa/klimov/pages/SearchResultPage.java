package by.a1qa.klimov.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Properties;

import static by.a1qa.klimov.utils.Constants.*;

public class SearchResultPage {
    protected Properties properties;
    protected WebDriver driver;

    public SearchResultPage(WebDriver driver, Properties properties) {
        this.driver = driver;
        this.properties = properties;
    }

    public List<WebElement> getSearchResult() {
        return driver.findElements(By.xpath(XPATH_SEARCH_RESULT_ELEMENTS));
    }

    public int getSizeSearchResult() {
        return getSearchResult().size();
    }

    public List<WebElement> getPricesWithLimit(int limit) {
        return driver.findElements(By.xpath(XPATH_SEARCH_PRICE+ "[position()<=" + limit + "]"));
    }

    public void sortGamesPriceAsc() {
        driver.findElement(By.xpath(XPATH_SEARCH_BUTTON_SORT)).click();
        driver.findElement(By.xpath(XPATH_SEARCH_BUTTON_SORT_PRICE_ASC)).click();
    }

    public boolean atPage() {
        try {
            return driver.findElement(By.xpath(XPATH_SEARCH_RESULT_ELEMENT)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void waitLoadingPageAfterSort() {
        WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(properties.getProperty("waitLoadingPageSeconds")));
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//div[@id='search_result_container']"), "style", ""));
    }

    public boolean checkSortWebElementsByAsc() {
        List<WebElement> searchResult = getPricesWithLimit(LIMIT_CHECKED_GAMES);
        for (int i = 0; i < searchResult.size() - 1; i++) {
            String firstStrPrice = searchResult.get(i).getAttribute(ATTRIBUTE_FOR_PRICE);
            String secondStrPrice = searchResult.get(i + 1).getAttribute(ATTRIBUTE_FOR_PRICE);
            if (firstStrPrice != null && secondStrPrice != null) {
                int firstPrice = firstStrPrice.equals("") ? 0 : Integer.parseInt(firstStrPrice);
                int secondPrice = secondStrPrice.equals("") ? 0 : Integer.parseInt(secondStrPrice);
                if (!(firstPrice <= secondPrice)) return false;
            } else return false;
        }
        return true;
    }
}

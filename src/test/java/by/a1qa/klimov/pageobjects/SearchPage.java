package by.a1qa.klimov.pageobjects;

import by.a1qa.klimov.elements.Button;
import by.a1qa.klimov.elements.Cluster;
import by.a1qa.klimov.elements.Container;
import by.a1qa.klimov.property.ConfigurationProperties;
import by.a1qa.klimov.property.DataProperties;
import by.a1qa.klimov.webdriversetting.WebDriverSinglton;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static by.a1qa.klimov.utils.Constants.*;

public class SearchPage extends BaseSteamPage {
    private Properties configProperties = ConfigurationProperties.getConfigurationProperties();
    private Properties dataProperties = DataProperties.getDataProperties();
    protected WebDriver driver = WebDriverSinglton.getWebDriver();

    public SearchPage(By locator, String name) {
        super(new Cluster(locator, name));
    }

    public int getSizeSearchResult() {
        return getSearchResult().size();
    }

    public void sortGamesPriceAsc() {
        new Button(By.xpath(XPATH_SEARCH_BUTTON_SORT), "Button sort").click();
        new Button(By.xpath(XPATH_SEARCH_BUTTON_SORT_PRICE_ASC), "Button sort price ASC").click();
    }

    public boolean atPage() {
        try {
            return baseElementIsDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void waitLoadingPageAfterSort() {
        WebDriverWait wait = new WebDriverWait(driver,
                Long.parseLong(configProperties.getProperty("waitLoadingPageSeconds")));
        wait.until(ExpectedConditions.attributeToBe(
                By.xpath(XPATH_WAIT_RESULT_SORT_CONTAINER),
                ATTRIBUTE_FOR_WAIT_SEARCH_RESULT,
                EXPECTED_ATTRIBUTE_VALUE));
    }

    public List<WebElement> getSearchResult() {
        return getSearchResultContainer().getElementsAsList(XPATH_CONTAINER_TAG_SEARCH);
    }

    public List<WebElement> getSearchResultLimit(int limit) {
        return getSearchResultContainer().getElementsAsListWithLimit(XPATH_CONTAINER_TAG_SEARCH, limit);
    }

    public List<Integer> getSearchGamesPrices() {
        List<Integer> integerPrices = new ArrayList<>();
        List<WebElement> searchResult = getSearchResultLimit(
                Integer.parseInt(dataProperties.getProperty("limitCheckedGames"))
        );
        for (WebElement webElement : searchResult) {
            integerPrices.add(
                    Integer.parseInt(
                            webElement.findElement(By.xpath(XPATH_CONTAINER_SEARCH_PRICES))
                                    .getAttribute(ATTRIBUTE_FOR_PRICE)));
        }
        return integerPrices;
    }

    private Container getSearchResultContainer() {
        return new Container(By.xpath(XPATH_SEARCH_RESULT_CONTAINER), "Search result container");
    }
}

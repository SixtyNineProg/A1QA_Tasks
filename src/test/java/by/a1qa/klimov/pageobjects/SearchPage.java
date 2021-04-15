package by.a1qa.klimov.pageobjects;

import by.a1qa.klimov.elements.*;
import by.a1qa.klimov.model.BaseForm;
import by.a1qa.klimov.property.ConfigurationProperties;
import by.a1qa.klimov.property.DataProperties;
import by.a1qa.klimov.webdriversetting.WebDriverSinglton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static by.a1qa.klimov.utils.Constants.*;

public class SearchPage extends BaseForm {
    public static final String XPATH_SEARCH_RESULT_CONTAINER =
            "//div[@id='search_result_container']//div[@id='search_resultsRows']";
    public static final String XPATH_SEARCH_BUTTON_SORT = "//*[@id='sort_by_trigger']";
    public static final String XPATH_SEARCH_BUTTON_SORT_PRICE_ASC = "//*[@id='Price_ASC']";
    public static final String XPATH_SEARCH_PRICES = "//div[@data-price-final]";
    public static final String XPATH_SEARCH_RESULTS = "//div[@id='search_resultsRows']/a";
    public static final String XPATH_WAIT_RESULT_SORT_CONTAINER = "//div[@id='search_result_container']";

    private Button sortButton;
    private Button sortButtonByASC;
    private Label searchResultContainer;

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
        getSortButton().click();
        getSortButtonByASC().click();
    }

    public boolean atSearchPage() {
        return isOpened();
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
        return getSearchResultContainer().getElementsAsList(By.xpath(XPATH_SEARCH_RESULTS));
    }

    public List<WebElement> getSearchResultLimit(int limit) {
        return getSearchResultContainer().getElementsAsListWithLimit(XPATH_SEARCH_PRICES, limit);
    }

    public List<Integer> getSearchGamesPrices() {
        List<Integer> integerPrices = new ArrayList<>();
        List<WebElement> searchResult = getSearchResultLimit(
                Integer.parseInt(dataProperties.getProperty("limitCheckedGames"))
        );
        for (WebElement webElement : searchResult) {
            integerPrices.add(
                    Integer.parseInt(
                            webElement
                                    .getAttribute(ATTRIBUTE_FOR_PRICE)));
        }
        return integerPrices;
    }

    public Button getSortButton() {
        return sortButton == null ?
                sortButton = new Button(By.xpath(XPATH_SEARCH_BUTTON_SORT), "Button sort") :
                sortButton;
    }

    public Button getSortButtonByASC() {
        return sortButtonByASC == null ?
                sortButtonByASC = new Button(By.xpath(XPATH_SEARCH_BUTTON_SORT_PRICE_ASC), "Button sort price ASC") :
                sortButtonByASC;
    }

    public Label getSearchResultContainer() {
        return searchResultContainer == null ?
                searchResultContainer =
                        new Label(By.xpath(XPATH_SEARCH_RESULT_CONTAINER), "Search result container") :
                searchResultContainer;
    }
}

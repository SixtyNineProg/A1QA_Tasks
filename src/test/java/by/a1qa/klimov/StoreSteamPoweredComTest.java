package by.a1qa.klimov;

import by.a1qa.klimov.pages.HomePage;
import by.a1qa.klimov.pages.SearchResultPage;
import by.a1qa.klimov.setting.WebDriverSettings;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.a1qa.klimov.utils.Constants.*;

public class StoreSteamPoweredComTest extends WebDriverSettings {

    @Test
    public void steamTest() {
        driver.manage().timeouts().implicitlyWait(Long.parseLong(property.getProperty("implicitlyWait")), TimeUnit.SECONDS);
        driver.get(STEAM_SHOP_URL);
        new WebDriverWait(driver, Long.parseLong(property.getProperty("explicitWait")))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH_LOGO_HOLDER_WITH_URL)));
        HomePage homePage = new HomePage(driver);
        WebElement homeElement = homePage.getUniqueHomeElement();
        Assert.assertNotNull(homeElement);

        homePage.getSearchFieldAndInsertText(SEARCH_REQUEST);
        homePage.getSearchButtonAndClick();
        new WebDriverWait(driver, Long.parseLong(property.getProperty("explicitWait")))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH_LOGO_HOLDER_WITH_URL)));
        SearchResultPage searchResultPage = new SearchResultPage(driver);
        WebElement searchElement = searchResultPage.getUniqueSearchElement();
        Assert.assertNotNull(searchElement);

        List<WebElement> searchResult = searchResultPage.getSearchResults();
        int actualSizeSearchResult = searchResult.size();
        Assert.assertNotEquals(actualSizeSearchResult, 0);

        searchResultPage.sortGamesPriceAsc();
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='search_resultsRows']//a")));
        List<WebElement> gamesPrices = searchResultPage.getPrices();
        boolean isSorted = checkSortWebElementsByAsc(gamesPrices);
        Assert.assertTrue(isSorted);
    }

    private boolean checkSortWebElementsByAsc(List<WebElement> searchResult) {
        for (int i = 1; i < LIMIT_CHECKED_GAMES; i++) {
            String firstStrPrice = searchResult.get(i - 1).getAttribute(ATTRIBUTE_FOR_PRICE);
            String secondStrPrice = searchResult.get(i).getAttribute(ATTRIBUTE_FOR_PRICE);
            int firstPrice;
            int secondPrice;
            if (firstStrPrice != null && firstStrPrice.equals("") &&
                    secondStrPrice != null && secondStrPrice.equals("")) {
                firstPrice = Integer.parseInt(firstStrPrice);
                secondPrice = Integer.parseInt(secondStrPrice);
            } else return false;
            if (!(firstPrice <= secondPrice)) return false;
        }
        return true;
    }
}

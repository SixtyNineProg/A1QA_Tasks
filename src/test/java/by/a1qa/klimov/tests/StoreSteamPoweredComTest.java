package by.a1qa.klimov.tests;

import by.a1qa.klimov.pageobjects.BrowserActions;
import by.a1qa.klimov.pageobjects.HomePage;
import by.a1qa.klimov.pageobjects.SearchPage;
import by.a1qa.klimov.pageobjects.StoreNavigationBar;
import by.a1qa.klimov.property.ConfigurationProperties;
import by.a1qa.klimov.property.DataProperties;
import by.a1qa.klimov.utils.Checker;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static by.a1qa.klimov.utils.Constants.*;

@Log4j
public class StoreSteamPoweredComTest extends TestSettings {
    public static final String XPATH_HOME_CLUSTER =
            "//div[contains(@class, 'home_page_body')]//div[contains(@class, 'home_cluster')]";
    public static final String XPATH_SEARCH_RESULT_CONTAINER =
            "//div[@id='search_result_container']//div[@id='search_resultsRows']";

    @Test
    public void steamTest() {
        HomePage homePage = new HomePage(By.xpath(XPATH_HOME_CLUSTER), "Home cluster");
        BrowserActions.openUrl(DataProperties.getDataPropertyByKey("steamShopUrl"));
        Assert.assertTrue(homePage.atPage(), "Home page is't open.");

        StoreNavigationBar storeNavigationBar = homePage.getStoreNavigationBar();
        storeNavigationBar.getSearchFieldAndInsertText(DataProperties.getDataPropertyByKey("searchRequest"));
        storeNavigationBar.getSearchButtonAndClick();
        SearchPage searchPage = new SearchPage(By.xpath(XPATH_SEARCH_RESULT_CONTAINER), "Steam search element");
        Assert.assertTrue(searchPage.atPage(), "Search page is't open.");
        Assert.assertTrue(searchPage.getSizeSearchResult() > 0, "Search results are't present.");

        searchPage.sortGamesPriceAsc();
        searchPage.waitLoadingPageAfterSort();
        List<Integer> gamesPrices = searchPage.getSearchGamesPrices();
        Assert.assertTrue(Checker.listIsSortedByASC(gamesPrices), "Search results are't sorted in ascending order");
    }
}

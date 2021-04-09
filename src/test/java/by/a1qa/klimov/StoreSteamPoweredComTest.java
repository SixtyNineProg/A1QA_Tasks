package by.a1qa.klimov;

import by.a1qa.klimov.pages.HomePage;
import by.a1qa.klimov.pages.SearchResultPage;
import by.a1qa.klimov.setting.WebDriverSettings;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class StoreSteamPoweredComTest extends WebDriverSettings {

    @Test
    public void StoreSteampoweredComTest() throws Exception {
        driver.manage().timeouts().implicitlyWait(Long.parseLong(configProperties.getProperty("implicitlyWait")), TimeUnit.SECONDS);
        HomePage homePage = new HomePage(driver);
        homePage.open();
        boolean atHomePage = homePage.atPage();
        Assert.assertTrue(atHomePage);

        homePage.getSearchFieldAndInsertText(dataProperties.getProperty("searchRequest"));
        homePage.getSearchButtonAndClick();
        SearchResultPage searchResultPage = new SearchResultPage(driver);
        boolean atSearchPage = searchResultPage.atPage();
        Assert.assertTrue(atSearchPage);
        int size = searchResultPage.getSizeSearchResult();
        Assert.assertNotEquals(size, 0);

        searchResultPage.sortGamesPriceAsc();
        searchResultPage.waitLoadingPageAfterSort();
        boolean isSorted = searchResultPage.checkSortWebElementsByAsc();
        Assert.assertTrue(isSorted);
    }
}

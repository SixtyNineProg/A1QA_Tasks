package by.a1qa.klimov;

import by.a1qa.klimov.pages.HomePage;
import by.a1qa.klimov.pages.SearchResultPage;
import by.a1qa.klimov.setting.WebDriverSettings;
import org.testng.Assert;
import org.testng.annotations.Test;

import static by.a1qa.klimov.utils.Constants.SEARCH_REQUEST;

public class StoreSteamPoweredComTest extends WebDriverSettings {

    @Test
    public void StoreSteampoweredComTest() {
        HomePage homePage = new HomePage(driver, property);
        homePage.open();
        boolean atHomePage = homePage.atPage();
        Assert.assertTrue(atHomePage);

        homePage.getSearchFieldAndInsertText(SEARCH_REQUEST);
        homePage.getSearchButtonAndClick();
        SearchResultPage searchResultPage = new SearchResultPage(driver, property);
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

package by.a1qa.klimov.tests;

import by.a1qa.klimov.pageobjects.HomePage;
import by.a1qa.klimov.pageobjects.SearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class StoreSteamPoweredComTest extends TestSettings {

    @Test
    public void StoreSteampoweredComTest() {
        driver.manage().timeouts().implicitlyWait(Long.parseLong(configProperties.getProperty("implicitlyWait")), TimeUnit.SECONDS);
        HomePage homePage = new HomePage(driver);
        homePage.open();
        boolean atHomePage = homePage.atPage();
        Assert.assertTrue(atHomePage);

        homePage.getSearchFieldAndInsertText(dataProperties.getProperty("searchRequest"));
        homePage.getSearchButtonAndClick();
        SearchPage searchPage = new SearchPage(driver);
        boolean atSearchPage = searchPage.atPage();
        Assert.assertTrue(atSearchPage);
        int size = searchPage.getSizeSearchResult();
        Assert.assertNotEquals(size, 0);

        searchPage.sortGamesPriceAsc();
        searchPage.waitLoadingPageAfterSort();
        boolean isSorted = searchPage.checkSortWebElementsByAsc();
        Assert.assertTrue(isSorted);
    }
}

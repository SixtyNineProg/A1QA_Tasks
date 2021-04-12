package by.a1qa.klimov.tests;

import by.a1qa.klimov.pageobjects.HomePage;
import by.a1qa.klimov.pageobjects.SearchPage;
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

    @Test
    public void steamTest() {
        driver.manage().timeouts().implicitlyWait(Long.parseLong(configProperties.getProperty("implicitlyWait")), TimeUnit.SECONDS);
        HomePage homePage = new HomePage(By.xpath(XPATH_HOME_CLUSTER), "Steam home cluster");
        homePage.open();
        boolean atHomePage = homePage.atPage();
        Assert.assertTrue(atHomePage, ASSERT_AT_HOME_PAGE);

        homePage.getSearchFieldAndInsertText(dataProperties.getProperty("searchRequest"));
        homePage.getSearchButtonAndClick();
        SearchPage searchPage = new SearchPage(By.xpath(XPATH_SEARCH_RESULT_CONTAINER), "Steam search element");
        boolean atSearchPage = searchPage.atPage();
        Assert.assertTrue(atSearchPage, ASSERT_AT_SEARCH_PAGE);
        int size = searchPage.getSizeSearchResult();
        Assert.assertTrue(size > 0, ASSERT_SEARCH_RESULTS_PRESENT);

        searchPage.sortGamesPriceAsc();
        searchPage.waitLoadingPageAfterSort();
        List<Integer> gamesPrices = searchPage.getSearchGamesPrices();
        boolean isSorted = Checker.listIsSortedByASC(gamesPrices);
        Assert.assertTrue(isSorted, ASSERT_SEARCH_RESULT_SORTED);
    }
}

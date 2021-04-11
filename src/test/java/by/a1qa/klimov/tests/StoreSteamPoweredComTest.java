package by.a1qa.klimov.tests;

import by.a1qa.klimov.pageobjects.HomePage;
import by.a1qa.klimov.pageobjects.SearchPage;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static by.a1qa.klimov.utils.Constants.XPATH_HOME_CLUSTER;
import static by.a1qa.klimov.utils.Constants.XPATH_SEARCH_RESULT_CONTAINER;

@Log4j
public class StoreSteamPoweredComTest extends TestSettings {

    @Test
    public void steamTest() {
        driver.manage().timeouts().implicitlyWait(Long.parseLong(configProperties.getProperty("implicitlyWait")), TimeUnit.SECONDS);
        HomePage homePage = new HomePage(By.xpath(XPATH_HOME_CLUSTER), "Steam home cluster");
        homePage.open();
        boolean atHomePage = homePage.atPage();
        Assert.assertTrue(atHomePage);

        homePage.getSearchFieldAndInsertText(dataProperties.getProperty("searchRequest"));
        homePage.getSearchButtonAndClick();
        SearchPage searchPage = new SearchPage(By.xpath(XPATH_SEARCH_RESULT_CONTAINER), "Steam search element");
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
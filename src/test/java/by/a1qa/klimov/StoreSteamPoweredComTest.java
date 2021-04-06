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

import java.util.List;
import java.util.concurrent.TimeUnit;

import static by.a1qa.klimov.utils.Constants.*;

public class StoreSteamPoweredComTest extends WebDriverSettings {

    @Test
    public void steamTest() {
        driver.manage().timeouts().implicitlyWait(Long.parseLong(property.getProperty("implicitlyWait")), TimeUnit.SECONDS);
        driver.get(STEAM_SHOP_URL);
        new WebDriverWait(driver, Long.parseLong(property.getProperty("explicitWait")))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH_LOGO_HOLDER)));
        HomePage homePage = new HomePage(driver);
        WebElement homeElement = homePage.getBodyElement();
        Assert.assertNotNull(homeElement);

        homePage.getSearchFieldAndInsertText(SEARCH_REQUEST);
        homePage.getSearchButtonAndClick();
        new WebDriverWait(driver, Long.parseLong(property.getProperty("explicitWait")))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH_LOGO_HOLDER)));
        SearchResultPage searchResultPage = new SearchResultPage(driver);
        List<WebElement> searchResult = searchResultPage.getSearchResults();
        int actualSizeSearchResult = searchResult.size();
        Assert.assertNotEquals(actualSizeSearchResult, 0);

        searchResultPage.sortGamesPriceAsc();
        searchResult = searchResultPage.getSearchResults();
        String string = searchResult.get(0).getText();
        Assert.assertTrue(true);
    }
}

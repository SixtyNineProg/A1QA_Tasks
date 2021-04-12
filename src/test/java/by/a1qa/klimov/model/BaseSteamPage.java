package by.a1qa.klimov.model;

import by.a1qa.klimov.elements.StoreNavigationBar;
import by.a1qa.klimov.webdriversetting.WebDriverSinglton;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static by.a1qa.klimov.utils.Constants.GET_PAGE_WITH_URL;
import static by.a1qa.klimov.utils.Constants.XPATH_STORE_NAVIGATION_BAR;

@Log4j
public abstract class BaseSteamPage extends BaseForm {
    private WebDriver driver = WebDriverSinglton.getWebDriver();

    public BaseSteamPage(BaseElement baseElement) {
        super(baseElement);
    }

    public StoreNavigationBar getStoreNavigationBar() {
        return new StoreNavigationBar(By.xpath(XPATH_STORE_NAVIGATION_BAR), "Store navigation bar");
    }

    public void getPage(String url) {
        log.info(GET_PAGE_WITH_URL + url);
        driver.get(url);
    }
}

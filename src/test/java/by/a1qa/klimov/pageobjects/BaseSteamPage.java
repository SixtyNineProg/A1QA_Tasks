package by.a1qa.klimov.pageobjects;

import by.a1qa.klimov.model.BaseElement;
import by.a1qa.klimov.model.BaseForm;
import by.a1qa.klimov.model.StoreNavigationBar;
import by.a1qa.klimov.webdriversetting.WebDriverSinglton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static by.a1qa.klimov.utils.Constants.XPATH_STORE_NAVIGATION_BAR;

public abstract class BaseSteamPage extends BaseForm {
    private WebDriver driver = WebDriverSinglton.getWebDriver();

    public BaseSteamPage(BaseElement baseElement) {
        super(baseElement);
    }

    public StoreNavigationBar getStoreNavigationBar() {
        return new StoreNavigationBar(By.xpath(XPATH_STORE_NAVIGATION_BAR), "Store navigation bar");
    }

    public void getPage(String url) {
        driver.get(url);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void refresh() {
        driver.navigate().refresh();
    }
}

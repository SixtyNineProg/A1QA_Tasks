package by.a1qa.klimov.pageobjects;

import by.a1qa.klimov.elements.Cluster;
import by.a1qa.klimov.property.DataProperties;
import org.openqa.selenium.By;

import java.util.Properties;

public class HomePage extends BaseSteamPage {
    private Properties dataProperties = DataProperties.getDataProperties();

    public HomePage(By locator, String name) {
        super(new Cluster(locator, name));
    }

    public void getSearchFieldAndInsertText(String text) {
        getStoreNavigationBar().getSearchField().sendText(text);
    }

    public void getSearchButtonAndClick() {
        getStoreNavigationBar().getSearchButton().click();
    }

    public boolean atPage() {
        return baseElementIsDisplayed();
    }

    public void open() {
        getPage(dataProperties.getProperty("steamShopUrl"));
    }
}

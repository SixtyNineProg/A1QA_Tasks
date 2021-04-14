package by.a1qa.klimov.pageobjects;

import by.a1qa.klimov.elements.AnotherElement;
import by.a1qa.klimov.elements.Cluster;
import by.a1qa.klimov.elements.StoreNavigationBar;
import by.a1qa.klimov.model.BaseElement;
import by.a1qa.klimov.model.BaseForm;
import by.a1qa.klimov.property.DataProperties;
import org.openqa.selenium.By;

import java.util.Properties;

public class HomePage extends BaseForm {
    public static final String XPATH_STORE_NAVIGATION_BAR = "//div[@class='store_nav']";

    public HomePage(By locator, String name) {
        super(new AnotherElement(locator, name));
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

    private StoreNavigationBar getStoreNavigationBar() {
        return new StoreNavigationBar(
                new AnotherElement(By.xpath(XPATH_STORE_NAVIGATION_BAR), "Store navigation element"));
    }
}

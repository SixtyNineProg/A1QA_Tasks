package by.a1qa.klimov.pageobjects;

import by.a1qa.klimov.elements.Label;
import by.a1qa.klimov.model.BaseForm;
import org.openqa.selenium.By;

public class HomePage extends BaseForm {
    public static final String XPATH_STORE_NAVIGATION_BAR = "//div[@class='store_nav']";

    public HomePage(By locator, String name) {
        super(new Label(locator, name));
    }

    public boolean atPage() {
        return isOpened();
    }

    public StoreNavigationBar getStoreNavigationBar() {
        return new StoreNavigationBar(
                new Label(By.xpath(XPATH_STORE_NAVIGATION_BAR), "Store navigation element"));
    }
}

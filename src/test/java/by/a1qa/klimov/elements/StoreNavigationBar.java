package by.a1qa.klimov.elements;

import by.a1qa.klimov.elements.Button;
import by.a1qa.klimov.elements.TextField;
import by.a1qa.klimov.model.BaseElement;
import org.openqa.selenium.By;

import static by.a1qa.klimov.utils.Constants.XPATH_BUTTON_SEARCH_FORM;
import static by.a1qa.klimov.utils.Constants.XPATH_SEARCH_FIELD;

public class StoreNavigationBar extends BaseElement {

    public StoreNavigationBar(By locator, String name) {
        super(locator, name);
    }

    public TextField getSearchField() {
        return new TextField(By.xpath(XPATH_SEARCH_FIELD), "Search field");
    }

    public Button getSearchButton() {
        return new Button(By.xpath(XPATH_BUTTON_SEARCH_FORM), "Search button");
    }
}

package by.a1qa.klimov.pageobjects;

import by.a1qa.klimov.elements.Button;
import by.a1qa.klimov.elements.TextField;
import by.a1qa.klimov.model.BaseElement;
import by.a1qa.klimov.model.BaseForm;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;

@Log4j
public class StoreNavigationBar extends BaseForm {
    public static final String XPATH_SEARCH_FIELD = "//div[@class='searchbox']//input";
    public static final String XPATH_BUTTON_SEARCH_FORM = "//*[@id='store_search_link']//img";

    private TextField searchField;
    private Button searchButton;

    public StoreNavigationBar(BaseElement baseElement) {
        super(baseElement);
    }

    public void getSearchFieldAndInsertText(String text) {
        getSearchField().sendText(text);
    }

    public void getSearchButtonAndClick() {
        getSearchButton().click();
    }

    private TextField getSearchField() {
        return searchField == null ?
                searchField = new TextField(By.xpath(XPATH_SEARCH_FIELD), "Search field") : searchField;
    }

    private Button getSearchButton() {
        return searchButton == null ?
                searchButton = new Button(By.xpath(XPATH_BUTTON_SEARCH_FORM), "Search button") : searchButton;
    }
}

package by.a1qa.klimov.pageobjects;

import by.a1qa.klimov.elements.Button;
import by.a1qa.klimov.elements.TextField;
import by.a1qa.klimov.model.BaseElement;
import by.a1qa.klimov.model.BaseForm;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;

import static by.a1qa.klimov.utils.Constants.XPATH_BUTTON_SEARCH_FORM;
import static by.a1qa.klimov.utils.Constants.XPATH_SEARCH_FIELD;

@Log4j
public class StoreNavigationBar extends BaseForm {
    private TextField searchField;
    private Button searchButton;

    public StoreNavigationBar(BaseElement baseElement) {
        super(baseElement);
    }

    public void getSearchFieldAndInsertText(String text) {
        getSearchField().sendText(text);
    }

    public void getSearchButtonAndClick() {
        getSearchField().click();
    }

    private TextField getSearchField() {
        return searchField == null ?
                new TextField(By.xpath(XPATH_SEARCH_FIELD), "Search field") : searchField;
    }

    private Button getSearchButton() {
        return searchButton == null ?
                new Button(By.xpath(XPATH_BUTTON_SEARCH_FORM), "Search button") : searchButton;
    }
}

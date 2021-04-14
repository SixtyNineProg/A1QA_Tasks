package by.a1qa.klimov.elements;

import by.a1qa.klimov.model.BaseElement;
import by.a1qa.klimov.model.BaseForm;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;

import static by.a1qa.klimov.utils.Constants.XPATH_BUTTON_SEARCH_FORM;
import static by.a1qa.klimov.utils.Constants.XPATH_SEARCH_FIELD;

@Log4j
public class StoreNavigationBar extends BaseForm {

    public StoreNavigationBar(BaseElement baseElement) {
        super(baseElement);
    }

    //TODO реализовать работу с кнопками
    public TextField getSearchField() {
        return new TextField(By.xpath(XPATH_SEARCH_FIELD), "Search field");
    }

    public Button getSearchButton() {
        return new Button(By.xpath(XPATH_BUTTON_SEARCH_FORM), "Search button");
    }
}

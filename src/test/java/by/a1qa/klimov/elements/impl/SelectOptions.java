package by.a1qa.klimov.elements.impl;


import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.Element;
import by.a1qa.klimov.elements.interfaces.ISelectOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.function.Supplier;

public class SelectOptions extends Element implements ISelectOptions {

    public Select select = new Select(doWithRetry((Supplier<RemoteWebElement>) this::getElement));

    public SelectOptions(final By locator, String name, ElementState state) {
        super(locator, name, state);
    }

    @Override
    protected String getElementType() {
        return getLocalizationManager().getLocalizedMessage("loc.selectOptions");
    }

    @Override
    public void selectByIndex(int index) {
        select.selectByIndex(index);
    }
}

package by.a1qa.klimov.pageobjects;

import by.a1qa.klimov.elements.Button;
import by.a1qa.klimov.elements.Href;
import by.a1qa.klimov.elements.Label;
import by.a1qa.klimov.model.BaseForm;
import org.openqa.selenium.By;

public class WindowsMainPage extends BaseForm {
    private static final String XPATH_HREF_NEW_WINDOW = "//a[contains(@href,'windows')]";

    private Href hrefNewWindow;

    public WindowsMainPage(By locator, String name) {
        super(new Href(locator, name));
    }

    public boolean atPage() {
        return isOpened();
    }

    public void hrefNewWindowClick() {
        getHrefNewWindow().click();
    }

    private Href getHrefNewWindow() {
        return hrefNewWindow == null ?
                hrefNewWindow = new Href(By.xpath(XPATH_HREF_NEW_WINDOW), "Href new window") :
                hrefNewWindow;
    }
}

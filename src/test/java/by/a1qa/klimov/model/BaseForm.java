package by.a1qa.klimov.model;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

public abstract class BaseForm {
    private BaseElement baseElement;

    public BaseForm(BaseElement baseElement) {
        this.baseElement = baseElement;
    }

    public boolean baseElementIsDisplayed() {
        try {
            waitForOpen();
            return baseElement.isDisplayed();
        } catch (NoSuchElementException|TimeoutException e) {
            return false;
        }
    }

    public void waitForOpen() {
        baseElement.waitForOpen();
    }
}

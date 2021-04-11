package by.a1qa.klimov.model;

import org.openqa.selenium.NoSuchElementException;

public abstract class BaseForm {
    private BaseElement baseElement;

    public BaseForm(BaseElement baseElement) {
        this.baseElement = baseElement;
    }

    public boolean baseElementIsDisplayed() {
        waitForOpen();
        try {
            return baseElement.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void waitForOpen() {
        baseElement.waitForOpen();
    }
}

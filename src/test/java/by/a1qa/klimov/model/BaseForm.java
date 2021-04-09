package by.a1qa.klimov.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class BaseForm {
    BaseElement baseElement;
    String name;

    boolean isDisplayed() {
        return baseElement.isDisplayed();
    }

    void waitForOpen() {
        baseElement.waitForOpen();
    }
}

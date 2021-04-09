package by.a1qa.klimov.model;

import lombok.AllArgsConstructor;
import org.openqa.selenium.By;

@AllArgsConstructor
public abstract class BaseElement {
    By locator;
    String name;

    boolean isDisplayed() {
        return true;
    }

    void waitForOpen() {

    }
}

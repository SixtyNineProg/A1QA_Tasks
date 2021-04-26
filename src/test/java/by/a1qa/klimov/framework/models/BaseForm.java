package by.a1qa.klimov.framework.models;

import lombok.extern.log4j.Log4j;

@Log4j
public abstract class BaseForm {
    private BaseElement baseElement;

    public BaseForm(BaseElement baseElement) {
        this.baseElement = baseElement;
    }

    public boolean isOpened() {
        log.info("Is opened.");
        return baseElement.isDisplayed();
    }
}

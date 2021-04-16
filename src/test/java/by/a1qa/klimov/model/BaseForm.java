package by.a1qa.klimov.model;

public abstract class BaseForm {
    private BaseElement baseElement;

    public BaseForm(BaseElement baseElement) {
        this.baseElement = baseElement;
    }

    public boolean isOpened() {
        return baseElement.isDisplayed();
    }
}

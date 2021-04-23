package by.a1qa.klimov.pageobjects;

import by.a1qa.klimov.framework.elements.Label;
import by.a1qa.klimov.framework.elements.Slider;
import by.a1qa.klimov.framework.models.BaseForm;
import org.openqa.selenium.By;

public class HorizontalSliderPage extends BaseForm {
    private static final String XPATH_SLIDER = "//div[@class='sliderContainer']//input[@type='range']";
    private static final String XPATH_SLIDER_VALUE = "//span[@id='range']";

    private Slider slider = new Slider(By.xpath(XPATH_SLIDER), "Slider");
    private Label labelSliderValue = new Label(By.xpath(XPATH_SLIDER_VALUE), "Slider value");

    public HorizontalSliderPage(By locator, String name) {
        super(new Label(locator, name));
    }

    public boolean atPage() {
        return isOpened();
    }

    public double moveSliderInRandomPlace() {
        return slider.moveSliderInRandomPlace();
    }

    public String getSliderValue() {
        return labelSliderValue.getText();
    }

}

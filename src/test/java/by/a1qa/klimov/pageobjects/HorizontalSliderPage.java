package by.a1qa.klimov.pageobjects;

import by.a1qa.klimov.elements.Label;
import by.a1qa.klimov.elements.Slider;
import by.a1qa.klimov.model.BaseForm;
import org.openqa.selenium.By;

public class HorizontalSliderPage extends BaseForm {
    private static final String XPATH_SLIDER = "//div[@class='sliderContainer']//input[@type='range']";
    private static final String XPATH_SLIDER_VALUE = "//span[@id='range']";

    private Slider slider;
    private Label labelSliderValue;

    public HorizontalSliderPage(By locator, String name) {
        super(new Label(locator, name));
    }

    public void moveToSlider() {
        getSlider().moveToElement();
    }

    public void sliderClick() {
        getSlider().click();
    }

    public boolean atPage() {
        return isOpened();
    }

    public String getSliderValue() {
        return getLabelSliderValue().getText();
    }

    private Slider getSlider() {
        return slider == null ?
                slider = new Slider(By.xpath(XPATH_SLIDER), "Slider") :
                slider;
    }

    private Label getLabelSliderValue() {
        return labelSliderValue == null ?
                labelSliderValue = new Label(By.xpath(XPATH_SLIDER_VALUE), "Slider value") :
                labelSliderValue;
    }

}

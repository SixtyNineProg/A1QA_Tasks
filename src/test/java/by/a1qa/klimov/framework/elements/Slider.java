package by.a1qa.klimov.framework.elements;

import by.a1qa.klimov.framework.models.BaseElement;
import by.a1qa.klimov.framework.utils.Randomizer;
import by.a1qa.klimov.framework.webdriversetting.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

public class Slider extends BaseElement {
    private static final String ATTRIBUTE_MAX_VALUE = "max";
    private static final String ATTRIBUTE_STEP_VALUER = "step";

    private Actions actions = new Actions(WebDriverSingleton.getWebDriver());

    public Slider(By locator, String name) {
        super(locator, name);
    }

    public double moveSliderInRandomPlace() {
        actions.moveToElement(findElement()).moveByOffset(-(getWight() / 2), 0).click().build().perform();

        double maxValue = Double.parseDouble(findElement().getAttribute(ATTRIBUTE_MAX_VALUE));
        double stepValue = Double.parseDouble(findElement().getAttribute(ATTRIBUTE_STEP_VALUER));
        int maxNumSteps = (int) (maxValue / stepValue);

        int numSteps = Randomizer.generateRandomNumFromRange(1, maxNumSteps - 1);
        for (int i = 0; i < numSteps; i++)
            findElement().sendKeys(Keys.ARROW_RIGHT);

        return numSteps * stepValue;
    }
}

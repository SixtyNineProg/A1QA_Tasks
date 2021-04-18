package by.a1qa.klimov.framework.action;

import by.a1qa.klimov.framework.webdriversetting.WebDriverSingleton;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

@Log4j
public class ActionsSingleton {
    public static final String GET_ACTIONS = "Get actions.";

    private static WebDriver driver = WebDriverSingleton.getWebDriver();
    private static Actions action = null;

    public static Actions getActions() {
        log.info(GET_ACTIONS);
        return action == null ?
                action = new Actions(driver) :
                action;
    }
}

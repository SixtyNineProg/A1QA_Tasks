package by.a1qa.klimov.framework.webdriversetting;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebDriver;

import static by.a1qa.klimov.framework.utils.Constants.GET_WEBDRIVER;

@Log4j
public class WebDriverSingleton {

    private static WebDriver driver = null;

    public static WebDriver getWebDriver() {
        log.info(GET_WEBDRIVER);
        if (driver == null)
            driver = WebDriverFactory.getWebDriver();
        return driver;
    }

}

package by.a1qa.klimov.framework.webdriversetting;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebDriver;

@Log4j
public class WebDriverSingleton {
    private static final String GET_WEBDRIVER = "Get webdriver.";

    private static WebDriver driver = null;

    public static WebDriver getWebDriver() {
        log.info(GET_WEBDRIVER);
        if (driver == null)
            driver = WebDriverFactory.getWebDriver();
        return driver;
    }

    public static WebDriver reopenSession() {
        driver = WebDriverFactory.getWebDriver();
        return driver;
    }

}

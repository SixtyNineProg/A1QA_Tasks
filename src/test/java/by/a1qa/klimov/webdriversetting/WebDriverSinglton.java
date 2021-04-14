package by.a1qa.klimov.webdriversetting;

import org.openqa.selenium.WebDriver;

public class WebDriverSinglton {

    private static WebDriver driver = null;

    public static WebDriver getWebDriver() {
        if (driver == null)
            driver = WebDriverFactory.getWebDriver();
        return driver;
    }

}

package by.a1qa.klimov.pageobjects;

import by.a1qa.klimov.webdriversetting.WebDriverSinglton;
import org.openqa.selenium.WebDriver;

public class BrowserActions {
    private static WebDriver driver = WebDriverSinglton.getWebDriver();

    public static void openUrl(String url) {
        driver.get(url);
    }

    public static void refresh() {
        driver.navigate().refresh();
    }
}

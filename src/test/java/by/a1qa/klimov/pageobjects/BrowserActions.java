package by.a1qa.klimov.pageobjects;

import by.a1qa.klimov.webdriversetting.WebDriverSinglton;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebDriver;

import static by.a1qa.klimov.utils.Constants.GET_PAGE_WITH_URL;
import static by.a1qa.klimov.utils.Constants.GET_REFRESH_PAGE;

@Log4j
public class BrowserActions {
    private static WebDriver driver = WebDriverSinglton.getWebDriver();

    public static void openUrl(String url) {
        log.info(GET_PAGE_WITH_URL + url);
        driver.get(url);
    }

    public static void refresh() {
        log.info(GET_REFRESH_PAGE);
        driver.navigate().refresh();
    }
}

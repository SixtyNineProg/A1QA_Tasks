package by.a1qa.klimov.framework.browser;

import by.a1qa.klimov.framework.webdriversetting.WebDriverSingleton;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

@Log4j
public class BrowserActions {
    public static final String GET_PAGE_WITH_URL = "Get page with URL: ";
    public static final String GET_REFRESH_PAGE = "Refresh page.";

    public static void openUrl(String url) {
        log.info(GET_PAGE_WITH_URL + url);
        WebDriverSingleton.getWebDriver().get(url);
    }

    public static void refresh() {
        log.info(GET_REFRESH_PAGE);
        WebDriverSingleton.getWebDriver().navigate().refresh();
    }

    public static List<String> getBrowserWindowHandles() {
        try {
            return new ArrayList<>(WebDriverSingleton.getWebDriver().getWindowHandles());
        } catch (NoSuchSessionException e) {
            return new ArrayList<>();
        }
    }

    public static void switchWindow(String windowHandle) {
        WebDriverSingleton.getWebDriver().switchTo().window(windowHandle);
    }

    public static String getCurrentWindowTitle() {
        return WebDriverSingleton.getWebDriver().getTitle();
    }

    public static void closeCurrentWindow() {
        WebDriverSingleton.getWebDriver().close();
    }

    public static String getCurrentWindowHandle() {
        return WebDriverSingleton.getWebDriver().getWindowHandle();
    }

    public static boolean windowIsPresent(String windowHandle) {
        return getBrowserWindowHandles().stream().anyMatch(windowHandle::equals);
    }

    public static void navigateBack() {
        WebDriverSingleton.getWebDriver().navigate().back();
    }

    public static void switchToDefaultContent() {
        WebDriverSingleton.getWebDriver().switchTo().defaultContent();
    }

    public static void switchToFrame(By locator) {
        WebDriverSingleton.getWebDriver().switchTo().frame(WebDriverSingleton.getWebDriver().findElement(locator));
    }

    public static String getCurrentUrl() {
        return WebDriverSingleton.getWebDriver().getCurrentUrl();
    }
}

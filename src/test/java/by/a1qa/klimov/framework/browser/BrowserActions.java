package by.a1qa.klimov.framework.browser;

import by.a1qa.klimov.framework.webdriversetting.WebDriverSingleton;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchSessionException;

import java.util.ArrayList;
import java.util.List;

@Log4j
public class BrowserActions {

    public static void openUrl(String url) {
        log.info("Get page with URL: " + url);
        WebDriverSingleton.getWebDriver().get(url);
    }

    public static List<String> getBrowserWindowHandles() {
        log.info("Get browser window handles.");
        try {
            return new ArrayList<>(WebDriverSingleton.getWebDriver().getWindowHandles());
        } catch (NoSuchSessionException e) {
            return new ArrayList<>();
        }
    }

    public static void switchWindow(String windowHandle) {
        log.info("Switch window. v" + windowHandle);
        WebDriverSingleton.getWebDriver().switchTo().window(windowHandle);
    }

    public static String getCurrentWindowTitle() {
        log.info("Get current window title.");
        return WebDriverSingleton.getWebDriver().getTitle();
    }

    public static void closeCurrentWindow() {
        log.info("Close current window.");
        WebDriverSingleton.getWebDriver().close();
    }

    public static String getCurrentWindowHandle() {
        log.info("Get current window handle.");
        return WebDriverSingleton.getWebDriver().getWindowHandle();
    }

    public static boolean windowIsPresent(String windowHandle) {
        log.info("Window is present. Window handle: " + windowHandle);
        return getBrowserWindowHandles().stream().anyMatch(windowHandle::equals);
    }

    public static void navigateBack() {
        log.info("Navigate back.");
        WebDriverSingleton.getWebDriver().navigate().back();
    }

    public static void switchToDefaultContent() {
        log.info("Switch to default content.");
        WebDriverSingleton.getWebDriver().switchTo().defaultContent();
    }

    public static void switchToFrame(String locator) {
        log.info("Switch to frame by locator: " + locator);
        WebDriverSingleton.getWebDriver().switchTo().frame(WebDriverSingleton.getWebDriver()
                .findElement(By.xpath(locator)));
    }

    public static String getCurrentUrl() {
        log.info("Get current URL.");
        return WebDriverSingleton.getWebDriver().getCurrentUrl();
    }
}

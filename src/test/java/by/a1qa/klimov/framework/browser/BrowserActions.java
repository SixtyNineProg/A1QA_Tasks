package by.a1qa.klimov.framework.browser;

import by.a1qa.klimov.framework.webdriversetting.WebDriverSinglton;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

import static by.a1qa.klimov.framework.utils.Constants.GET_PAGE_WITH_URL;
import static by.a1qa.klimov.framework.utils.Constants.GET_REFRESH_PAGE;

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

    public static List<String> getBrowserWindowHandles() {
        try {
            return new ArrayList<>(driver.getWindowHandles());
        } catch (NoSuchSessionException e) {
            return new ArrayList<>();
        }
    }

    public static void switchWindow(String windowHandle) {
        driver.switchTo().window(windowHandle);
    }

    public static String getCurrentWindowTitle() {
        return driver.getTitle();
    }

    public static void closeCurrentWindow() {
        driver.close();
    }

    public static String getCurrentWindowHandle() {
        return driver.getWindowHandle();
    }

    public static boolean windowIsPresent(String windowHandle) {
        return getBrowserWindowHandles().stream().anyMatch(windowHandle::equals);
    }

    public static void navigateBack() {
        driver.navigate().back();
    }

    public static void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    public static void switchToFrame(By locator) {
        driver.switchTo().frame(driver.findElement(locator));
    }
}

package by.a1qa.klimov.framework.browser;

import by.a1qa.klimov.framework.property.ConfigurationProperties;
import by.a1qa.klimov.framework.webdriversetting.WebDriverSingleton;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Log4j
public class Alerts {

    private static Alert alert;

    public static void acceptAlert() {
        log.info("Alert accept.");
        getAlert().accept();
    }

    public static String alertGetText() {
        log.info("Alert get text.");
        return getAlert().getText();
    }

    public static void alertSendText(String text) {
        log.info("Alert send text: " + text);
        getAlert().sendKeys(text);
    }

    public static boolean alertIsClose() {
        log.info("Alert is close.");
        try {
            getAlert().getText();
            return false;
        } catch (NoAlertPresentException e) {
            return true;
        }
    }

    private static Alert getAlert() {
        log.info("Get alert.");
        return alert == null ?
                alert = new WebDriverWait(WebDriverSingleton.getWebDriver(), Long.parseLong(
                        ConfigurationProperties.getConfigurationPropertyByKey("waitLoadingPageSeconds")))
                        .until(ExpectedConditions.alertIsPresent()) :
                alert;
    }
}

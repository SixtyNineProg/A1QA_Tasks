package by.a1qa.klimov.pageobjects;

import by.a1qa.klimov.property.ConfigurationProperties;
import by.a1qa.klimov.webdriversetting.WebDriverSinglton;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Alerts {
    private static WebDriver driver = WebDriverSinglton.getWebDriver();
    private static Alert alert;

    public static void acceptAlert() {
        getAlert().accept();
    }

    public static String alertGetText() {
        return getAlert().getText();
    }

    public static void alertSendText(String text) {
        getAlert().sendKeys(text);
    }

    public static boolean alertIsClose(){
        try {
            getAlert().getText();
            return false;
        } catch (NoAlertPresentException e) {
            return true;
        }
    }

    private static Alert getAlert() {
        return alert == null ?
                alert = new WebDriverWait(driver, Long.parseLong(
                        ConfigurationProperties.getConfigurationPropertyByKey("waitLoadingPageSeconds")))
                        .until(ExpectedConditions.alertIsPresent()) :
                alert;
    }
}

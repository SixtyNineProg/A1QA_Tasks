package by.a1qa.klimov.tests;

import by.a1qa.klimov.pageobjects.*;
import by.a1qa.klimov.property.DataProperties;
import by.a1qa.klimov.tests.testsettings.TestSettings;
import by.a1qa.klimov.utils.Randomizer;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

@Log4j
public class TheInternetHerokuAppCom extends TestSettings {

    private static final String XPATH_AUTH_PAGE_ELEMENT = "//*[contains(text(),'Basic Auth')]//parent::div";
    private static final String PART_URL = "://";

    private static final String XPATH_ALERT_PAGE_ELEMENT = "//button[contains(@onclick,'jsAlert')]";
    private static final String XPATH_SLIDER_PAGE_ELEMENT = "//div[@class='sliderContainer']//span[@id='range']";
    private static final String XPATH_HOVER_PAGE_ELEMENT = "//div[@class='figure']//div[@class='figcaption']";
    private static final String XPATH_WINDOWS_PAGE_ELEMENT = "//a[contains(@href,'windows')]";
    private static final String XPATH_TEXT_NEW_WINDOW = "//div[@class='example']//*[contains(text(),'New Window')]";

    @Test
    public void basicAuth() {
        String user = DataProperties.getDataPropertyByKey("basicAuthUser");
        String password = DataProperties.getDataPropertyByKey("basicAuthPassword");
        String url = DataProperties.getDataPropertyByKey("internetHerokuAppAuthUrl");
        int insertIndex = url.indexOf(PART_URL) + PART_URL.length();
        String urlWithCred = url.substring(0, insertIndex)
                + user + ":" + password + "@"
                + url.substring(insertIndex);
        BrowserActions.openUrl(urlWithCred);
        ResultAuthPage resultAuthPage = new ResultAuthPage(By.xpath(XPATH_AUTH_PAGE_ELEMENT),
                "Auth page element.");
        Assert.assertEquals(resultAuthPage.getTextAfterAuth(),
                "Congratulations! You must have the proper credentials.",
                "Authentication error.");
    }

    @Test
    public void javascriptAlerts() {
        BrowserActions.openUrl(DataProperties.getDataPropertyByKey("internetHerokuAppAlertUrl"));
        AlertsMainPage alertsMainPage = new AlertsMainPage(By.xpath(XPATH_ALERT_PAGE_ELEMENT),
                "Auth page element.");
        alertsMainPage.buttonAlertClick();
        Assert.assertEquals(Alerts.alertGetText(), "I am a JS Alert",
                "Confirmation text does not match");
        Alerts.acceptAlert();
        Assert.assertTrue(Alerts.alertIsClose(), "Alert is not closed.");
        Assert.assertEquals(alertsMainPage.getTextResult(), "You successfully clicked an alert",
                "Alert was not pressed");
        alertsMainPage.buttonConfirmClick();
        Assert.assertEquals(Alerts.alertGetText(), "I am a JS Confirm",
                "Confirmation text does not match");
        Alerts.acceptAlert();
        Assert.assertTrue(Alerts.alertIsClose(), "Alert is not closed.");
        Assert.assertEquals(alertsMainPage.getTextResult(), "You clicked: Ok",
                "Alert was not pressed");
        alertsMainPage.buttonPromptClick();
        String randomText = Randomizer.generateRandomText(
                Integer.parseInt(DataProperties.getDataPropertyByKey("randomTextLength")));
        Alerts.alertSendText(randomText);
        Alerts.acceptAlert();
        Assert.assertTrue(Alerts.alertIsClose(), "Alert is not closed.");
        Assert.assertEquals(alertsMainPage.getTextResult(), "You entered: " + randomText,
                "Alert was not pressed");
    }

    @Test
    public void horizontalSlider() {
        BrowserActions.openUrl(DataProperties.getDataPropertyByKey("internetHerokuAppSliderUrl"));
        HorizontalSliderPage horizontalSliderPage = new HorizontalSliderPage(
                By.xpath(XPATH_SLIDER_PAGE_ELEMENT), "Slider range label");
        Assert.assertTrue(horizontalSliderPage.atPage());
        horizontalSliderPage.moveToSlider();
        horizontalSliderPage.sliderClick();
        Assert.assertEquals(horizontalSliderPage.getSliderValue(), "2.5", "Incorrect slider value");
    }

    @Test
    public void hovers() {
        BrowserActions.openUrl(DataProperties.getDataPropertyByKey("internetHerokuAppHoverUrl"));
        HoverMainPage hoverMainPage = new HoverMainPage(By.xpath(XPATH_HOVER_PAGE_ELEMENT), "Hover label");
        Assert.assertTrue(hoverMainPage.atPage());
    }

    @Test
    public void windows() {
        BrowserActions.openUrl(DataProperties.getDataPropertyByKey("internetHerokuAppWindowsUrl"));
        WindowsMainPage windowsMainPage = new WindowsMainPage(
                By.xpath(XPATH_WINDOWS_PAGE_ELEMENT), "Window href");
        Assert.assertTrue(windowsMainPage.atPage());
        int numTabsBeforeOpenNew = BrowserActions.getBrowserTabs().size();
        windowsMainPage.hrefNewWindowClick();
        int numTabsAfterOpenNew = BrowserActions.getBrowserTabs().size();
        Assert.assertTrue(numTabsAfterOpenNew > numTabsBeforeOpenNew, "Tab is not open");
        NewTabPage newTabPage = new NewTabPage(By.xpath(XPATH_TEXT_NEW_WINDOW), "Label new window");
        BrowserActions.switchTab(BrowserActions.getBrowserTabs().get(1));
        Assert.assertEquals(newTabPage.getTextNewWindow(), "New Window",
                "The text on the new tab is incorrect");
        Assert.assertEquals(BrowserActions.getCurrentTabName(), "New Window",
                "The name of the new tab is incorrect");

        BrowserActions.switchTab(BrowserActions.getBrowserTabs().get(0));
        Assert.assertTrue(windowsMainPage.atPage());
        numTabsBeforeOpenNew = BrowserActions.getBrowserTabs().size();
        windowsMainPage.hrefNewWindowClick();
        numTabsAfterOpenNew = BrowserActions.getBrowserTabs().size();
        Assert.assertTrue(numTabsAfterOpenNew > numTabsBeforeOpenNew, "Tab is not open");
        newTabPage = new NewTabPage(By.xpath(XPATH_TEXT_NEW_WINDOW), "Label new window");
        BrowserActions.switchTab(BrowserActions.getBrowserTabs().get(1));
        Assert.assertEquals(newTabPage.getTextNewWindow(), "New Window",
                "The text on the new tab is incorrect");
        Assert.assertEquals(BrowserActions.getCurrentTabName(), "New Window",
                "The name of the new tab is incorrect");
    }
}

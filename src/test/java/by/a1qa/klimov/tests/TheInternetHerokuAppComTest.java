package by.a1qa.klimov.tests;

import by.a1qa.klimov.framework.browser.Alerts;
import by.a1qa.klimov.framework.browser.BrowserActions;
import by.a1qa.klimov.framework.property.ConfigurationProperties;
import by.a1qa.klimov.framework.property.DataProperties;
import by.a1qa.klimov.framework.testsettings.TestSettings;
import by.a1qa.klimov.framework.utils.Comparator;
import by.a1qa.klimov.framework.utils.Randomizer;
import by.a1qa.klimov.pageobjects.*;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@Log4j
public class TheInternetHerokuAppComTest extends TestSettings {

    private static final String XPATH_AUTH_PAGE_ELEMENT = "//*[contains(text(),'Basic Auth')]//parent::div";
    private static final String PART_URL = "://";

    private static final String XPATH_ALERT_PAGE_ELEMENT = "//button[contains(@onclick,'jsAlert')]";
    private static final String XPATH_SLIDER_PAGE_ELEMENT = "//div[@class='sliderContainer']//span[@id='range']";
    private static final String XPATH_HOVER_PAGE_ELEMENT = "//div[@class='figure']//img[@alt='User Avatar']";
    private static final String XPATH_WINDOWS_PAGE_ELEMENT = "//a[contains(@href,'windows')]";
    private static final String XPATH_TEXT_NEW_WINDOW = "//div[@class='example']//*[contains(text(),'New Window')]";
    private static final String XPATH_USER_PAGE = "//*[contains(text(),'user1')]";
    private final static String XPATH_IFRAME_ELEMENT = "//div[@class='tox-editor-container']//iframe";

    @Test
    public void basicAuth() {
        String user = DataProperties.getDataPropertyByKey("basicAuthUser");
        String password = DataProperties.getDataPropertyByKey("basicAuthPassword");
        String url = ConfigurationProperties.getConfigurationPropertyByKey("internetHerokuAppAuthUrl");
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
        BrowserActions.openUrl(ConfigurationProperties.getConfigurationPropertyByKey("internetHerokuAppAlertUrl"));
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
        BrowserActions.openUrl(ConfigurationProperties.getConfigurationPropertyByKey("internetHerokuAppSliderUrl"));
        HorizontalSliderPage horizontalSliderPage = new HorizontalSliderPage(
                By.xpath(XPATH_SLIDER_PAGE_ELEMENT), "Slider range label");
        Assert.assertTrue(horizontalSliderPage.atPage());
        horizontalSliderPage.moveToStartSlider();
        //horizontalSliderPage.sliderClick();
        Assert.assertEquals(horizontalSliderPage.getSliderValue(), "2.5", "Incorrect slider value");
    }

    @Test
    public void hovers() {
        BrowserActions.openUrl(ConfigurationProperties.getConfigurationPropertyByKey("internetHerokuAppHoverUrl"));
        HoverMainPage hoverMainPage = new HoverMainPage(By.xpath(XPATH_HOVER_PAGE_ELEMENT), "Hover label");
        Assert.assertTrue(hoverMainPage.atPage());

        String userName = DataProperties.getDataPropertyByKey("userName1");
        testUserInHoverPage(userName, hoverMainPage);
        userName = DataProperties.getDataPropertyByKey("userName2");
        testUserInHoverPage(userName, hoverMainPage);
        userName = DataProperties.getDataPropertyByKey("userName3");
        testUserInHoverPage(userName, hoverMainPage);
    }

    private void testUserInHoverPage(String userName, HoverMainPage hoverMainPage) {
        hoverMainPage.moveToUserImage(userName);
        String labelName = hoverMainPage.getTextLabelUserName(userName);
        Assert.assertTrue(Comparator.isExistPartsInText(labelName, userName));
        Assert.assertTrue(hoverMainPage.userHrefIsExist(userName));
        String userHref = hoverMainPage.getUserHref(userName);
        BrowserActions.openUrl(userHref);
        UserPage userPage = new UserPage(By.xpath(XPATH_USER_PAGE), "Label with user name");
        Assert.assertTrue(userPage.isOpened());
        BrowserActions.navigateBack();
    }

    @Test
    public void windows() {
        BrowserActions.openUrl(ConfigurationProperties.getConfigurationPropertyByKey("internetHerokuAppWindowsUrl"));
        WindowsMainPage windowsMainPage = new WindowsMainPage(
                By.xpath(XPATH_WINDOWS_PAGE_ELEMENT), "Window href");
        Assert.assertTrue(windowsMainPage.atPage());
        String homeWindowHandle = BrowserActions.getCurrentWindowHandle();

        List<String> tabsBeforeOpenNew = BrowserActions.getBrowserWindowHandles();
        windowsMainPage.hrefNewWindowClick();
        List<String> tabsAfterOpenNew = BrowserActions.getBrowserWindowHandles();
        String stepOneWindowHandle = Comparator.compareListsAndReturnDifferenceElement(
                tabsBeforeOpenNew,
                tabsAfterOpenNew);
        Assert.assertNotNull(stepOneWindowHandle, "Step one. Window handle is not found.");
        BrowserActions.switchWindow(stepOneWindowHandle);

        NewTabPage stepOneTabPage = new NewTabPage(By.xpath(XPATH_TEXT_NEW_WINDOW), "Label new window");
        Assert.assertTrue(stepOneTabPage.isOpened(), "Tab is not open");
        Assert.assertEquals(stepOneTabPage.getTextNewWindow(), "New Window",
                "The text on the new tab is incorrect");
        Assert.assertEquals(BrowserActions.getCurrentWindowTitle(), "New Window",
                "The name of the new tab is incorrect");

        BrowserActions.switchWindow(homeWindowHandle);
        windowsMainPage = new WindowsMainPage(
                By.xpath(XPATH_WINDOWS_PAGE_ELEMENT), "Window href");
        Assert.assertTrue(windowsMainPage.atPage());

        tabsBeforeOpenNew = BrowserActions.getBrowserWindowHandles();
        windowsMainPage.hrefNewWindowClick();
        tabsAfterOpenNew = BrowserActions.getBrowserWindowHandles();
        String stepTwoWindowHandle = Comparator.compareListsAndReturnDifferenceElement(
                tabsBeforeOpenNew,
                tabsAfterOpenNew);
        Assert.assertNotNull(stepTwoWindowHandle, "Step two. Window handle is not found.");
        BrowserActions.switchWindow(stepTwoWindowHandle);

        NewTabPage stepTwoTabPage = new NewTabPage(By.xpath(XPATH_TEXT_NEW_WINDOW), "Label new window");
        Assert.assertTrue(stepTwoTabPage.isOpened(), "Tab is not open");
        Assert.assertEquals(stepTwoTabPage.getTextNewWindow(), "New Window",
                "The text on the new tab is incorrect");
        Assert.assertEquals(BrowserActions.getCurrentWindowTitle(), "New Window",
                "The name of the new tab is incorrect");

        BrowserActions.switchWindow(stepOneWindowHandle);
        BrowserActions.closeCurrentWindow();
        Assert.assertFalse(BrowserActions.windowIsPresent(stepOneWindowHandle));

        BrowserActions.switchWindow(homeWindowHandle);
        BrowserActions.closeCurrentWindow();
        Assert.assertFalse(BrowserActions.windowIsPresent(homeWindowHandle));

        BrowserActions.switchWindow(stepTwoWindowHandle);
        BrowserActions.closeCurrentWindow();
        Assert.assertFalse(BrowserActions.windowIsPresent(stepTwoWindowHandle));
    }

    @Test
    public void iFrames() {
        BrowserActions.openUrl(ConfigurationProperties.getConfigurationPropertyByKey("internetHerokuAppIFramesUrl"));
        FrameMainPage frameMainPage = new FrameMainPage(By.xpath(XPATH_IFRAME_ELEMENT),
                "IFrame page element");
        Assert.assertTrue(frameMainPage.isOpened());
        frameMainPage.frameClearText(XPATH_IFRAME_ELEMENT);
        String randomText = Randomizer.generateRandomText(
                Integer.parseInt(DataProperties.getDataPropertyByKey("randomTextLength")));
        frameMainPage.frameSetText(
                randomText,
                XPATH_IFRAME_ELEMENT);
        String frameText = frameMainPage.frameGetText(XPATH_IFRAME_ELEMENT);
        Assert.assertEquals(randomText, frameText, "The text is not inserted.");
        frameMainPage.highlightFrameText(XPATH_IFRAME_ELEMENT);
        frameMainPage.boldButtonClick();
        Assert.assertTrue(frameMainPage.isBoldFrameText(frameText, XPATH_IFRAME_ELEMENT));
    }
}

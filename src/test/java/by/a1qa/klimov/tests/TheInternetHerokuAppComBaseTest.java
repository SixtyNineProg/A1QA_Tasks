package by.a1qa.klimov.tests;

import by.a1qa.klimov.framework.browser.Alerts;
import by.a1qa.klimov.framework.browser.BrowserActions;
import by.a1qa.klimov.framework.property.ConfigurationProperties;
import by.a1qa.klimov.framework.property.DataProperties;
import by.a1qa.klimov.framework.testsettings.BaseTest;
import by.a1qa.klimov.framework.utils.Comparator;
import by.a1qa.klimov.framework.utils.Randomizer;
import by.a1qa.klimov.pageobjects.*;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

@Log4j
public class TheInternetHerokuAppComBaseTest extends BaseTest {

    @Test
    public void basicAuthorization() {
        String urlWithCred = getUrlWithCred(
                DataProperties.getDataPropertyByKey("basicAuthUser"),
                DataProperties.getDataPropertyByKey("basicAuthPassword"),
                ConfigurationProperties.getConfigurationPropertyByKey("internetHerokuAppAuthUrl"));
        BrowserActions.openUrl(urlWithCred);
        ResultAuthPage resultAuthPage = new ResultAuthPage(By.xpath("//*[contains(text(),'Basic Auth')]//parent::div"),
                "Auth page element.");
        Assert.assertEquals(resultAuthPage.getTextAfterAuth(),
                "Congratulations! You must have the proper credentials.",
                "Authentication error.");
    }

    private String getUrlWithCred(String user, String password, String url) {
        String partUrl = "://";
        int insertIndex = url.indexOf(partUrl) + partUrl.length();
        return url.substring(0, insertIndex)
                + user + ":" + password + "@"
                + url.substring(insertIndex);
    }

    @Test
    public void alerts() {
        BrowserActions.openUrl(ConfigurationProperties.getConfigurationPropertyByKey("internetHerokuAppAlertUrl"));
        AlertsMainPage alertsMainPage = new AlertsMainPage(By.xpath("//button[contains(@onclick,'jsAlert')]"),
                "Auth page element.");
        Assert.assertTrue(alertsMainPage.atPage());

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
    public void actions() {
        BrowserActions.openUrl(ConfigurationProperties.getConfigurationPropertyByKey("internetHerokuAppSliderUrl"));
        HorizontalSliderPage horizontalSliderPage = new HorizontalSliderPage(
                By.xpath("//div[@class='sliderContainer']//span[@id='range']"), "Slider range label");
        Assert.assertTrue(horizontalSliderPage.atPage());
        double shiftValue = horizontalSliderPage.moveSliderInRandomPlace();
        double screenValue = Double.parseDouble(horizontalSliderPage.getSliderValue());
        Assert.assertEquals(screenValue, shiftValue, "Incorrect slider value");
    }

    @Parameters({"userName", "userHref"})
    @Test
    public void hovers(@Optional("user1") String userName,
                       @Optional("http://the-internet.herokuapp.com/users/1") String userHref) {
        BrowserActions.openUrl(ConfigurationProperties.getConfigurationPropertyByKey("internetHerokuAppHoverUrl"));

        HoverMainPage hoverMainPage = new HoverMainPage(By.xpath("//div[@class='figure']//img[@alt='User Avatar']"), "Hover label");
        Assert.assertTrue(hoverMainPage.atPage());

        hoverMainPage.moveToUserImage(userName);
        String labelName = hoverMainPage.getTextLabelUserName(userName);
        Assert.assertTrue(Comparator.isExistPartsInText(labelName, userName));
        Assert.assertTrue(hoverMainPage.isDisplayedUserHref(userName));

        hoverMainPage.userHrefClick(userName);
        Assert.assertEquals(BrowserActions.getCurrentUrl(), userHref);
        BrowserActions.navigateBack();

        Assert.assertTrue(hoverMainPage.atPage());
    }

    @Test
    public void handles() {
        String xpathWindowsPageElement = "//a[contains(@href,'windows')]";
        String xpathTextNewWindow = "//div[@class='example']//*[contains(text(),'New Window')]";

        BrowserActions.openUrl(ConfigurationProperties.getConfigurationPropertyByKey("internetHerokuAppWindowsUrl"));
        WindowsMainPage windowsMainPage = new WindowsMainPage(
                By.xpath(xpathWindowsPageElement), "Window href");
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

        NewTabPage stepOneTabPage = new NewTabPage(By.xpath(xpathTextNewWindow), "Label new window");
        Assert.assertTrue(stepOneTabPage.isOpened(), "Tab is not open");
        Assert.assertEquals(stepOneTabPage.getTextNewWindow(), "New Window",
                "The text on the new tab is incorrect");
        Assert.assertEquals(BrowserActions.getCurrentWindowTitle(), "New Window",
                "The name of the new tab is incorrect");

        BrowserActions.switchWindow(homeWindowHandle);
        windowsMainPage = new WindowsMainPage(
                By.xpath(xpathWindowsPageElement), "Window href");
        Assert.assertTrue(windowsMainPage.atPage());

        tabsBeforeOpenNew = BrowserActions.getBrowserWindowHandles();
        windowsMainPage.hrefNewWindowClick();
        tabsAfterOpenNew = BrowserActions.getBrowserWindowHandles();
        String stepTwoWindowHandle = Comparator.compareListsAndReturnDifferenceElement(
                tabsBeforeOpenNew,
                tabsAfterOpenNew);
        Assert.assertNotNull(stepTwoWindowHandle, "Step two. Window handle is not found.");
        BrowserActions.switchWindow(stepTwoWindowHandle);

        NewTabPage stepTwoTabPage = new NewTabPage(By.xpath(xpathTextNewWindow), "Label new window");
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
    public void iFrame() {
        String xpathIframeElement = "//div[@class='tox-editor-container']//iframe";

        BrowserActions.openUrl(ConfigurationProperties.getConfigurationPropertyByKey("internetHerokuAppIFramesUrl"));
        FrameMainPage frameMainPage = new FrameMainPage(By.xpath(xpathIframeElement),
                "IFrame page element");
        Assert.assertTrue(frameMainPage.isOpened());

        BrowserActions.switchToFrame(xpathIframeElement);

        frameMainPage.frameClearText();
        String randomText = Randomizer.generateRandomText(
                Integer.parseInt(DataProperties.getDataPropertyByKey("randomTextLength")));
        frameMainPage.frameSetText(randomText);
        String frameText = frameMainPage.frameGetText();
        Assert.assertEquals(randomText, frameText, "The text is not inserted.");

        frameMainPage.highlightFrameText();

        BrowserActions.switchToDefaultContent();
        frameMainPage.boldButtonClick();
        BrowserActions.switchToFrame(xpathIframeElement);

        Assert.assertEquals(frameMainPage.getBoldFrameText(), frameText);

        BrowserActions.switchToDefaultContent();
    }
}

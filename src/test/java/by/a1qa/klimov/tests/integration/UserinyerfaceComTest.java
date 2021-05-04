package by.a1qa.klimov.tests.integration;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import by.a1qa.klimov.forms.UserinyerfaceGame1Form;
import by.a1qa.klimov.forms.UserinyerfaceGame2Form;
import by.a1qa.klimov.forms.UserinyerfaceGame3Form;
import by.a1qa.klimov.forms.UserinyerfaceMainForm;
import by.a1qa.klimov.properties.ConfigurationProperties;
import by.a1qa.klimov.tests.BaseTest;
import by.a1qa.klimov.utils.FileUploader;
import lombok.extern.log4j.Log4j;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

@Log4j
public class UserinyerfaceComTest extends BaseTest {

    @Test
    public void textCase1() {
        Browser browser = AqualityServices.getBrowser();
        browser.goTo(ConfigurationProperties.getConfigurationPropertyByKey("userinyerfaceUrl"));
        browser.waitForPageToLoad();

        UserinyerfaceMainForm userinyerfaceMainForm = new UserinyerfaceMainForm();
        Assert.assertTrue(userinyerfaceMainForm.state().waitForDisplayed(), "Main page not open.");
        userinyerfaceMainForm.startLinkClick();

        UserinyerfaceGame1Form userinyerfaceGame1Form = new UserinyerfaceGame1Form();
        Assert.assertTrue(userinyerfaceGame1Form.state().isDisplayed(), "Game1 page not open.");

        userinyerfaceGame1Form.writePassword();
        userinyerfaceGame1Form.writeEmail();
        userinyerfaceGame1Form.writeDomain();
        userinyerfaceGame1Form.chooseDomain();
        userinyerfaceGame1Form.acceptTerms();
        userinyerfaceGame1Form.clickNext();

        UserinyerfaceGame2Form userinyerfaceGame2Form = new UserinyerfaceGame2Form();
        Assert.assertTrue(userinyerfaceGame2Form.state().isDisplayed(), "Game2 page not open.");

        userinyerfaceGame2Form.buttonUploadAvatarClick();
        FileUploader.uploadFileWithRobot(
                new File(ConfigurationProperties.getConfigurationPropertyByKey("pathToUploadFile")).getAbsolutePath());

        userinyerfaceGame2Form.chooseInterests();
        userinyerfaceGame2Form.buttonNextClick();

        UserinyerfaceGame3Form userinyerfaceGame3Form = new UserinyerfaceGame3Form();
        Assert.assertTrue(userinyerfaceGame3Form.state().isDisplayed(), "Game3 page not open.");
    }

    @Test
    public void textCase2() {
        Browser browser = AqualityServices.getBrowser();
        browser.goTo(ConfigurationProperties.getConfigurationPropertyByKey("userinyerfaceUrl"));
        browser.waitForPageToLoad();

        UserinyerfaceMainForm userinyerfaceMainForm = new UserinyerfaceMainForm();
        Assert.assertTrue(userinyerfaceMainForm.state().waitForDisplayed(), "Main page not open.");
        userinyerfaceMainForm.startLinkClick();

        UserinyerfaceGame1Form userinyerfaceGame1Form = new UserinyerfaceGame1Form();
        Assert.assertTrue(userinyerfaceGame1Form.state().isDisplayed(), "Game1 page not open.");

        userinyerfaceGame1Form.buttonSendToBottomClick();
        userinyerfaceGame1Form.waitHideButtonSendToBottom();
        Assert.assertFalse(userinyerfaceGame1Form.buttonSendToBottomIsPresent(), "Help form isn't closed.");
    }

    @Test
    public void textCase3() {
        Browser browser = AqualityServices.getBrowser();
        browser.goTo(ConfigurationProperties.getConfigurationPropertyByKey("userinyerfaceUrl"));
        browser.waitForPageToLoad();

        UserinyerfaceMainForm userinyerfaceMainForm = new UserinyerfaceMainForm();
        Assert.assertTrue(userinyerfaceMainForm.state().waitForDisplayed(), "Main page not open.");
        userinyerfaceMainForm.startLinkClick();

        UserinyerfaceGame1Form userinyerfaceGame1Form = new UserinyerfaceGame1Form();
        Assert.assertTrue(userinyerfaceGame1Form.state().isDisplayed(), "Game1 page not open.");

        userinyerfaceGame1Form.waitDisplayButtonTransparent();
        userinyerfaceGame1Form.buttonTransparentClick();
        Assert.assertFalse(userinyerfaceGame1Form.buttonTransparentIsPresent(), "Cookies form isn't closed.");
    }

    @Test
    public void textCase4() {
        Browser browser = AqualityServices.getBrowser();
        browser.goTo(ConfigurationProperties.getConfigurationPropertyByKey("userinyerfaceUrl"));
        browser.waitForPageToLoad();

        UserinyerfaceMainForm userinyerfaceMainForm = new UserinyerfaceMainForm();
        Assert.assertTrue(userinyerfaceMainForm.state().waitForDisplayed(), "Main page not open.");
        userinyerfaceMainForm.startLinkClick();

        UserinyerfaceGame1Form userinyerfaceGame1Form = new UserinyerfaceGame1Form();
        Assert.assertTrue(userinyerfaceGame1Form.state().isDisplayed(), "Game1 page not open.");

        Assert.assertEquals(userinyerfaceGame1Form.labelTimerGetText(), "00:00:00", "Timer not reset");
    }
}

package by.a1qa.klimov.tests.integration;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import by.a1qa.klimov.forms.LoginForm;
import by.a1qa.klimov.forms.AvatarAndInterestsPage;
import by.a1qa.klimov.forms.PersonalDetailsForm;
import by.a1qa.klimov.forms.WelcomePage;
import by.a1qa.klimov.properties.ConfigurationProperties;
import by.a1qa.klimov.tests.BaseTest;
import by.a1qa.klimov.utils.Robot;
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

        WelcomePage welcomePage = new WelcomePage();
        Assert.assertTrue(welcomePage.state().waitForDisplayed(), "Main page not open.");
        welcomePage.startLinkClick();

        LoginForm loginForm = new LoginForm();
        Assert.assertTrue(loginForm.state().isDisplayed(), "Game1 page not open.");

        loginForm.writePassword();
        loginForm.writeEmail();
        loginForm.writeDomain();
        loginForm.chooseDomain();
        loginForm.acceptTerms();
        loginForm.clickNext();

        AvatarAndInterestsPage avatarAndInterestsPage = new AvatarAndInterestsPage();
        Assert.assertTrue(avatarAndInterestsPage.state().isDisplayed(), "Game2 page not open.");

        avatarAndInterestsPage.buttonUploadAvatarClick();
        Robot.uploadFileWithRobot(
                new File(ConfigurationProperties.getConfigurationPropertyByKey("pathToUploadFile")).getAbsolutePath());

        avatarAndInterestsPage.chooseInterests();
        avatarAndInterestsPage.buttonNextClick();

        PersonalDetailsForm personalDetailsForm = new PersonalDetailsForm();
        Assert.assertTrue(personalDetailsForm.state().isDisplayed(), "Game3 page not open.");
    }

    @Test
    public void textCase2() {
        Browser browser = AqualityServices.getBrowser();
        browser.goTo(ConfigurationProperties.getConfigurationPropertyByKey("userinyerfaceUrl"));
        browser.waitForPageToLoad();

        WelcomePage welcomePage = new WelcomePage();
        Assert.assertTrue(welcomePage.state().waitForDisplayed(), "Main page not open.");
        welcomePage.startLinkClick();

        LoginForm loginForm = new LoginForm();
        Assert.assertTrue(loginForm.state().isDisplayed(), "Game1 page not open.");

        loginForm.buttonSendToBottomClick();
        loginForm.waitHideButtonSendToBottom();
        Assert.assertFalse(loginForm.buttonSendToBottomIsPresent(), "Help form isn't closed.");
    }

    @Test
    public void textCase3() {
        Browser browser = AqualityServices.getBrowser();
        browser.goTo(ConfigurationProperties.getConfigurationPropertyByKey("userinyerfaceUrl"));
        browser.waitForPageToLoad();

        WelcomePage welcomePage = new WelcomePage();
        Assert.assertTrue(welcomePage.state().waitForDisplayed(), "Main page not open.");
        welcomePage.startLinkClick();

        LoginForm loginForm = new LoginForm();
        Assert.assertTrue(loginForm.state().isDisplayed(), "Game1 page not open.");

        loginForm.waitDisplayButtonTransparent();
        loginForm.buttonTransparentClick();
        Assert.assertFalse(loginForm.buttonTransparentIsPresent(), "Cookies form isn't closed.");
    }

    @Test
    public void textCase4() {
        Browser browser = AqualityServices.getBrowser();
        browser.goTo(ConfigurationProperties.getConfigurationPropertyByKey("userinyerfaceUrl"));
        browser.waitForPageToLoad();

        WelcomePage welcomePage = new WelcomePage();
        Assert.assertTrue(welcomePage.state().waitForDisplayed(), "Main page not open.");
        welcomePage.startLinkClick();

        LoginForm loginForm = new LoginForm();
        Assert.assertTrue(loginForm.state().isDisplayed(), "Game1 page not open.");

        Assert.assertEquals(loginForm.labelTimerGetText(), "00:00:00", "Timer not reset");
    }
}

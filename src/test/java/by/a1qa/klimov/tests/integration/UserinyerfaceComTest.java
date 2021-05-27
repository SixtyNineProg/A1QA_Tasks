package by.a1qa.klimov.tests.integration;

import by.a1qa.klimov.forms.AvatarAndInterestsPage;
import by.a1qa.klimov.forms.LoginForm;
import by.a1qa.klimov.forms.PersonalDetailsForm;
import by.a1qa.klimov.forms.WelcomePage;
import by.a1qa.klimov.properties.ConfigurationProperties;
import by.a1qa.klimov.properties.DataProperties;
import by.a1qa.klimov.tests.BaseTest;
import by.a1qa.klimov.utils.Robot;
import lombok.extern.log4j.Log4j;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

@Log4j
public class UserinyerfaceComTest extends BaseTest {

    @Test
    public void testRegistrationForm() {
        WelcomePage welcomePage = new WelcomePage();
        Assert.assertTrue(welcomePage.state().waitForDisplayed(), "Welcome page not open.");
        welcomePage.startLinkClick();

        LoginForm loginForm = new LoginForm();
        Assert.assertTrue(loginForm.state().waitForDisplayed(), "LoginForm page not open.");

        loginForm.fillPassword(
                Integer.parseInt(DataProperties.getDataPropertyByKey("numCapitalLetter")),
                Integer.parseInt(DataProperties.getDataPropertyByKey("numLowerCaseLetter")),
                Integer.parseInt(DataProperties.getDataPropertyByKey("numNumeral")),
                Boolean.parseBoolean(DataProperties.getDataPropertyByKey("atSymbol")));
        loginForm.fillEmail();
        loginForm.fillDomain();
        loginForm.chooseDomain();
        loginForm.acceptTerms();
        loginForm.clickNext();

        AvatarAndInterestsPage avatarAndInterestsPage = new AvatarAndInterestsPage();
        Assert.assertTrue(avatarAndInterestsPage.state().waitForDisplayed(), "AvatarAndInterests page not open.");

        avatarAndInterestsPage.buttonUploadAvatarClick();
        Robot.uploadFileWithRobot(
                new File(ConfigurationProperties.getConfigurationPropertyByKey("pathToUploadFile")).getAbsolutePath());

        avatarAndInterestsPage.chooseInterests(
                Integer.parseInt(DataProperties.getDataPropertyByKey("numInterests"))
        );
        avatarAndInterestsPage.buttonNextClick();

        PersonalDetailsForm personalDetailsForm = new PersonalDetailsForm();
        Assert.assertTrue(personalDetailsForm.state().waitForDisplayed(), "PersonalDetailsForm page not open.");
    }

    @Test
    public void testCloseHelpForm() {
        WelcomePage welcomePage = new WelcomePage();
        Assert.assertTrue(welcomePage.state().waitForDisplayed(), "Welcome page not open.");
        welcomePage.startLinkClick();

        LoginForm loginForm = new LoginForm();
        Assert.assertTrue(loginForm.state().waitForDisplayed(), "LoginForm page not open.");

        loginForm.buttonSendToBottomClick();
        loginForm.waitHideLabelHelpFormTitle();
        Assert.assertFalse(loginForm.labelHelpFormIsPresent(), "Help form isn't closed.");
    }

    @Test
    public void testCloseCookiesForm() {
        WelcomePage welcomePage = new WelcomePage();
        Assert.assertTrue(welcomePage.state().waitForDisplayed(), "Welcome page not open.");
        welcomePage.startLinkClick();

        LoginForm loginForm = new LoginForm();
        Assert.assertTrue(loginForm.state().waitForDisplayed(), "LoginForm page not open.");

        loginForm.waitDisplayButtonTransparent();
        loginForm.buttonTransparentClick();
        Assert.assertFalse(loginForm.buttonTransparentIsPresent(), "Cookies form isn't closed.");
    }

    @Test
    public void testTimerIsReset() {
        WelcomePage welcomePage = new WelcomePage();
        Assert.assertTrue(welcomePage.state().waitForDisplayed(), "Welcome page not open.");
        welcomePage.startLinkClick();

        LoginForm loginForm = new LoginForm();
        Assert.assertTrue(loginForm.state().isDisplayed(), "LoginForm page not open.");

        Assert.assertEquals(
                loginForm.labelTimerGetText(),
                DataProperties.getDataPropertyByKey("expectedTimerValue"),
                "Timer not reset");
    }
}
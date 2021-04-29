package by.a1qa.klimov.tests.integration;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import by.a1qa.klimov.forms.UserinyerfaceGame1Form;
import by.a1qa.klimov.forms.UserinyerfaceMainForm;
import by.a1qa.klimov.properties.ConfigurationProperties;
import by.a1qa.klimov.properties.DataProperties;
import by.a1qa.klimov.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;


public class UserinyerfaceComTest extends BaseTest {

    @Test
    public void textCase1() {
        Browser browser = AqualityServices.getBrowser();
        browser.goTo(ConfigurationProperties.getConfigurationPropertyByKey("userinyerfaceUrl"));
        browser.waitForPageToLoad();

        UserinyerfaceMainForm userinyerfaceMainForm = new UserinyerfaceMainForm();
        Assert.assertTrue(userinyerfaceMainForm.state().waitForDisplayed());

        userinyerfaceMainForm.startLinkClick();

        UserinyerfaceGame1Form userinyerfaceGame1Form = new UserinyerfaceGame1Form();
        Assert.assertTrue(userinyerfaceGame1Form.state().waitForDisplayed());

        userinyerfaceGame1Form.writePassword(DataProperties.getDataPropertyByKey("password"));
        userinyerfaceGame1Form.writeEmail(DataProperties.getDataPropertyByKey("email"));
        userinyerfaceGame1Form.writeDomain(DataProperties.getDataPropertyByKey("domain"));
        userinyerfaceGame1Form.chooseDomain();

    }
}

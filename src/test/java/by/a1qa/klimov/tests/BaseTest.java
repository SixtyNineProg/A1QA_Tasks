package by.a1qa.klimov.tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import by.a1qa.klimov.properties.ConfigurationProperties;
import by.a1qa.klimov.utils.Robot;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    @BeforeMethod
    protected void beforeMethod() {
        AqualityServices.getBrowser().maximize();
        Browser browser = AqualityServices.getBrowser();
        browser.goTo(ConfigurationProperties.getConfigurationPropertyByKey("userinyerfaceUrl"));
        browser.waitForPageToLoad();
        //Robot.loginProxy();
    }

    @AfterMethod
    public void tearDown() {
        if (AqualityServices.isBrowserStarted()) {
            AqualityServices.getBrowser().quit();
        }
    }
}

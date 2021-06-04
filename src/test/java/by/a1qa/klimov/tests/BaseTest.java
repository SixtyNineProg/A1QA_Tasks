package by.a1qa.klimov.tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.logging.Logger;
import by.a1qa.klimov.properties.ConfigurationData;
import jdk.jfr.Threshold;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.Appender;
import org.apache.log4j.Level;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    @BeforeMethod
    protected void beforeMethod() {
        AqualityServices.getBrowser().maximize();
        Browser browser = AqualityServices.getBrowser();
        browser.goTo(ConfigurationData.getConfigurationPropertyByKey("vkUrl"));
        browser.waitForPageToLoad();
    }

    @AfterMethod
    public void tearDown() {
        if (AqualityServices.isBrowserStarted()) {
            AqualityServices.getBrowser().quit();
        }
    }
}

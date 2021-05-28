package by.a1qa.klimov.tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import by.a1qa.klimov.properties.ConfigurationData;
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

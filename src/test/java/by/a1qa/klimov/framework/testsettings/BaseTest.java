package by.a1qa.klimov.framework.testsettings;

import by.a1qa.klimov.framework.property.ConfigurationProperties;
import by.a1qa.klimov.framework.webdriversetting.WebDriverSingleton;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

@Slf4j
public abstract class BaseTest {

    @BeforeMethod
    public void setUp() {
        WebDriverSingleton.reopenSession().manage().timeouts().implicitlyWait(Long.parseLong(
                ConfigurationProperties.getConfigurationPropertyByKey("implicitlyWait")), TimeUnit.SECONDS);
    }

    @AfterMethod
    public void close() {
        if (WebDriverSingleton.getWebDriver() != null)
            WebDriverSingleton.getWebDriver().quit();
    }
}

package by.a1qa.klimov.framework.testsettings;

import by.a1qa.klimov.framework.property.ConfigurationProperties;
import by.a1qa.klimov.framework.webdriversetting.WebDriverSingleton;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

@Slf4j
public class TestSettings {
    private static WebDriver driver;

    public TestSettings() {
        driver = WebDriverSingleton.getWebDriver();
        driver.manage().timeouts().implicitlyWait(Long.parseLong(
                ConfigurationProperties.getConfigurationPropertyByKey("implicitlyWait")), TimeUnit.SECONDS);
    }

    @BeforeTest
    public void setUp() {
    }

    @AfterTest
    public void close() {
        if (driver != null)
            driver.quit();
    }
}

package by.a1qa.klimov.tests.testsettings;

import by.a1qa.klimov.framework.property.ConfigurationProperties;
import by.a1qa.klimov.framework.property.DataProperties;
import by.a1qa.klimov.framework.webdriversetting.WebDriverSinglton;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Slf4j
public class TestSettings {
    private static WebDriver driver;
    protected static Properties configProperties;
    protected static Properties dataProperties;

    public TestSettings() {
        configProperties = ConfigurationProperties.getConfigurationProperties();
        dataProperties = DataProperties.getDataProperties();
        driver = WebDriverSinglton.getWebDriver();
        driver.manage().timeouts().implicitlyWait(Long.parseLong(
                configProperties.getProperty("implicitlyWait")), TimeUnit.SECONDS);
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

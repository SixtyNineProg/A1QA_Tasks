package by.a1qa.klimov.tests;

import by.a1qa.klimov.property.ConfigurationProperties;
import by.a1qa.klimov.property.DataProperties;
import by.a1qa.klimov.webdriversetting.WebDriverSinglton;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.Properties;

@Slf4j
public class TestSettings {
    protected static WebDriver driver;
    protected static Properties configProperties;
    protected static Properties dataProperties;

    public TestSettings() {
        configProperties = ConfigurationProperties.getConfigurationProperties();
        dataProperties = DataProperties.getDataProperties();
        driver = WebDriverSinglton.getWebDriver();
    }

    @BeforeSuite
    public void setUp() {
    }

    @AfterSuite
    public void close() {
        if (driver != null)
            driver.close();
    }
}

package by.a1qa.klimov.webdriversetting;

import by.a1qa.klimov.property.ConfigurationProperties;
import by.a1qa.klimov.property.DataProperties;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.Properties;

@Slf4j
public class WebDriverSettings {
    protected static WebDriver driver;
    protected static Properties configProperties;
    protected static Properties dataProperties;

    public WebDriverSettings() {
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

package by.a1qa.klimov.setting;

import by.a1qa.klimov.property.ConfigurationProperties;
import by.a1qa.klimov.property.DataProperties;
import by.a1qa.klimov.utils.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
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

    @BeforeSuite
    public void setUp() throws Exception {
        configProperties = ConfigurationProperties.getProperties();
        dataProperties = DataProperties.getProperties();

        boolean proxyEnable = Boolean.parseBoolean(configProperties.getProperty("useProxy"));
        String host = configProperties.getProperty("proxyHost");
        String login = configProperties.getProperty("proxyLogin");
        String password = configProperties.getProperty("proxyPassword");
        String port = configProperties.getProperty("proxyPort");
        String browserName = configProperties.getProperty("browserName");
        browserName = browserName.toUpperCase().trim();
        if (proxyEnable) {
            switch (browserName) {
                case "CHROME":
                    WebDriverManager.chromedriver().proxy(login + ":" + password + "@" + host + ":" + port);
                    break;
                case "FIREFOX":
                    WebDriverManager.firefoxdriver().proxy(login + ":" + password + "@" + host + ":" + port);
                    break;
            }
        }
        WebDriverManager.getInstance(DriverManagerType.valueOf(browserName)).setup();
        driver = WebDriverFactory.getWebDriver(browserName);
    }

    @AfterSuite
    public void close() {
        if (driver != null)
            driver.close();
    }
}

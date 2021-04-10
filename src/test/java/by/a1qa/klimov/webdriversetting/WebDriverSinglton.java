package by.a1qa.klimov.webdriversetting;

import by.a1qa.klimov.property.ConfigurationProperties;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;

import java.io.FileNotFoundException;
import java.util.Properties;

public class WebDriverSinglton {

    private static WebDriver driver = null;
    private static Properties configurationProperties;

    public WebDriverSinglton() {
        configurationProperties = ConfigurationProperties.getConfigurationProperties();
    }

    public static WebDriver getWebDriver() {
        if (driver == null) {
            initializeProxy(configurationProperties);
            String browserName = configurationProperties.getProperty("browserName");
            browserName = browserName.toUpperCase().trim();
            io.github.bonigarcia.wdm.WebDriverManager.getInstance(DriverManagerType.valueOf(browserName)).setup();
            driver = WebDriverFactory.getWebDriver(browserName);
        }
        return driver;
    }

    private static void initializeProxy(Properties configProperties) {
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
    }
}

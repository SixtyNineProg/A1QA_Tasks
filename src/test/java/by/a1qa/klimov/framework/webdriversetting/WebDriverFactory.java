package by.a1qa.klimov.framework.webdriversetting;

import by.a1qa.klimov.framework.property.ConfigurationProperties;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@Log4j
public class WebDriverFactory {

    public static WebDriver getWebDriver() {
        initializeProxy();
        String browserName = ConfigurationProperties.getConfigurationPropertyByKey("browserName");
        browserName = browserName.toUpperCase().trim();
        io.github.bonigarcia.wdm.WebDriverManager.getInstance(DriverManagerType.valueOf(browserName)).setup();
        browserName = browserName.toUpperCase().trim();
        switch (browserName) {
            case "CHROME": {
                log.info("Create ChromeDriver");
                return new ChromeDriver();
            }
            case "FIREFOX": {
                log.info("Create FirefoxDriver");
                return new FirefoxDriver();
            }
            default:
                log.error("An error occurred while creating the driver");
                throw new NullPointerException();
        }
    }

    private static void initializeProxy() {
        boolean proxyEnable = Boolean.parseBoolean(ConfigurationProperties.getConfigurationPropertyByKey("useProxy"));
        String host = ConfigurationProperties.getConfigurationPropertyByKey("proxyHost");
        String login = ConfigurationProperties.getConfigurationPropertyByKey("proxyLogin");
        String password = ConfigurationProperties.getConfigurationPropertyByKey("proxyPassword");
        String port = ConfigurationProperties.getConfigurationPropertyByKey("proxyPort");
        String browserName = ConfigurationProperties.getConfigurationPropertyByKey("browserName");
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

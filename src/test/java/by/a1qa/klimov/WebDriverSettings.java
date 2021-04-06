package by.a1qa.klimov;

import by.a1qa.klimov.utils.Constants;
import by.a1qa.klimov.utils.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Slf4j
public class WebDriverSettings {
    protected static WebDriver driver;
    protected static Properties property = new Properties();

    @BeforeSuite
    public void setUp() {
        try (FileInputStream fis = new FileInputStream("./config.properties")) {
            property.load(fis);
        } catch (IOException e) {
            log.error(Constants.PROPERTY_FILE_UPLOAD_ERROR);
            System.exit(0);
        }

        boolean proxyEnable = Boolean.parseBoolean(property.getProperty("useProxy"));
        String host = property.getProperty("proxyHost");
        String login = property.getProperty("proxyLogin");
        String password = property.getProperty("proxyPassword");
        String port = property.getProperty("proxyPort");
        String browserName = property.getProperty("browserName");
        browserName = browserName.toUpperCase().trim();
        if (proxyEnable)
            switch (browserName) {
                case "CHROME":
                    WebDriverManager.chromedriver().proxy(login + ":" + password + "@" + host + ":" + port);
                    break;
                case "FIREFOX":
                    WebDriverManager.firefoxdriver().proxy(login + ":" + password + "@" + host + ":" + port);
                    break;
            }
        WebDriverManager.getInstance(DriverManagerType.valueOf(browserName)).setup();
        driver = WebDriverFactory.getWebDriver(browserName);
        driver.get("https://ru.wikipedia.org/");
    }

    @AfterSuite
    public void close() {
        if (driver != null)
            driver.close();
    }
}

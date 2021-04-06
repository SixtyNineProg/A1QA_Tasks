package by.a1qa.klimov;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import static io.github.bonigarcia.wdm.config.DriverManagerType.CHROME;


public class WebDriverSettings {
    protected static WebDriver driver;

    @BeforeSuite
    public static void setUp() {
        WebDriverManager.chromedriver().proxy("ircfb_klimov:Kda2077@proxy-irc.rw.by:3128");
        WebDriverManager.getInstance(CHROME).setup();
        driver = new ChromeDriver();
        driver.get("https://ru.wikipedia.org/");
    }

    @AfterSuite
    public static void close() {
        if (driver != null)
            driver.close();
    }
}

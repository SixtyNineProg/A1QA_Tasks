package by.a1qa;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static io.github.bonigarcia.wdm.config.DriverManagerType.CHROME;

public class WebDriverSettings {
    protected static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        WebDriverManager.chromedriver().proxy("ircfb_klimov:Kda2077@proxy-irc.rw.by:3128");
        WebDriverManager.getInstance(CHROME).setup();
        driver = new ChromeDriver();
        driver.get("https://ru.wikipedia.org/");
    }

    @AfterClass
    public static void close() {
        if (driver != null)
            driver.close();
    }
}

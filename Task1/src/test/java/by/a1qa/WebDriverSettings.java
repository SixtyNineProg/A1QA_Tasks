package by.a1qa;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverSettings {
    protected static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/Keks/IdeaProjects/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @AfterClass
    public static void close() {
        driver.close();
    }
}

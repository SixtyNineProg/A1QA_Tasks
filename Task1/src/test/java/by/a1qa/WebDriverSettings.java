package by.a1qa;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverSettings {
    protected WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/Keks/IdeaProjects/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @After
    public void close() {
        driver.close();
    }
}

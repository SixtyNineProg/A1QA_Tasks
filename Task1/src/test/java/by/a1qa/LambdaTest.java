package by.a1qa;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

public class LambdaTest extends WebDriverSettings{
    private final static String URL_WIKIPEDIA_RU = "https://ru.wikipedia.org/";
    private final static String URL_WIKIPEDIA_EN = "https://en.wikipedia.org/";
    private final static String EXPECTED_TITLE_WIKIPEDIA_RU = "Добро пожаловать в Википедию,";
    private final static String EXPECTED_TITLE_WIKIPEDIA_EN = "Welcome to Wikipedia,";

    private final static String XPATH_WIKIPEDIA_TITLE_RU = "//div[@class='mw-parser-output']//*[@class='mw-headline']";
    private final static String XPATH_WIKIPEDIA_TITLE_EN = "//div[@class='mw-parser-output']//*[@id='mp-welcome']";

    @Test
    public void getWikipediaTitleRu() {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(URL_WIKIPEDIA_RU);
        String actualTitle = driver.findElement(By.xpath(XPATH_WIKIPEDIA_TITLE_RU)).getText();
        Assert.assertEquals(EXPECTED_TITLE_WIKIPEDIA_RU, actualTitle);
    }

    @Test
    public void getWikipediaTitleEn() {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(URL_WIKIPEDIA_EN);
        String actualTitle = driver.findElement(By.xpath(XPATH_WIKIPEDIA_TITLE_EN)).getText();
        Assert.assertEquals(EXPECTED_TITLE_WIKIPEDIA_EN, actualTitle);
    }
}

package by.a1qa;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

public class WikipediaTest extends WebDriverSettings{
    private final static String URL_WIKIPEDIA_RU = "https://ru.wikipedia.org/";
    private final static String URL_WIKIPEDIA_EN = "https://en.wikipedia.org/";
    private final static String URL_WIKIPEDIA_RU_CRIB = "https://ru.wikipedia.org/wiki/" +
            "%D0%92%D0%B8%D0%BA%D0%B8%D0%BF%D0%B5%D0%B4%D0%B8%D1%8F" +
            ":%D0%A8%D0%BF%D0%B0%D1%80%D0%B3%D0%B0%D0%BB%D0%BA%D0%B0";
    private final static String EXPECTED_TITLE_WIKIPEDIA_RU = "Добро пожаловать в Википедию,";
    private final static String EXPECTED_TITLE_WIKIPEDIA_EN = "Welcome to Wikipedia,";
    private final static String EXPECTED_URL_SAVE_AS_PDF_WIKIPEDIA = "https://ru.wikipedia.org/w/index.php?" +
            "title=%D0%A1%D0%BB%D1%83%D0%B6%D0%B5%D0%B1%D0%BD%D0%B0%D1%8F:DownloadAsPdf&page=" +
            "%D0%97%D0%B0%D0%B3%D0%BB%D0%B0%D0%B2%D0%BD%D0%B0%D1%8F_%D1%81%D1%82%D1%80%D0%B0%D0%BD%D0%B8%D1%86%D0%B0&" +
            "action=show-download-screen";
    private final static String EXPECTED_IMG_FEATURED_ARTICLE = "img";
    private final static String EXPECTED_EXAMPLE_SIMPLE_TEXT = "Редактировать просто — что введёшь, то и увидишь.";
    private final static String EXPECTED_BUTTON_SETTING_LANGUAGE = "uls-settings-trigger";



    private final static String XPATH_WIKIPEDIA_TITLE_RU = "//div[@class='mw-parser-output']//*[@class='mw-headline']";
    private final static String XPATH_WIKIPEDIA_TITLE_EN = "//div[@class='mw-parser-output']//*[@id='mp-welcome']";
    private final static String XPATH_WIKIPEDIA_SAVE_AS_PDF = "//*[@id='coll-download-as-rl']/a";
    private final static String XPATH_WIKIPEDIA_IMG_FEATURED_ARTICLE =
            "//div[@id='main-tfa']//div[@class='floatright']//img";
    private final static String XPATH_WIKIPEDIA_EXAMPLE_SIMPLE_TEXT =
            "//div[@class='mw-parser-output']/table/tbody/tr[4]/td[2]/p/tt";
    private final static String XPATH_WIKIPEDIA_BUTTON_SETTING_LANGUAGE = "//button[@class='uls-settings-trigger']";


    @Test
    public void getWikipediaTitleRu() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(URL_WIKIPEDIA_RU);
        String actualTitle = driver.findElement(By.xpath(XPATH_WIKIPEDIA_TITLE_RU)).getText();
        Assert.assertEquals(EXPECTED_TITLE_WIKIPEDIA_RU, actualTitle);
    }

    @Test
    public void getWikipediaTitleEn() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(URL_WIKIPEDIA_EN);
        String actualTitle = driver.findElement(By.xpath(XPATH_WIKIPEDIA_TITLE_EN)).getText();
        Assert.assertEquals(EXPECTED_TITLE_WIKIPEDIA_EN, actualTitle);
    }

    @Test
    public void getWikipediaUrlSaveAsPdf() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(URL_WIKIPEDIA_RU);
        String actualUrl = driver.findElement(By.xpath(XPATH_WIKIPEDIA_SAVE_AS_PDF)).getAttribute("href");
        Assert.assertEquals(EXPECTED_URL_SAVE_AS_PDF_WIKIPEDIA, actualUrl);
    }

    @Test
    public void getWikipediaImgFeaturedArticle() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(URL_WIKIPEDIA_RU);
        String tagName = driver.findElement(By.xpath(XPATH_WIKIPEDIA_IMG_FEATURED_ARTICLE)).getTagName();
        Assert.assertEquals(EXPECTED_IMG_FEATURED_ARTICLE, tagName);
    }

    @Test
    public void getWikipediaExampleSimpleText() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(URL_WIKIPEDIA_RU_CRIB);
        String text = driver.findElement(By.xpath(XPATH_WIKIPEDIA_EXAMPLE_SIMPLE_TEXT)).getText();
        Assert.assertEquals(EXPECTED_EXAMPLE_SIMPLE_TEXT, text);
    }

    @Test
    public void getWikipediaButtonSettingLanguage() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(URL_WIKIPEDIA_RU_CRIB);
        String attribute = driver.findElement(By.xpath(XPATH_WIKIPEDIA_BUTTON_SETTING_LANGUAGE)).getAttribute("class");
        Assert.assertEquals(EXPECTED_BUTTON_SETTING_LANGUAGE, attribute);
    }
}

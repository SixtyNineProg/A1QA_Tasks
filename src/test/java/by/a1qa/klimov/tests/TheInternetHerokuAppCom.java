package by.a1qa.klimov.tests;

import by.a1qa.klimov.pageobjects.BrowserActions;
import by.a1qa.klimov.pageobjects.ResultAuthPage;
import by.a1qa.klimov.property.DataProperties;
import by.a1qa.klimov.tests.testsettings.TestSettings;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

@Log4j
public class TheInternetHerokuAppCom extends TestSettings {

    private static final String XPATH_AUTH_PAGE_ELEMENT = "//div[@id='content']//div[@class='example']";
    private static final String PART_URL = "://";

    @Test
    public void basicAuth() {
        String user = DataProperties.getDataPropertyByKey("basicAuthUser");
        String password = DataProperties.getDataPropertyByKey("basicAuthPassword");
        String url = DataProperties.getDataPropertyByKey("internetHerokuAppUrl");
        int insertIndex = url.indexOf(PART_URL) + PART_URL.length();
        String urlWithCred = url.substring(0, insertIndex)
                + user + ":" + password + "@"
                + url.substring(insertIndex);
        BrowserActions.openUrl(urlWithCred);
        ResultAuthPage resultAuthPage = new ResultAuthPage(By.xpath(XPATH_AUTH_PAGE_ELEMENT),
                "Auth page element.");
        Assert.assertEquals(resultAuthPage.getTextAfterAuth(),
                "Congratulations! You must have the proper credentials.",
                "Authentication error.");
    }

    @Test
    public void javascriptAlerts() {

    }
}

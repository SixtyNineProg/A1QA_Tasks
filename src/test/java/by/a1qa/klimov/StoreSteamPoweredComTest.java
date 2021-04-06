package by.a1qa.klimov;

import by.a1qa.klimov.pages.HomePage;
import by.a1qa.klimov.utils.WebDriverSettings;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static by.a1qa.klimov.utils.Constants.STEAM_SHOP_URL;
import static by.a1qa.klimov.utils.Constants.XPATH_LOGO_HOLDER;

public class StoreSteamPoweredComTest extends WebDriverSettings {

    @Test
    public void whenOpenUrlThenReceiveStartPageElement() {
        driver.manage().timeouts().implicitlyWait(Long.parseLong(property.getProperty("implicitlyWait")), TimeUnit.SECONDS);
        driver.get(STEAM_SHOP_URL);
        new WebDriverWait(driver, Long.parseLong(property.getProperty("explicitWait")))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH_LOGO_HOLDER)));
        HomePage homePage = new HomePage(driver);

    }
}

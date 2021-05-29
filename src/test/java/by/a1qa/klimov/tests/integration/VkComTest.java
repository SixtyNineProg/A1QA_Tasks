package by.a1qa.klimov.tests.integration;

import by.a1qa.klimov.api.JsonplaceholderApi;
import by.a1qa.klimov.api.VkComApi;
import by.a1qa.klimov.forms.FeedPage;
import by.a1qa.klimov.forms.LoginPage;
import by.a1qa.klimov.forms.UserPage;
import by.a1qa.klimov.forms.VkNavigationBar;
import by.a1qa.klimov.properties.DataProperties;
import by.a1qa.klimov.tests.BaseTest;
import by.a1qa.klimov.utils.Randomizer;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;

public class VkComTest extends BaseTest {
    private final VkComApi vkComApi = new VkComApi();

    @Test
    public void testUserAction() {
        LoginPage loginPage = new LoginPage();
        Assert.assertTrue(loginPage.state().waitForDisplayed(), "Login page not open.");
        loginPage.fillEmail();
        loginPage.fillPassword();
        loginPage.clickLoginButton();

        FeedPage feedPage = new FeedPage();
        Assert.assertTrue(feedPage.state().waitForDisplayed(), "Feed page not open.");

        VkNavigationBar vkNavigationBar = new VkNavigationBar();
        vkNavigationBar.clickMyPageButton();

        UserPage userPage = new UserPage();
        Assert.assertTrue(userPage.state().waitForDisplayed(), "User page not open.");

        String message = Randomizer.generateRandomText(
                Integer.parseInt(DataProperties.getDataPropertyByKey("postLengthText")));
        userPage.createPost(message);
        Integer id = vkComApi.getWallPostId(message, HttpURLConnection.HTTP_OK);
        Assert.assertEquals(id, Integer.getInteger("0"));
    }
}

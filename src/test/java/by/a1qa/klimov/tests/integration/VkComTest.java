package by.a1qa.klimov.tests.integration;

import by.a1qa.klimov.api.VkComApi;
import by.a1qa.klimov.forms.FeedPage;
import by.a1qa.klimov.forms.LoginPage;
import by.a1qa.klimov.forms.UserPage;
import by.a1qa.klimov.forms.VkNavigationBar;
import by.a1qa.klimov.models.PostData;
import by.a1qa.klimov.models.User;
import by.a1qa.klimov.properties.ConfigurationData;
import by.a1qa.klimov.properties.DataProperties;
import by.a1qa.klimov.tests.BaseTest;
import by.a1qa.klimov.utils.Randomizer;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;

public class VkComTest extends BaseTest {
    private final VkComApi vkComApi = new VkComApi();

    @Test
    public void testUserAction() {
        User user = vkComApi.getUser(HttpURLConnection.HTTP_OK);
        Integer userId = user.getId();

        String userHref = ConfigurationData.getConfigurationPropertyByKey("vkUrl") + "id" + userId;

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
                Integer.parseInt(DataProperties.getDataPropertyByKey("postTextLength")));
        Integer postId = vkComApi.createPost(userId, message, HttpURLConnection.HTTP_OK);

        PostData postData = userPage.getPostData(postId, userId);
        Assert.assertEquals(postData.getUserHref(), userHref, "User does not match");
        Assert.assertEquals(postData.getText(), message, "Text does not match");

        String editedMessage = Randomizer.generateRandomText(
                Integer.parseInt(DataProperties.getDataPropertyByKey("postTextLength")));

        Integer editedPostId = vkComApi.editPostMessage(
                userId,
                postId,
                editedMessage,
                DataProperties.getDataPropertyByKey("picturePath"),
                HttpURLConnection.HTTP_OK);
        Assert.assertEquals(editedPostId, postId, "Post isn't edited");

        postData = userPage.getPostData(postId, userId);
        Assert.assertEquals(postData.getText(), editedMessage, "Edited text does not match");

        Assert.assertTrue(
                userPage.isExistPictureOnPost(
                        postId, userId, "/" + DataProperties.getDataPropertyByKey("picturePath")),
                "Picture is not present");
    }

    @Test
    public void requestTest() throws UnirestException {
        vkComApi.uploadPicture(
                "file1",
                ConfigurationData.getConfigurationPropertyByKey("pathToUploadFile"),
                HttpURLConnection.HTTP_OK);
    }
}

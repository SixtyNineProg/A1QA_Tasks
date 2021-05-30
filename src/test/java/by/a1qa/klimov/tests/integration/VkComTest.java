package by.a1qa.klimov.tests.integration;

import by.a1qa.klimov.api.VkComApi;
import by.a1qa.klimov.forms.FeedPage;
import by.a1qa.klimov.forms.LoginPage;
import by.a1qa.klimov.forms.UserPage;
import by.a1qa.klimov.forms.VkNavigationBar;
import by.a1qa.klimov.models.PostData;
import by.a1qa.klimov.models.User;
import by.a1qa.klimov.models.wallpost.Post;
import by.a1qa.klimov.properties.DataProperties;
import by.a1qa.klimov.tests.BaseTest;
import by.a1qa.klimov.utils.Randomizer;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;
import java.util.List;

public class VkComTest extends BaseTest {
    private final VkComApi vkComApi = new VkComApi();

    @Test
    public void testUserAction() {
        User user = vkComApi.getUser(HttpURLConnection.HTTP_OK);
        Integer userId = user.getId();

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

        List<Post> posts = vkComApi.getWallPosts(userId, HttpURLConnection.HTTP_OK);
        Post newPost = Post.getPost(posts, message);
        Assert.assertNotNull(newPost, "Post not created");
        Assert.assertEquals(newPost.getFrom_id(), userId,
                "User does not match");

        PostData lastPostData = userPage.getLastPostData();
        Assert.assertEquals(lastPostData.getUserId(), userId, "User does not match");
        Assert.assertEquals(lastPostData.getText(), message, "Text does not match");
    }
}

package by.a1qa.klimov.tests.integration;

import by.a1qa.klimov.api.ServiceAlbum;
import by.a1qa.klimov.api.TypeObject;
import by.a1qa.klimov.api.VkComApi;
import by.a1qa.klimov.forms.FeedPage;
import by.a1qa.klimov.forms.LoginPage;
import by.a1qa.klimov.forms.UserPage;
import by.a1qa.klimov.forms.VkNavigationBar;
import by.a1qa.klimov.models.CommentData;
import by.a1qa.klimov.models.PostData;
import by.a1qa.klimov.models.User;
import by.a1qa.klimov.models.wallpost.attachments.Photo;
import by.a1qa.klimov.models.wallpost.attachments.Size;
import by.a1qa.klimov.properties.ConfigurationData;
import by.a1qa.klimov.properties.DataProperties;
import by.a1qa.klimov.tests.BaseTest;
import by.a1qa.klimov.utils.FileComparator;
import by.a1qa.klimov.utils.FileSaver;
import by.a1qa.klimov.utils.Randomizer;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Objects;

public class VkComTest extends BaseTest {
    private final VkComApi vkComApi = new VkComApi();

    @Test
    public void testUserAction() throws UnirestException, IOException {
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

        File fileUploadImage = new File(ConfigurationData.getConfigurationPropertyByKey("pathToUploadFile"));

        Photo picture = vkComApi.uploadPicture(
                userId,
                "file1",
                fileUploadImage);

        String picturePath = "photo" + userId + "_" + picture.getId();

        Integer editedPostId = vkComApi.editPostMessage(
                userId,
                postId,
                editedMessage,
                picturePath,
                HttpURLConnection.HTTP_OK);
        Assert.assertEquals(editedPostId, postId, "Post isn't edited");

        postData = userPage.getPostData(postId, userId);
        Assert.assertEquals(postData.getText(), editedMessage, "Edited text does not match");

        Assert.assertTrue(
                userPage.isExistPictureOnPost(
                        postId, userId, "/" + picturePath),
                "Picture is not present");

        Photo uploadedPicture = vkComApi.getPhoto(userId, ServiceAlbum.WALL, picture.getId());
        List<Size> sizes = uploadedPicture.getSizes();
        int sizesLength = sizes.size();
        Assert.assertNotEquals(sizesLength, 0);

        BufferedImage bimg = ImageIO.read(fileUploadImage);
        int uploadImageHeight = bimg.getHeight();
        int uploadImageWight = bimg.getWidth();

        String pictureUrl = Objects.requireNonNull(sizes.stream()
                .filter(size -> (size.getHeight() == uploadImageHeight && size.getWidth() == uploadImageWight))
                .findAny()
                .orElse(null))
                .getUrl();

        String pathToDownloadFile = ConfigurationData.getConfigurationPropertyByKey("pathToDownloadFile");

        Assert.assertTrue(FileSaver.savePicture(
                pathToDownloadFile,
                ConfigurationData.getConfigurationPropertyByKey("formatDownloadFile"),
                pictureUrl));

        Assert.assertTrue(FileComparator.compareFiles(
                fileUploadImage,
                new File(pathToDownloadFile)
        ));

        String commentMessage = Randomizer.generateRandomText(
                Integer.parseInt(DataProperties.getDataPropertyByKey("postTextLength")));

        Integer commentId = vkComApi.leaveComment(userId, postId, commentMessage);

        CommentData commentData = userPage.getCommentData(userId, postId, commentId);
        Assert.assertEquals(commentData.getUserHref(), userHref, "Author comment does not match");
        Assert.assertEquals(commentData.getText(), commentMessage, "Text comment does not match");

        userPage.likePost(userId, postId);
        Assert.assertEquals(vkComApi.isLike(userId, userId, postId, TypeObject.POST), (Integer) 1,
                "Not found like from user with id: " + userId);

        Assert.assertEquals(vkComApi.deletePost(userId, postId), (Integer) 1, "Post isn't deleted");
        Assert.assertTrue(userPage.isPostDeleted(userId, postId), "Post isn't deleted");
    }
}

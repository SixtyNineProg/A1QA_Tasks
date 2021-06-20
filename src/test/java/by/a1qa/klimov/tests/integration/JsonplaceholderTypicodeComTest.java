package by.a1qa.klimov.tests.integration;

import by.a1qa.klimov.api.JsonplaceholderApi;
import by.a1qa.klimov.api.models.Post;
import by.a1qa.klimov.api.models.User;
import by.a1qa.klimov.properties.DataProperties;
import by.a1qa.klimov.tests.integration.listener.JsonplaceholderTestListener;
import by.a1qa.klimov.utils.JsonUtils;
import by.a1qa.klimov.utils.ListUtils;
import by.a1qa.klimov.utils.Randomizer;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;
import java.util.List;
import java.util.stream.Collectors;

@Listeners(JsonplaceholderTestListener.class)
public class JsonplaceholderTypicodeComTest extends BaseTest {
    private final JsonplaceholderApi jsonplaceholderApi = new JsonplaceholderApi();

    @Test
    public void testGetAllPots() {
        List<Post> posts = jsonplaceholderApi.readAllPosts(HttpURLConnection.HTTP_OK);
        List<Integer> ids = posts.stream().map(Post::getId).collect(Collectors.toList());
        Assert.assertTrue(ListUtils.listIsSortedByASC(ids), "IDs are not sorted.");
    }

    @Test
    public void testGetExistPost() {
        Integer id = Integer.valueOf(DataProperties.getDataPropertyByKey("idGetExistPost"));

        Post post = jsonplaceholderApi.readPost(id, HttpURLConnection.HTTP_OK);

        Assert.assertEquals(
                post.getUserId(),
                Integer.valueOf(DataProperties.getDataPropertyByKey("userIdGetExistPost")));

        Assert.assertEquals(post.getId(), id, "Ids do not mach.");
        Assert.assertNotEquals(post.getTitle(), "", "Title isn't empty.");
        Assert.assertNotEquals(post.getBody(), "", "Body isn't empty.");
    }

    @Test
    public void testGetNonExistPost() {
        Post post = jsonplaceholderApi.readPost(
                Integer.parseInt(DataProperties.getDataPropertyByKey("idGetNonExistPost")),
                HttpURLConnection.HTTP_NOT_FOUND);
        Assert.assertNull(post, "User found.");
    }

    @Test
    public void testPostRequestWithBody() {
        int randomLength = Integer.parseInt(DataProperties.getDataPropertyByKey("randomGenerateLength"));

        Post post = new Post(
                Integer.valueOf(DataProperties.getDataPropertyByKey("userIdPostRequestWithBody")),
                null,
                Randomizer.generateRandomText(randomLength),
                Randomizer.generateRandomText(randomLength)
        );

        Post answerPost = jsonplaceholderApi.createPost(post, HttpURLConnection.HTTP_CREATED);

        Assert.assertEquals(answerPost.getTitle(), post.getTitle(), "Titles don't mach");
        Assert.assertEquals(answerPost.getBody(), post.getBody(), "Bodes don't mach");
        Assert.assertEquals(answerPost.getUserId(), post.getUserId(), "User ids don't mach");
        Assert.assertNotNull(answerPost.getId(), "Id not found.");
    }

    @Test
    public void testGetUsers() {
        List<User> users = jsonplaceholderApi.readAllUsers(HttpURLConnection.HTTP_OK);
        User user = User.getUserById(users, Integer.parseInt(DataProperties.getDataPropertyByKey("userIdGetUsers")));

        String jsonExpected = DataProperties.getDataPropertyByKey("expectedUserDataGetUsers");
        User expectedUser = JsonUtils.toObject(jsonExpected, User.class);
        Assert.assertEquals(user, expectedUser, "The current user does not match the expected user.");
    }

    @Test
    public void testGetOneUser() {
        User user = jsonplaceholderApi.readUser(
                Integer.parseInt(DataProperties.getDataPropertyByKey("idGetOneUser")),
                HttpURLConnection.HTTP_OK
        );

        String jsonExpected = DataProperties.getDataPropertyByKey("expectedUserGetOneUser");
        User expectedUser = JsonUtils.toObject(jsonExpected, User.class);
        Assert.assertEquals(user, expectedUser, "The current user does not match the expected user.");
    }
}

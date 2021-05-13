package by.a1qa.klimov.tests.integration;

import by.a1qa.klimov.api.IJsonplaceholderApi;
import by.a1qa.klimov.api.impl.JsonplaceholderApi;
import by.a1qa.klimov.api.utils.APIUtils;
import by.a1qa.klimov.models.Post;
import by.a1qa.klimov.models.User;
import by.a1qa.klimov.properties.DataProperties;
import by.a1qa.klimov.tests.BaseTest;
import by.a1qa.klimov.utils.JsonUtils;
import by.a1qa.klimov.utils.ListUtils;
import by.a1qa.klimov.utils.Randomizer;
import lombok.extern.log4j.Log4j;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;
import java.util.List;
import java.util.stream.Collectors;

@Log4j
public class JsonplaceholderTypicodeComTest extends BaseTest {
    private final IJsonplaceholderApi jsonplaceholderApi = new JsonplaceholderApi();

    @Test
    public void testGetAllPots() {
        List<Post> posts = jsonplaceholderApi.readAllPosts(HttpURLConnection.HTTP_OK);
        List<Integer> ids = posts.stream().map(Post::getId).collect(Collectors.toList());
        Assert.assertTrue(ListUtils.listIsSortedByASC(ids));
    }


    @Test
    public void testGetExistPost() {
        Integer id = Integer.valueOf(DataProperties.getDataPropertyByKey("idGetExistPost"));

        Post post = jsonplaceholderApi.readPost(id, HttpURLConnection.HTTP_OK);

        Assert.assertEquals(
                post.getUserId(),
                Integer.valueOf(DataProperties.getDataPropertyByKey("userIdGetExistPost")));

        Assert.assertEquals(post.getId(), id);
        Assert.assertNotEquals(post.getTitle(), "");
        Assert.assertNotEquals(post.getBody(), "");
    }

    @Test
    public void testGetNonExistPost() {
        Post post = jsonplaceholderApi.readPost(
                Integer.parseInt(DataProperties.getDataPropertyByKey("idGetNonExistPost")),
                HttpURLConnection.HTTP_NOT_FOUND);
        Assert.assertNull(post);
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

        Assert.assertEquals(answerPost.getTitle(), post.getTitle());
        Assert.assertEquals(answerPost.getBody(), post.getBody());
        Assert.assertEquals(answerPost.getUserId(), post.getUserId());
        Assert.assertNotNull(answerPost.getId());
    }

    @Test
    public void testGetUsers() {
        List<User> users = jsonplaceholderApi.readAllUsers(HttpURLConnection.HTTP_OK);
        User user = APIUtils.getUserById(users, Integer.parseInt(DataProperties.getDataPropertyByKey("userIdGetUsers")));

        String jsonExpected = DataProperties.getDataPropertyByKey("expectedUserDataGetUsers");
        User expectedUser = JsonUtils.toObject(jsonExpected, User.class);
        Assert.assertEquals(user, expectedUser);
    }

    @Test
    public void testGetOneUser() {
        User user = jsonplaceholderApi.readUser(
                Integer.parseInt(DataProperties.getDataPropertyByKey("idGetOneUser")),
                HttpURLConnection.HTTP_OK
        );

        String jsonExpected = DataProperties.getDataPropertyByKey("expectedUserGetOneUser");
        User expectedUser = JsonUtils.toObject(jsonExpected, User.class);
        Assert.assertEquals(user, expectedUser);
    }
}

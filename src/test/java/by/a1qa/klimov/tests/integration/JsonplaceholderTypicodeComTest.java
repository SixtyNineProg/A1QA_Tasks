package by.a1qa.klimov.tests.integration;

import by.a1qa.klimov.models.Post;
import by.a1qa.klimov.models.RequestResult;
import by.a1qa.klimov.models.User;
import by.a1qa.klimov.tests.BaseTest;
import by.a1qa.klimov.utils.APIUtils;
import by.a1qa.klimov.utils.JsonUtils;
import by.a1qa.klimov.utils.Randomizer;
import by.a1qa.klimov.utils.Sort;
import lombok.extern.log4j.Log4j;
import org.json.JSONArray;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@Log4j
public class JsonplaceholderTypicodeComTest extends BaseTest {

    @Test
    @Parameters({"url", "method", "contentType", "accept"})
    public void testAllPots(@Optional("https://jsonplaceholder.typicode.com/posts") String url,
                            @Optional("GET") String method,
                            @Optional("application/x-www-form-urlencoded") String contentType,
                            @Optional("application/json") String accept) {
        RequestResult requestResult = APIUtils.doRequest(
                APIUtils.createUrl(url),
                method,
                contentType,
                accept,
                null);

        assert requestResult != null;

        Assert.assertEquals(requestResult.getCode(), 200);

        Assert.assertTrue(JsonUtils.stringIsJsonArray(requestResult.getAnswer()));

        List<Integer> ids = getIdsFormPodts(requestResult.getAnswer());
        Assert.assertTrue(Sort.listIsSortedByASC(ids));
    }

    private List<Integer> getIdsFormPodts(String posts) {
        List<Integer> ids = new ArrayList<>();
        if (posts != null) {
            JSONArray jsonArray = new JSONArray(posts);
            for (int i = 0; i < jsonArray.length(); i++) {
                Post post = JsonUtils.toObject(jsonArray.get(i).toString(), Post.class);
                if (post != null) ids.add(post.getId());
                else throw new NullPointerException("Post is empty.");
            }
        } else throw new NullPointerException("Json post is empty.");
        return ids;
    }


    @Test
    @Parameters({"url", "method", "contentType", "accept"})
    public void testExistPost(@Optional("https://jsonplaceholder.typicode.com/posts/99") String url,
                              @Optional("GET") String method,
                              @Optional("application/x-www-form-urlencoded") String contentType,
                              @Optional("application/json") String accept) {
        RequestResult requestResult = APIUtils.doRequest(
                APIUtils.createUrl(url),
                method,
                contentType,
                accept,
                null);

        assert requestResult != null;
        Assert.assertEquals(requestResult.getCode(), 200);

        Post post = JsonUtils.toObject(requestResult.getAnswer(), Post.class);
        assert post != null;
        Assert.assertEquals(post.getUserId(), (Integer) 10);
        Assert.assertEquals(post.getId(), (Integer) 99);
        Assert.assertNotEquals(post.getTitle(), "");
        Assert.assertNotEquals(post.getBody(), "");
    }

    @Test
    @Parameters({"url", "method", "contentType", "accept"})
    public void testNonExistPost(@Optional("https://jsonplaceholder.typicode.com/posts/150") String url,
                                 @Optional("GET") String method,
                                 @Optional("application/x-www-form-urlencoded") String contentType,
                                 @Optional("application/json") String accept) {
        RequestResult requestResult = APIUtils.doRequest(
                APIUtils.createUrl(url),
                method,
                contentType,
                accept,
                null);

        assert requestResult != null;
        Assert.assertEquals(requestResult.getCode(), 404);
        Assert.assertNull(requestResult.getAnswer());
    }

    @Test
    @Parameters({"url", "method", "contentType", "accept"})
    public void testPostRequestWithBody(@Optional("https://jsonplaceholder.typicode.com/posts") String url,
                                        @Optional("POST") String method,
                                        @Optional("application/json") String contentType,
                                        @Optional("application/json") String accept) {

        Post post = new Post(
                1,
                null,
                Randomizer.generateRandomText(6),
                Randomizer.generateRandomText(6)
        );
        RequestResult requestResult = APIUtils.doRequest(
                APIUtils.createUrl(url),
                method,
                contentType,
                accept,
                post);

        assert requestResult != null;
        Assert.assertEquals(requestResult.getCode(), 201);

        Post answerPost = JsonUtils.toObject(requestResult.getAnswer(), Post.class);
        assert answerPost != null;
        Assert.assertEquals(answerPost.getTitle(), post.getTitle());
        Assert.assertEquals(answerPost.getBody(), post.getBody());
        Assert.assertEquals(answerPost.getUserId(), post.getUserId());
        Assert.assertNotNull(answerPost.getId());
    }

    @Test
    @Parameters({"url", "method", "contentType", "accept"})
    public void testUsers(@Optional("https://jsonplaceholder.typicode.com/users") String url,
                          @Optional("GET") String method,
                          @Optional("application/x-www-form-urlencoded") String contentType,
                          @Optional("application/json") String accept) {
        RequestResult requestResult = APIUtils.doRequest(
                APIUtils.createUrl(url),
                method,
                contentType,
                accept,
                null);

        assert requestResult != null;

        Assert.assertEquals(requestResult.getCode(), 200);

        Assert.assertTrue(JsonUtils.stringIsJsonArray(requestResult.getAnswer()));

        List<User> users = createListUsers(requestResult.getAnswer());

        User user = getUserById(users, 5);
        assert user != null;

        String jsonExpected = "{\"website\":\"demarco.info\"," +
                "\"address\":{\"zipcode\":\"33263\",\"geo\":{\"lng\":\"62.5342\",\"lat\":\"-31.8129\"}," +
                "\"suite\":\"Suite 351\",\"city\":\"Roscoeview\",\"street\":\"Skiles Walks\"}," +
                "\"phone\":\"(254)954-1289\"," +
                "\"name\":\"Chelsey Dietrich\"," +
                "\"company\":{\"bs\":\"revolutionize end-to-end systems\",\"catchPhrase\":\"User-centric fault-tolerant solution\",\"name\":\"Keebler LLC\"}," +
                "\"id\":5," +
                "\"email\":\"Lucio_Hettinger@annie.ca\"," +
                "\"username\":\"Kamren\"}";

        User expectedUser = JsonUtils.toObject(jsonExpected, User.class);

        Assert.assertEquals(user, expectedUser);
    }

    private List<User> createListUsers(String jsonUsers) {
        List<User> userList = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(jsonUsers);
        for (int i = 0; i < jsonArray.length(); i++)
            userList.add(JsonUtils.toObject(jsonArray.get(i).toString(), User.class));
        return userList;
    }

    private User getUserById(List<User> users, int userId) {
        return users.stream().filter(p -> p.getId() == userId).findAny().orElse(null);
    }

    @Test
    @Parameters({"url", "method", "contentType", "accept"})
    public void testOneUser(@Optional("https://jsonplaceholder.typicode.com/users/5") String url,
                          @Optional("GET") String method,
                          @Optional("application/x-www-form-urlencoded") String contentType,
                          @Optional("application/json") String accept) {
        RequestResult requestResult = APIUtils.doRequest(
                APIUtils.createUrl(url),
                method,
                contentType,
                accept,
                null);

        assert requestResult != null;

        Assert.assertEquals(requestResult.getCode(), 200);

        User user = JsonUtils.toObject(requestResult.getAnswer(), User.class);
        assert user != null;

        String jsonExpected = "{\"website\":\"demarco.info\"," +
                "\"address\":{\"zipcode\":\"33263\",\"geo\":{\"lng\":\"62.5342\",\"lat\":\"-31.8129\"}," +
                "\"suite\":\"Suite 351\",\"city\":\"Roscoeview\",\"street\":\"Skiles Walks\"}," +
                "\"phone\":\"(254)954-1289\"," +
                "\"name\":\"Chelsey Dietrich\"," +
                "\"company\":{\"bs\":\"revolutionize end-to-end systems\",\"catchPhrase\":\"User-centric fault-tolerant solution\",\"name\":\"Keebler LLC\"}," +
                "\"id\":5," +
                "\"email\":\"Lucio_Hettinger@annie.ca\"," +
                "\"username\":\"Kamren\"}";

        User expectedUser = JsonUtils.toObject(jsonExpected, User.class);

        Assert.assertEquals(user, expectedUser);
    }
}

package by.a1qa.klimov.tests.integration;

import by.a1qa.klimov.models.Post;
import by.a1qa.klimov.models.RequestResult;
import by.a1qa.klimov.models.User;
import by.a1qa.klimov.properties.DataProperties;
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
    @Parameters({"url"})
    public void testGetAllPots(@Optional("https://jsonplaceholder.typicode.com/posts") String url) {
        RequestResult requestResult = APIUtils.doRequest(
                APIUtils.createUrl(url),
                "GET",
                "application/x-www-form-urlencoded",
                "application/json",
                null);

        assert requestResult != null;

        Assert.assertEquals(requestResult.getCode(), Integer.parseInt(DataProperties.getDataPropertyByKey("codeGetAllPots")));

        Assert.assertTrue(JsonUtils.stringIsJsonArray(requestResult.getAnswer()));

        List<Integer> ids = getIdsFormPosts(requestResult.getAnswer());
        Assert.assertTrue(Sort.listIsSortedByASC(ids));
    }

    private List<Integer> getIdsFormPosts(String posts) {
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
    @Parameters({"url"})
    public void testGetExistPost(@Optional("https://jsonplaceholder.typicode.com/posts/99") String url) {
        RequestResult requestResult = APIUtils.doRequest(
                APIUtils.createUrl(url),
                "GET",
                "application/x-www-form-urlencoded",
                "application/json",
                null);

        assert requestResult != null;
        Assert.assertEquals(
                requestResult.getCode(),
                Integer.parseInt(DataProperties.getDataPropertyByKey("codeGetExistPost")));

        Post post = JsonUtils.toObject(requestResult.getAnswer(), Post.class);
        assert post != null;
        Assert.assertEquals(
                post.getUserId(),
                Integer.valueOf(DataProperties.getDataPropertyByKey("userIdGetExistPost")));
        Assert.assertEquals(
                post.getId(),
                Integer.valueOf(DataProperties.getDataPropertyByKey("idGetExistPost")));
        Assert.assertNotEquals(post.getTitle(), "");
        Assert.assertNotEquals(post.getBody(), "");
    }

    @Test
    @Parameters({"url"})
    public void testGetNonExistPost(@Optional("https://jsonplaceholder.typicode.com/posts/150") String url) {
        RequestResult requestResult = APIUtils.doRequest(
                APIUtils.createUrl(url),
                "GET",
                "application/x-www-form-urlencoded",
                "application/json",
                null);

        assert requestResult != null;
        Assert.assertEquals(requestResult.getCode(), Integer.parseInt(DataProperties.getDataPropertyByKey("codeGetNonExistPost")));
        Assert.assertNull(requestResult.getAnswer());
    }

    @Test
    @Parameters({"url"})
    public void testPostRequestWithBody(@Optional("https://jsonplaceholder.typicode.com/posts") String url) {
        int randomLength = Integer.parseInt(DataProperties.getDataPropertyByKey("randomGenerateLength"));

        Post post = new Post(
                Integer.valueOf(DataProperties.getDataPropertyByKey("userIdPostRequestWithBody")),
                null,
                Randomizer.generateRandomText(randomLength),
                Randomizer.generateRandomText(randomLength)
        );

        RequestResult requestResult = APIUtils.doRequest(
                APIUtils.createUrl(url),
                "POST",
                "application/json",
                "application/json",
                JsonUtils.toJson(post));

        assert requestResult != null;
        Assert.assertEquals(
                requestResult.getCode(),
                Integer.parseInt(DataProperties.getDataPropertyByKey("codePostRequestWithBody")));

        Post answerPost = JsonUtils.toObject(requestResult.getAnswer(), Post.class);
        assert answerPost != null;
        Assert.assertEquals(answerPost.getTitle(), post.getTitle());
        Assert.assertEquals(answerPost.getBody(), post.getBody());
        Assert.assertEquals(answerPost.getUserId(), post.getUserId());
        Assert.assertNotNull(answerPost.getId());
    }

    @Test
    @Parameters({"url"})
    public void testGetUsers(@Optional("https://jsonplaceholder.typicode.com/users") String url) {
        RequestResult requestResult = APIUtils.doRequest(
                APIUtils.createUrl(url),
                "GET",
                "application/x-www-form-urlencoded",
                "application/json",
                null);

        assert requestResult != null;
        Assert.assertEquals(
                requestResult.getCode(),
                Integer.parseInt(DataProperties.getDataPropertyByKey("codeGetUsers")));

        Assert.assertTrue(JsonUtils.stringIsJsonArray(requestResult.getAnswer()));

        List<User> users = createListUsers(requestResult.getAnswer());

        User user = getUserById(users, Integer.parseInt(DataProperties.getDataPropertyByKey("userIdGetUsers")));
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
        for (int i = 0; i < jsonArray.length(); i++) {
            String str = jsonArray.get(i).toString();
            userList.add(JsonUtils.toObject(jsonArray.get(i).toString(), User.class));
        }
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

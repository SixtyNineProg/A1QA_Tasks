package by.a1qa.klimov.tests.integration;

import by.a1qa.klimov.api.IJsonplaceholderApi;
import by.a1qa.klimov.api.impl.JsonplaceholderApi;
import by.a1qa.klimov.models.Post;
import by.a1qa.klimov.tests.BaseTest;
import by.a1qa.klimov.utils.ListUtils;
import lombok.extern.log4j.Log4j;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class JsonplaceholderTypicodeComTest extends BaseTest {
    private final IJsonplaceholderApi jsonplaceholderApi = new JsonplaceholderApi();

    @Test
    public void testGetAllPots() {
        List<Post> posts = jsonplaceholderApi.readAllPosts(HttpURLConnection.HTTP_OK);

        List<Integer> ids = new ArrayList<>();
        posts.stream().map(post -> ids.add(post.getId()));

        List<Integer> ids = getIdsFormPosts(requestResult.getAnswer());
        Assert.assertTrue(ListUtils.listIsSortedByASC(ids));
    }


//    @Test
//    @Parameters({"url"})
//    public void testGetExistPost(@Optional("https://jsonplaceholder.typicode.com/posts/99") String url) {
//        RequestResult requestResult = APIUtils.doRequest(
//                APIUtils.createUrl(url),
//                "GET",
//                "application/x-www-form-urlencoded",
//                "application/json",
//                null);
//
//        assert requestResult != null;
//        Assert.assertEquals(requestResult.getCode(), HttpURLConnection.HTTP_OK);
//
//        Post post = JsonUtils.toObject(requestResult.getAnswer(), Post.class);
//        assert post != null;
//        Assert.assertEquals(
//                post.getUserId(),
//                Integer.valueOf(DataProperties.getDataPropertyByKey("userIdGetExistPost")));
//        Assert.assertEquals(
//                post.getId(),
//                Integer.valueOf(DataProperties.getDataPropertyByKey("idGetExistPost")));
//        Assert.assertNotEquals(post.getTitle(), "");
//        Assert.assertNotEquals(post.getBody(), "");
//    }
//
//    @Test
//    @Parameters({"url"})
//    public void testGetNonExistPost(@Optional("https://jsonplaceholder.typicode.com/posts/150") String url) {
//        RequestResult requestResult = APIUtils.doRequest(
//                APIUtils.createUrl(url),
//                "GET",
//                "application/x-www-form-urlencoded",
//                "application/json",
//                null);
//
//        assert requestResult != null;
//        Assert.assertEquals(requestResult.getCode(), HttpURLConnection.HTTP_NOT_FOUND);
//        Assert.assertNull(requestResult.getAnswer());
//    }
//
//    @Test
//    @Parameters({"url"})
//    public void testPostRequestWithBody(@Optional("https://jsonplaceholder.typicode.com/posts") String url) {
//        int randomLength = Integer.parseInt(DataProperties.getDataPropertyByKey("randomGenerateLength"));
//
//        Post post = new Post(
//                Integer.valueOf(DataProperties.getDataPropertyByKey("userIdPostRequestWithBody")),
//                null,
//                Randomizer.generateRandomText(randomLength),
//                Randomizer.generateRandomText(randomLength)
//        );
//
//        RequestResult requestResult = APIUtils.doRequest(
//                APIUtils.createUrl(url),
//                "POST",
//                "application/json",
//                "application/json",
//                JsonUtils.toJson(post));
//
//        assert requestResult != null;
//        Assert.assertEquals(requestResult.getCode(), HttpURLConnection.HTTP_CREATED);
//
//        Post answerPost = JsonUtils.toObject(requestResult.getAnswer(), Post.class);
//        assert answerPost != null;
//        Assert.assertEquals(answerPost.getTitle(), post.getTitle());
//        Assert.assertEquals(answerPost.getBody(), post.getBody());
//        Assert.assertEquals(answerPost.getUserId(), post.getUserId());
//        Assert.assertNotNull(answerPost.getId());
//    }
//
//    @Test
//    @Parameters({"url"})
//    public void testGetUsers(@Optional("https://jsonplaceholder.typicode.com/users") String url) {
//        RequestResult requestResult = APIUtils.doRequest(
//                APIUtils.createUrl(url),
//                "GET",
//                "application/x-www-form-urlencoded",
//                "application/json",
//                null);
//
//        assert requestResult != null;
//        Assert.assertEquals(requestResult.getCode(), HttpURLConnection.HTTP_OK);
//
//        Assert.assertTrue(JsonUtils.isStringJsonArray(requestResult.getAnswer()));
//
//        List<User> users = createListUsers(requestResult.getAnswer());
//
//        User user = getUserById(users, Integer.parseInt(DataProperties.getDataPropertyByKey("userIdGetUsers")));
//        assert user != null;
//
//        String jsonExpected = DataProperties.getDataPropertyByKey("expectedUserDataGetUsers");
//        User expectedUser = JsonUtils.toObject(jsonExpected, User.class);
//        Assert.assertEquals(user, expectedUser);
//    }
//
//    private List<User> createListUsers(String jsonUsers) {
//        List<User> userList = new ArrayList<>();
//        JSONArray jsonArray = new JSONArray(jsonUsers);
//        for (int i = 0; i < jsonArray.length(); i++) {
//            userList.add(JsonUtils.toObject(jsonArray.get(i).toString(), User.class));
//        }
//        return userList;
//    }
//
//    private User getUserById(List<User> users, int userId) {
//        return users.stream().filter(p -> p.getId() == userId).findAny().orElse(null);
//    }
//
//    @Test
//    @Parameters({"url"})
//    public void testGetOneUser(@Optional("https://jsonplaceholder.typicode.com/users/5") String url) {
//        RequestResult requestResult = APIUtils.doRequest(
//                APIUtils.createUrl(url),
//                "GET",
//                "application/x-www-form-urlencoded",
//                "application/json",
//                null);
//
//        assert requestResult != null;
//        Assert.assertEquals(requestResult.getCode(), HttpURLConnection.HTTP_OK);
//
//        User user = JsonUtils.toObject(requestResult.getAnswer(), User.class);
//        assert user != null;
//
//        String jsonExpected = DataProperties.getDataPropertyByKey("expectedUserGetOneUser");
//        User expectedUser = JsonUtils.toObject(jsonExpected, User.class);
//        Assert.assertEquals(user, expectedUser);
//    }
}

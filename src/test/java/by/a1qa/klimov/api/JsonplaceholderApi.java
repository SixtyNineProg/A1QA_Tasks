package by.a1qa.klimov.api;

import aquality.selenium.core.logging.Logger;
import aquality.selenium.core.utilities.JsonSettingsFile;
import by.a1qa.klimov.api.models.Post;
import by.a1qa.klimov.api.models.RequestResult;
import by.a1qa.klimov.api.models.User;
import by.a1qa.klimov.api.utils.APIUtils;
import by.a1qa.klimov.utils.JsonUtils;
import org.testng.Assert;

import java.util.List;

public class JsonplaceholderApi {
    private static final String BASE_URL =
            new JsonSettingsFile("settings.json").getValue("/url").toString();

    public List<Post> readAllPosts(int expectedRequestCode) {
        Logger.getInstance().info("Read all posts");
        RequestResult requestResult = APIUtils
                .doGetRequest(BASE_URL + UrlPath.POSTS);
        Assert.assertEquals(requestResult.getCode(), expectedRequestCode,
                "Response code does not match" + expectedRequestCode);
        Assert.assertTrue(JsonUtils.isStringJsonArray(requestResult.getAnswer()),
                "The answer does not match the JSON format");
        return JsonUtils.toObjectsList(requestResult.getAnswer(), Post.class);
    }

    public Post readPost(int id, int expectedRequestCode) {
        Logger.getInstance().info("Read post with id: " + id);
        RequestResult requestResult = APIUtils
                .doGetRequest(BASE_URL + UrlPath.POSTS_ID + id);
        Assert.assertEquals(requestResult.getCode(), expectedRequestCode,
                "Response code does not match" + expectedRequestCode);
        try {
            return JsonUtils.toObject(requestResult.getAnswer(), Post.class);
        } catch (IllegalArgumentException e) {
            Logger.getInstance().fatal("Does not fit JSON format", e);
            return null;
        }
    }

    public Post createPost(Post post, int expectedRequestCode) {
        Logger.getInstance().info("Create post with value " + post.toString());
        RequestResult requestResult = APIUtils.doPostRequest(
                BASE_URL + UrlPath.POSTS,
                JsonUtils.toJson(post),
                "application/json",
                "application/json");
        Assert.assertEquals(requestResult.getCode(), expectedRequestCode,
                "Response code does not match" + expectedRequestCode);
        try {
            return JsonUtils.toObject(requestResult.getAnswer(), Post.class);
        } catch (IllegalArgumentException e) {
            Logger.getInstance().fatal("Does not fit JSON format", e);
            return null;
        }
    }

    public List<User> readAllUsers(int expectedRequestCode) {
        Logger.getInstance().info("Read all users");
        RequestResult requestResult = APIUtils
                .doGetRequest(BASE_URL + UrlPath.USERS);
        Assert.assertEquals(requestResult.getCode(), expectedRequestCode,
                "Response code does not match" + expectedRequestCode);
        Assert.assertTrue(JsonUtils.isStringJsonArray(requestResult.getAnswer()),
                "The answer does not match the JSON format");
        return JsonUtils.toObjectsList(requestResult.getAnswer(), User.class);
    }

    public User readUser(int id, int expectedRequestCode) {
        Logger.getInstance().info("Read user with id: " + id);
        RequestResult requestResult = APIUtils
                .doGetRequest(BASE_URL + UrlPath.USERS_ID + id);
        Assert.assertEquals(requestResult.getCode(), expectedRequestCode,
                "Response code does not match" + expectedRequestCode);
        try {
            return JsonUtils.toObject(requestResult.getAnswer(), User.class);
        } catch (IllegalArgumentException e) {
            Logger.getInstance().fatal("Does not fit JSON format", e);
            return null;
        }
    }
}

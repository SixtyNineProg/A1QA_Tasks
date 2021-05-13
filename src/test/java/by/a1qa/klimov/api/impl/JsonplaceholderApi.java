package by.a1qa.klimov.api.impl;

import aquality.selenium.core.logging.Logger;
import by.a1qa.klimov.api.IJsonplaceholderApi;
import by.a1qa.klimov.models.Post;
import by.a1qa.klimov.models.RequestResult;
import by.a1qa.klimov.models.User;
import by.a1qa.klimov.properties.ConfigurationProperties;
import by.a1qa.klimov.api.utils.APIUtils;
import by.a1qa.klimov.utils.JsonUtils;
import org.testng.Assert;

import java.util.List;

public class JsonplaceholderApi implements IJsonplaceholderApi {

    @Override
    public List<Post> readAllPosts(int expectedRequestCode) {
        RequestResult requestResult = APIUtils
                .doGetRequest(ConfigurationProperties.getConfigurationPropertyByKey("postsUrl"));
        Assert.assertEquals(requestResult.getCode(), expectedRequestCode);
        Assert.assertTrue(JsonUtils.isStringJsonArray(requestResult.getAnswer()));
        return JsonUtils.toObjectsList(requestResult.getAnswer(), Post.class);
    }

    @Override
    public Post readPost(int id, int expectedRequestCode) {
        RequestResult requestResult = APIUtils
                .doGetRequest(ConfigurationProperties.getConfigurationPropertyByKey("onePostUrl") + id);
        Assert.assertEquals(requestResult.getCode(), expectedRequestCode);
        try {
            return JsonUtils.toObject(requestResult.getAnswer(), Post.class);
        } catch (IllegalArgumentException e) {
            Logger.getInstance().fatal("Does not fit JSON format", e);
            return null;
        }
    }

    @Override
    public Post createPost(Post post, int expectedRequestCode) {
        RequestResult requestResult = APIUtils.doPostRequest(
                ConfigurationProperties.getConfigurationPropertyByKey("postsUrl"),
                JsonUtils.toJson(post));
        Assert.assertEquals(requestResult.getCode(), expectedRequestCode);
        try {
            return JsonUtils.toObject(requestResult.getAnswer(), Post.class);
        } catch (IllegalArgumentException e) {
            Logger.getInstance().fatal("Does not fit JSON format", e);
            return null;
        }
    }

    @Override
    public List<User> readAllUsers(int expectedRequestCode) {
        RequestResult requestResult = APIUtils
                .doGetRequest(ConfigurationProperties.getConfigurationPropertyByKey("usersUrl"));
        Assert.assertEquals(requestResult.getCode(), expectedRequestCode);
        Assert.assertTrue(JsonUtils.isStringJsonArray(requestResult.getAnswer()));
        return JsonUtils.toObjectsList(requestResult.getAnswer(), User.class);
    }

    @Override
    public User readUser(int id, int expectedRequestCode) {
        RequestResult requestResult = APIUtils
                .doGetRequest(ConfigurationProperties.getConfigurationPropertyByKey("oneUserUrl") + id);
        Assert.assertEquals(requestResult.getCode(), expectedRequestCode);
        try {
            return JsonUtils.toObject(requestResult.getAnswer(), User.class);
        } catch (IllegalArgumentException e) {
            Logger.getInstance().fatal("Does not fit JSON format", e);
            return null;
        }
    }
}

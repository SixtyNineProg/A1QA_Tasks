package by.a1qa.klimov.api.impl;

import by.a1qa.klimov.api.IJsonplaceholderApi;
import by.a1qa.klimov.models.Post;
import by.a1qa.klimov.models.RequestResult;
import by.a1qa.klimov.properties.ConfigurationProperties;
import by.a1qa.klimov.utils.APIUtils;
import by.a1qa.klimov.utils.JsonUtils;
import org.testng.Assert;

import java.util.List;

public class JsonplaceholderApi implements IJsonplaceholderApi {

    @Override
    public List<Post> readAllPosts(int expectedRequestCode) {
        RequestResult requestResult = APIUtils
                .doGetRequest(ConfigurationProperties.getConfigurationPropertyByKey("allPostsUrl"));
        Assert.assertEquals(requestResult.getCode(), expectedRequestCode);
        Assert.assertTrue(JsonUtils.isStringJsonArray(requestResult.getAnswer()));
        return JsonUtils.toObjectsList(requestResult.getAnswer(), Post.class);
    }
}

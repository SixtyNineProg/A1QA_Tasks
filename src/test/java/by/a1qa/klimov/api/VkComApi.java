package by.a1qa.klimov.api;

import by.a1qa.klimov.api.utils.APIUtils;
import by.a1qa.klimov.models.RequestResult;
import by.a1qa.klimov.models.wallpost.Post;
import by.a1qa.klimov.properties.ConfigurationData;
import by.a1qa.klimov.properties.DataProperties;
import by.a1qa.klimov.utils.JsonUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VkComApi {
    public List<Post> getWallPosts(int expectedRequestCode) {
        Map<String, String> requestParameters = new HashMap<>() {{
            put("owner_id", DataProperties.getDataPropertyByKey("userId"));
            put("v", ConfigurationData.getConfigurationPropertyByKey("vkApiVersion"));
            put("access_token", DataProperties.getDataPropertyByKey("userAccessToken"));
        }};

        RequestResult requestResult = APIUtils.doGetRequest(
                ConfigurationData.getConfigurationPropertyByKey("vkApiUrl") + UrlPath.WALL_GET,
                requestParameters);
        Assert.assertEquals(requestResult.getCode(), expectedRequestCode,
                "Response code does not match" + expectedRequestCode);
        JSONObject answer = new JSONObject(requestResult.getAnswer());
        JSONObject response = answer.getJSONObject("response");
        JSONArray items = response.getJSONArray("items");
        return JsonUtils.toObjectsList(items, Post.class);
    }
}

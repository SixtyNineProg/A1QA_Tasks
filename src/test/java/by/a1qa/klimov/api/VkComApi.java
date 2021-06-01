package by.a1qa.klimov.api;

import by.a1qa.klimov.api.utils.APIUtils;
import by.a1qa.klimov.models.RequestResult;
import by.a1qa.klimov.models.User;
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
    private static final String RESPONSE_OBJECT_KEY = "response";
    private static final String WALL_POSTS_ITEMS = "items";
    private static final String POST_ID_ATTRIBUTE = "post_id";

    public Integer editPostMessage(
            Integer ownerId, Integer postId, String message, String picturePath, int expectedRequestCode) {
        Map<String, String> requestParameters = new HashMap<>() {{
            put("owner_id", String.valueOf(ownerId));
            put("post_id", String.valueOf(postId));
            put("message", message);
            put("attachments", picturePath);
            put("v", ConfigurationData.getConfigurationPropertyByKey("vkApiVersion"));
            put("access_token", DataProperties.getDataPropertyByKey("userAccessToken"));
        }};

        RequestResult requestResult = APIUtils.doGetRequest(
                ConfigurationData.getConfigurationPropertyByKey("vkApiUrl") + UrlPath.WALL_EDIT_POST,
                requestParameters);
        Assert.assertEquals(requestResult.getCode(), expectedRequestCode,
                "Response code does not match" + expectedRequestCode);
        JSONObject jsonObject = new JSONObject(requestResult.getAnswer());
        JSONObject response = jsonObject.getJSONObject(RESPONSE_OBJECT_KEY);
        return (Integer) response.get(POST_ID_ATTRIBUTE);
    }

    public Integer createPost(Integer ownerId, String message, int expectedRequestCode) {
        Map<String, String> requestParameters = new HashMap<>() {{
            put("owner_id", String.valueOf(ownerId));
            put("message", message);
            put("v", ConfigurationData.getConfigurationPropertyByKey("vkApiVersion"));
            put("access_token", DataProperties.getDataPropertyByKey("userAccessToken"));
        }};

        RequestResult requestResult = APIUtils.doGetRequest(
                ConfigurationData.getConfigurationPropertyByKey("vkApiUrl") + UrlPath.WALL_POST,
                requestParameters);
        Assert.assertEquals(requestResult.getCode(), expectedRequestCode,
                "Response code does not match" + expectedRequestCode);
        JSONObject jsonObject = new JSONObject(requestResult.getAnswer());
        JSONObject response = jsonObject.getJSONObject(RESPONSE_OBJECT_KEY);
        return (Integer) response.get(POST_ID_ATTRIBUTE);
    }

    public User getUser(int expectedRequestCode) {
        Map<String, String> requestParameters = new HashMap<>() {{
            put("usid", null);
            put("v", ConfigurationData.getConfigurationPropertyByKey("vkApiVersion"));
            put("access_token", DataProperties.getDataPropertyByKey("userAccessToken"));
        }};

        RequestResult requestResult = APIUtils.doGetRequest(
                ConfigurationData.getConfigurationPropertyByKey("vkApiUrl") + UrlPath.USERS_GET,
                requestParameters);
        Assert.assertEquals(requestResult.getCode(), expectedRequestCode,
                "Response code does not match" + expectedRequestCode);
        JSONObject jsonObject = new JSONObject(requestResult.getAnswer());
        JSONArray response = jsonObject.getJSONArray(RESPONSE_OBJECT_KEY);
        JSONObject firstUser;
        if (response.length() != 0) firstUser = response.getJSONObject(0);
        else throw new NullPointerException("The answer does not include users");
        return JsonUtils.toObject(firstUser, User.class);
    }

    public List<Post> getWallPosts(Integer owner_id, int expectedRequestCode) {
        Map<String, String> requestParameters = new HashMap<>() {{
            put("owner_id", owner_id.toString());
            put("v", ConfigurationData.getConfigurationPropertyByKey("vkApiVersion"));
            put("access_token", DataProperties.getDataPropertyByKey("userAccessToken"));
        }};

        RequestResult requestResult = APIUtils.doGetRequest(
                ConfigurationData.getConfigurationPropertyByKey("vkApiUrl") + UrlPath.WALL_GET,
                requestParameters);
        Assert.assertEquals(requestResult.getCode(), expectedRequestCode,
                "Response code does not match" + expectedRequestCode);
        JSONObject answer = new JSONObject(requestResult.getAnswer());
        JSONObject response = answer.getJSONObject(RESPONSE_OBJECT_KEY);
        JSONArray items = response.getJSONArray(WALL_POSTS_ITEMS);
        return JsonUtils.toObjectsList(items, Post.class);
    }
}

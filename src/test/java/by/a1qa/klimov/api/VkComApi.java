package by.a1qa.klimov.api;

import aquality.selenium.core.logging.Logger;
import by.a1qa.klimov.api.utils.APIUtils;
import by.a1qa.klimov.models.RequestResult;
import by.a1qa.klimov.models.User;
import by.a1qa.klimov.models.wallpost.Post;
import by.a1qa.klimov.models.wallpost.attachments.Photo;
import by.a1qa.klimov.properties.ConfigurationData;
import by.a1qa.klimov.properties.DataProperties;
import by.a1qa.klimov.utils.JsonUtils;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
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

    //    public Post createPost(Post post, int expectedRequestCode) {
//        RequestResult requestResult = APIUtils.doPostRequest(
//                BASE_URL + UrlPath.POSTS,
//                JsonUtils.toJson(post),
//                "application/json",
//                "application/json");
//        Assert.assertEquals(requestResult.getCode(), expectedRequestCode,
//                "Response code does not match" + expectedRequestCode);
//        try {
//            return JsonUtils.toObject(requestResult.getAnswer(), Post.class);
//        } catch (IllegalArgumentException e) {
//            Logger.getInstance().fatal("Does not fit JSON format", e);
//            return null;
//        }
//    }
    public Photo uploadPicture(String fieldName, String pathToPicture, int expectedRequestCode) throws UnirestException {
        Map<String, String> headers = new HashMap<>();
        headers.put("accept", "application/json");
        headers.put("Authorization", "Bearer " + DataProperties.getDataPropertyByKey("userAccessToken"));

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("album_id", DataProperties.getDataPropertyByKey("userPhotoAlbumId"));
        parameters.put("v", ConfigurationData.getConfigurationPropertyByKey("vkApiVersion"));

        HttpResponse<JsonNode> jsonResponse
                = Unirest.get(ConfigurationData.getConfigurationPropertyByKey("vkApiUrl") +
                UrlPath.PHOTOS_GET_UPLOAD_SERVER)
                .headers(headers)
                .queryString(parameters)
                .asJson();

        Assert.assertEquals(jsonResponse.getStatus(), expectedRequestCode,
                "Response code does not match" + expectedRequestCode);
        Assert.assertNotNull(jsonResponse.getBody());

        JSONObject body = new JSONObject(jsonResponse.getBody().toString());
        JSONObject response = body.getJSONObject(RESPONSE_OBJECT_KEY);
        String uploadUrl = response.getString("upload_url");


        jsonResponse = Unirest.post(
                uploadUrl)
                .field(fieldName, new File(pathToPicture))
                .asJson();

        Assert.assertEquals(jsonResponse.getStatus(), expectedRequestCode,
                "Response code does not match" + expectedRequestCode);
        Assert.assertNotNull(jsonResponse.getBody());

        JsonNode jsonNode = jsonResponse.getBody();
        String server = jsonNode.getObject().get("server").toString();
        String photosList = jsonNode.getObject().get("photos_list").toString();
        String hash = jsonNode.getObject().get("hash").toString();


        headers = new HashMap<>();
        headers.put("accept", "application/json");
        headers.put("Authorization", "Bearer " + DataProperties.getDataPropertyByKey("userAccessToken"));

        parameters = new HashMap<>();
        parameters.put("album_id", DataProperties.getDataPropertyByKey("userPhotoAlbumId"));
        parameters.put("server", server);
        parameters.put("photos_list", photosList);
        parameters.put("hash", hash);
        parameters.put("v", ConfigurationData.getConfigurationPropertyByKey("vkApiVersion"));

        jsonResponse = Unirest.get(ConfigurationData.getConfigurationPropertyByKey("vkApiUrl") +
                UrlPath.PHOTOS_SAVE)
                .headers(headers)
                .queryString(parameters)
                .asJson();

        Assert.assertEquals(jsonResponse.getStatus(), expectedRequestCode,
                "Response code does not match" + expectedRequestCode);
        Assert.assertNotNull(jsonResponse.getBody());

        body = new JSONObject(jsonResponse.getBody().toString());
        JSONArray responseArray = body.getJSONArray(RESPONSE_OBJECT_KEY);
        List<Photo> photos = JsonUtils.toObjectsList(responseArray, Photo.class);
        if (photos.size() != 0) return photos.get(0);
        else throw new NullPointerException();
    }
}

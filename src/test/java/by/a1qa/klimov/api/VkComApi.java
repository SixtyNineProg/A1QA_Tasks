package by.a1qa.klimov.api;

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
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

import java.io.File;
import java.net.HttpURLConnection;
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

    public Photo uploadPicture(String fieldName, String pathToPicture) throws UnirestException {
        String uploadUrl = getUploadServer();

        JsonNode jsonNode = uploadPictureToServer(uploadUrl, fieldName, pathToPicture);
        String server = jsonNode.getObject().get("server").toString();
        String photosList = jsonNode.getObject().get("photos_list").toString();
        String hash = jsonNode.getObject().get("hash").toString();

        List<Photo> photos = savePictures(server, photosList, hash);
        if (photos.size() != 0) return photos.get(0);
        else throw new NullPointerException();
    }

    private List<Photo> savePictures(String server, String photosList, String hash) throws UnirestException {
        Map<String, String> headers = new HashMap<>();
        headers.put("accept", "application/json");
        headers.put("Authorization", "Bearer " + DataProperties.getDataPropertyByKey("userAccessToken"));

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("album_id", DataProperties.getDataPropertyByKey("userPhotoAlbumId"));
        parameters.put("server", server);
        parameters.put("photos_list", photosList);
        parameters.put("hash", hash);
        parameters.put("v", ConfigurationData.getConfigurationPropertyByKey("vkApiVersion"));

        HttpResponse<JsonNode> jsonResponse = Unirest.get(ConfigurationData.getConfigurationPropertyByKey("vkApiUrl") +
                UrlPath.PHOTOS_SAVE)
                .headers(headers)
                .queryString(parameters)
                .asJson();

        Assert.assertEquals(jsonResponse.getStatus(), HttpURLConnection.HTTP_OK,
                "Response code does not match" + HttpURLConnection.HTTP_OK);
        Assert.assertNotNull(jsonResponse.getBody());

        JSONObject body = new JSONObject(jsonResponse.getBody().toString());
        JSONArray responseArray = body.getJSONArray(RESPONSE_OBJECT_KEY);
        return JsonUtils.toObjectsList(responseArray, Photo.class);
    }

    private JsonNode uploadPictureToServer(
            String uploadUrl, String fieldName, String pathToPicture) throws UnirestException {
        HttpResponse<JsonNode> jsonResponse = Unirest.post(
                uploadUrl)
                .field(fieldName, new File(pathToPicture))
                .asJson();

        Assert.assertEquals(jsonResponse.getStatus(), HttpURLConnection.HTTP_OK,
                "Response code does not match" + HttpURLConnection.HTTP_OK);
        JsonNode jsonNode = jsonResponse.getBody();
        Assert.assertNotNull(jsonNode);
        return jsonNode;
    }

    private String getUploadServer() throws UnirestException {
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

        Assert.assertEquals(jsonResponse.getStatus(), HttpURLConnection.HTTP_OK,
                "Response code does not match" + HttpURLConnection.HTTP_OK);
        Assert.assertNotNull(jsonResponse.getBody());

        JSONObject body = new JSONObject(jsonResponse.getBody().toString());
        JSONObject response = body.getJSONObject(RESPONSE_OBJECT_KEY);
        return response.getString("upload_url");
    }

    public Integer leaveComment(Integer ownerId, Integer postId, String message) throws UnirestException {
        Map<String, String> headers = new HashMap<>();
        headers.put("accept", "application/json");
        headers.put("Authorization", "Bearer " + DataProperties.getDataPropertyByKey("userAccessToken"));

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("owner_id", ownerId);
        parameters.put("post_id", postId);
        parameters.put("message", message);
        parameters.put("v", ConfigurationData.getConfigurationPropertyByKey("vkApiVersion"));

        HttpResponse<JsonNode> jsonResponse
                = Unirest.get(ConfigurationData.getConfigurationPropertyByKey("vkApiUrl") +
                UrlPath.WALL_CRATE_COMMENT)
                .headers(headers)
                .queryString(parameters)
                .asJson();

        Assert.assertEquals(jsonResponse.getStatus(), HttpURLConnection.HTTP_OK,
                "Response code does not match" + HttpURLConnection.HTTP_OK);
        Assert.assertNotNull(jsonResponse.getBody());

        JSONObject body = new JSONObject(jsonResponse.getBody().toString());
        JSONObject response = body.getJSONObject(RESPONSE_OBJECT_KEY);
        return response.getInt("comment_id");
    }

    public Integer isLike(Integer userId, Integer ownerId, Integer itemId, TypeObject type) throws UnirestException {
        Map<String, String> headers = new HashMap<>();
        headers.put("accept", "application/json");
        headers.put("Authorization", "Bearer " + DataProperties.getDataPropertyByKey("userAccessToken"));

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("type", type.toString().toLowerCase());
        parameters.put("user_id", userId);
        parameters.put("owner_id", ownerId);
        parameters.put("item_id", itemId);
        parameters.put("v", ConfigurationData.getConfigurationPropertyByKey("vkApiVersion"));

        HttpResponse<JsonNode> jsonResponse
                = Unirest.get(ConfigurationData.getConfigurationPropertyByKey("vkApiUrl") +
                UrlPath.LIKES_IS_LIKED)
                .headers(headers)
                .queryString(parameters)
                .asJson();

        JSONObject body = new JSONObject(jsonResponse.getBody().toString());
        JSONObject response = body.getJSONObject(RESPONSE_OBJECT_KEY);
        return response.getInt("liked");
    }

    public Integer deletePost(Integer ownerId, Integer postId) throws UnirestException {
        Map<String, String> headers = new HashMap<>();
        headers.put("accept", "application/json");
        headers.put("Authorization", "Bearer " + DataProperties.getDataPropertyByKey("userAccessToken"));

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("owner_id", ownerId);
        parameters.put("post_id", postId);
        parameters.put("v", ConfigurationData.getConfigurationPropertyByKey("vkApiVersion"));

        HttpResponse<JsonNode> jsonResponse
                = Unirest.get(ConfigurationData.getConfigurationPropertyByKey("vkApiUrl") +
                UrlPath.WALL_DELETE)
                .headers(headers)
                .queryString(parameters)
                .asJson();

        return jsonResponse.getBody().getObject().getInt(RESPONSE_OBJECT_KEY);
    }
}

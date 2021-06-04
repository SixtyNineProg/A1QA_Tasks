package by.a1qa.klimov.api;

import by.a1qa.klimov.api.utils.APIUtils;
import by.a1qa.klimov.models.RequestResult;
import by.a1qa.klimov.models.User;
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
    private static final String ITEMS_OBJECT_KEY = "items";
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

    public Photo uploadPicture(Integer userId, String fieldName, File uploadFile) throws UnirestException {
        String uploadUrl = getUploadServer(userId);

        JsonNode jsonNode = uploadPictureToServer(uploadUrl, fieldName, uploadFile);
        String server = jsonNode.getObject().get("server").toString();
        String photo = jsonNode.getObject().get("photo").toString();
        String hash = jsonNode.getObject().get("hash").toString();

        List<Photo> photos = savePictures(userId, server, photo, hash);
        if (photos.size() != 0) return photos.get(0);
        else throw new NullPointerException();
    }

    private List<Photo> savePictures(
            Integer userId, String server, String photo, String hash) throws UnirestException {
        Map<String, String> headers = new HashMap<>();
        headers.put("accept", "application/json");
        headers.put("Authorization", "Bearer " + DataProperties.getDataPropertyByKey("userAccessToken"));

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("user_id", userId);
        parameters.put("server", server);
        parameters.put("photo", photo);
        parameters.put("hash", hash);
        parameters.put("v", ConfigurationData.getConfigurationPropertyByKey("vkApiVersion"));

        HttpResponse<JsonNode> jsonResponse = Unirest.get(ConfigurationData.getConfigurationPropertyByKey("vkApiUrl") +
                UrlPath.PHOTOS_SAVE_WALL_PHOTO)
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
            String uploadUrl, String fieldName, File uploadFile) throws UnirestException {
        HttpResponse<JsonNode> jsonResponse = Unirest.post(
                uploadUrl)
                .field(fieldName, uploadFile)
                .asJson();

        Assert.assertEquals(jsonResponse.getStatus(), HttpURLConnection.HTTP_OK,
                "Response code does not match" + HttpURLConnection.HTTP_OK);
        JsonNode jsonNode = jsonResponse.getBody();
        Assert.assertNotNull(jsonNode);
        return jsonNode;
    }

    private String getUploadServer(Integer userId) throws UnirestException {
        Map<String, String> headers = new HashMap<>();
        headers.put("accept", "application/json");
        headers.put("Authorization", "Bearer " + DataProperties.getDataPropertyByKey("userAccessToken"));

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("user_id", userId);
        parameters.put("v", ConfigurationData.getConfigurationPropertyByKey("vkApiVersion"));

        HttpResponse<JsonNode> jsonResponse
                = Unirest.get(ConfigurationData.getConfigurationPropertyByKey("vkApiUrl") +
                UrlPath.PHOTOS_GET_WALL_UPLOAD_SERVER)
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

    public Photo getPhoto(Integer ownerId, ServiceAlbum albumId, Integer photoId) throws UnirestException {
        Map<String, String> headers = new HashMap<>();
        headers.put("accept", "application/json");
        headers.put("Authorization", "Bearer " + DataProperties.getDataPropertyByKey("userAccessToken"));

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("owner_id", ownerId);
        parameters.put("album_id", albumId.toString().toLowerCase());
        parameters.put("photo_ids", photoId);
        parameters.put("v", ConfigurationData.getConfigurationPropertyByKey("vkApiVersion"));

        HttpResponse<JsonNode> jsonResponse
                = Unirest.get(ConfigurationData.getConfigurationPropertyByKey("vkApiUrl") +
                UrlPath.PHOTOS_GET)
                .headers(headers)
                .queryString(parameters)
                .asJson();

        JSONObject body = new JSONObject(jsonResponse.getBody().toString());
        JSONObject response = body.getJSONObject(RESPONSE_OBJECT_KEY);
        JSONArray items = response.getJSONArray(ITEMS_OBJECT_KEY);
        List<Photo> photos = JsonUtils.toObjectsList(items, Photo.class);
        if (photos.size() != 0) return photos.get(0);
        else throw new NullPointerException("Photo not found");
    }
}

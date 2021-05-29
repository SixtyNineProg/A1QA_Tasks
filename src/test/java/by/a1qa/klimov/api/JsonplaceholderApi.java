package by.a1qa.klimov.api;

import aquality.selenium.core.utilities.JsonSettingsFile;

public class JsonplaceholderApi {
    private static final String BASE_URL =
            new JsonSettingsFile("settings.json").getValue("/url").toString();

//    public List<Post> readAllPosts(int expectedRequestCode) {
//        RequestResult requestResult = APIUtils
//                .doGetRequest(BASE_URL + UrlPath.POSTS);
//        Assert.assertEquals(requestResult.getCode(), expectedRequestCode,
//                "Response code does not match" + expectedRequestCode);
//        Assert.assertTrue(JsonUtils.isStringJsonArray(requestResult.getAnswer()),
//                "The answer does not match the JSON format");
//        return JsonUtils.toObjectsList(requestResult.getAnswer(), Post.class);
//    }

//    public Post readPost(int id, int expectedRequestCode) {
//        RequestResult requestResult = APIUtils
//                .doGetRequest(BASE_URL + UrlPath.POSTS_ID + id);
//        Assert.assertEquals(requestResult.getCode(), expectedRequestCode,
//                "Response code does not match" + expectedRequestCode);
//        try {
//            return JsonUtils.toObject(requestResult.getAnswer(), Post.class);
//        } catch (IllegalArgumentException e) {
//            Logger.getInstance().fatal("Does not fit JSON format", e);
//            return null;
//        }
//    }

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

//    public List<User> readAllUsers(int expectedRequestCode) {
//        RequestResult requestResult = APIUtils
//                .doGetRequest(BASE_URL + UrlPath.USERS);
//        Assert.assertEquals(requestResult.getCode(), expectedRequestCode,
//                "Response code does not match" + expectedRequestCode);
//        Assert.assertTrue(JsonUtils.isStringJsonArray(requestResult.getAnswer()),
//                "The answer does not match the JSON format");
//        return JsonUtils.toObjectsList(requestResult.getAnswer(), User.class);
//    }

//    public User readUser(int id, int expectedRequestCode) {
//        RequestResult requestResult = APIUtils
//                .doGetRequest(BASE_URL + UrlPath.USERS_ID + id);
//        Assert.assertEquals(requestResult.getCode(), expectedRequestCode,
//                "Response code does not match" + expectedRequestCode);
//        try {
//            return JsonUtils.toObject(requestResult.getAnswer(), User.class);
//        } catch (IllegalArgumentException e) {
//            Logger.getInstance().fatal("Does not fit JSON format", e);
//            return null;
//        }
//    }
}

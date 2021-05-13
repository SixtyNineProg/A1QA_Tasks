package by.a1qa.klimov.api;

import by.a1qa.klimov.models.Post;
import by.a1qa.klimov.models.User;

import java.util.List;

public interface IJsonplaceholderApi {
    List<Post> readAllPosts(int expectedRequestCode);
    Post readPost(int id, int expectedRequestCode);
    Post createPost(Post post, int expectedRequestCode);
    List<User> readAllUsers(int expectedRequestCode);
    User readUser(int id, int expectedRequestCode);
}

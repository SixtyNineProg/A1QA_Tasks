package by.a1qa.klimov.api;

import by.a1qa.klimov.models.Post;

import java.util.List;

public interface IJsonplaceholderApi {
    List<Post> readAllPosts(int expectedRequestCode);
}

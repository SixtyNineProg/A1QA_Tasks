package by.a1qa.klimov.utils;

import java.net.MalformedURLException;
import java.net.URL;

public class NetworkUtils {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    private static final String ALL_POSTS = "posts";

    public static URL createAllPostsUrl() throws MalformedURLException {
        return new URL(BASE_URL + ALL_POSTS);
    }
}

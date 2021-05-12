package by.a1qa.klimov.service;

import by.a1qa.klimov.models.Post;

import java.util.List;

public interface IPostService {
    List<Post> readAll();
    Post read(int id);
}

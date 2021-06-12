package by.a1qa.klimov.api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    Integer userId;
    Integer id;
    String title;
    String body;
}

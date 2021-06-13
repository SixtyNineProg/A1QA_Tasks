package by.a1qa.klimov.jdbc.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"id", "name", "login", "email"})
public class Author {
    private Long id;
    private String name;
    private String login;
    private String email;
}

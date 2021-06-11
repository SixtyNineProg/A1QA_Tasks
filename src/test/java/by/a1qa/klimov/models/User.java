package by.a1qa.klimov.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    Integer id;
    String name;
    String username;
    String email;
    @JsonProperty("address")
    Address address;
    String phone;
    String website;
    @JsonProperty("company")
    Company company;

    public static User getUserById(List<User> users, int userId) {
        return users.stream().filter(p -> p.getId() == userId).findAny().orElse(null);
    }
}

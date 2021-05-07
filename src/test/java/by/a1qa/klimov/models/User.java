package by.a1qa.klimov.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}

package by.a1qa.klimov.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String first_name;
    private Integer id;
    private String last_name;
    private Boolean can_access_closed;
    private Boolean is_closed;
}

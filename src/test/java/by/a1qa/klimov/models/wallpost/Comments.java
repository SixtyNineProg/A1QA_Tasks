package by.a1qa.klimov.models.wallpost;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comments {
    Integer count;
    Integer can_post;
    Boolean groups_can_post;
    Integer can_close;
}

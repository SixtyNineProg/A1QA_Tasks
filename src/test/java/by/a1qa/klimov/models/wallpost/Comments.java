package by.a1qa.klimov.models.wallpost;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comments {
    private Integer count;
    private Integer can_post;
    private Boolean groups_can_post;
    private Integer can_close;
}

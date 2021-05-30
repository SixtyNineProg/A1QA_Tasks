package by.a1qa.klimov.models.wallpost;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reposts {
    private Integer count;
    private Integer wall_count;
    private Integer mail_count;
    private Integer user_reposted;
}

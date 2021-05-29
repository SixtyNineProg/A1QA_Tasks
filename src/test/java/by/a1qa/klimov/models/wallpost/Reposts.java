package by.a1qa.klimov.models.wallpost;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reposts {
    Integer count;
    Integer wall_count;
    Integer mail_count;
    Integer user_reposted;
}

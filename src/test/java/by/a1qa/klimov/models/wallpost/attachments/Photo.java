package by.a1qa.klimov.models.wallpost.attachments;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Photo {
    Integer album_id;
    Integer date;
    Integer id;
    Integer owner_id;
    Boolean has_tags;
    String access_key;
    @JsonProperty("sizes")
    List<Size> sizes;
    String text;
}

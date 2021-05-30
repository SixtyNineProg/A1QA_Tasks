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
    private Integer album_id;
    private Integer date;
    private Integer id;
    private Integer owner_id;
    private Boolean has_tags;
    private String access_key;
    @JsonProperty("sizes")
    private List<Size> sizes;
    private String text;
}

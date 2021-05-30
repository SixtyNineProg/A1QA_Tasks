package by.a1qa.klimov.models.wallpost.attachments;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attachment {
    private String type;
    @JsonProperty("photo")
    private Photo photo;
}

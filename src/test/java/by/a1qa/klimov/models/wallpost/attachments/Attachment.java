package by.a1qa.klimov.models.wallpost.attachments;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attachment {
    String type;
    @JsonProperty("photo")
    Photo photo;
}

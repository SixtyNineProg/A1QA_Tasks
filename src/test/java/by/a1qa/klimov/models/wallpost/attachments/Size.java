package by.a1qa.klimov.models.wallpost.attachments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Size {
    Integer height;
    String url;
    String type;
    Integer width;
}

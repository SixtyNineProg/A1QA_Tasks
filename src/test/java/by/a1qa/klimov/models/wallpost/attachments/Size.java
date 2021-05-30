package by.a1qa.klimov.models.wallpost.attachments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Size {
    private Integer height;
    private String url;
    private String type;
    private Integer width;
}
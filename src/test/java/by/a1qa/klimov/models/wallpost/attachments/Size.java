package by.a1qa.klimov.models.wallpost.attachments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Size {
    private Integer height;
    private String url;
    private String type;
    private Integer width;

    public static Size getSizeWithParameter(List<Size> sizeList, int height, int wight) {
        return sizeList.stream()
                .filter(size -> (size.getHeight() == height && size.getWidth() == wight))
                .findAny()
                .orElse(null);
    }
}

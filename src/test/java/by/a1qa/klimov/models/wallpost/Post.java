package by.a1qa.klimov.models.wallpost;

import by.a1qa.klimov.models.wallpost.attachments.Attachment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private Integer id;
    private Integer from_id;
    private Integer owner_id;
    private Integer date;
    private String post_type;
    private String text;
    @Nullable
    private Integer can_edit;
    private Integer can_delete;
    private Integer can_pin;
    private Boolean can_archive;
    private Boolean is_archived;
    @JsonProperty("attachments")
    private List<Attachment> attachments;
    @JsonProperty("post_source")
    private PostSource post_source;
    @JsonProperty("comments")
    private Comments comments;
    @JsonProperty("likes")
    private Likes likes;
    @JsonProperty("reposts")
    private Reposts reposts;
    @JsonProperty("views")
    private Views views;
    private Boolean is_favorite;
    @JsonProperty("donut")
    private Donut donut;
    private Double short_text_rate;
    @Nullable
    private Integer edited;

    public static Post getPost(List<Post> posts, String text) {
        return posts.stream().filter(post -> text.equals(post.getText())).findAny().orElse(null);
    }
}

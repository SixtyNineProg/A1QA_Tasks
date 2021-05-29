package by.a1qa.klimov.models.wallpost;

import by.a1qa.klimov.models.wallpost.attachments.Attachment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    Integer id;
    Integer from_id;
    Integer owner_id;
    Integer date;
    String post_type;
    String text;
    @Nullable
    Integer can_edit;
    Integer can_delete;
    Integer can_pin;
    Boolean can_archive;
    Boolean is_archived;
    @JsonProperty("attachments")
    List<Attachment> attachments;
    @JsonProperty("post_source")
    PostSource post_source;
    @JsonProperty("comments")
    Comments comments;
    @JsonProperty("likes")
    Likes likes;
    @JsonProperty("reposts")
    Reposts reposts;
    @JsonProperty("views")
    Views views;
    Boolean is_favorite;
    @JsonProperty("donut")
    Donut donut;
    Double short_text_rate;
    @Nullable
    Integer edited;

    public static Integer getPostId(List<Post> posts, String text) {
        return Objects.requireNonNull(
                posts.stream().filter(post -> text.equals(post.getText())).findAny().orElse(null))
                .getId();
    }
}

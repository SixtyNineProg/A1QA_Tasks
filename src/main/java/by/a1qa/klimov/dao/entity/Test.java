package by.a1qa.klimov.dao.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({
        "id",
        "name",
        "status_id",
        "method_name",
        "project_id",
        "session_id",
        "start_time",
        "end_time",
        "env",
        "browser",
        "author_id",})
public class Test {
    private Long id;
    private String name;
    @JsonProperty("status_id")
    private Integer statusId;
    @JsonProperty("method_name")
    private String methodName;
    @JsonProperty("project_id")
    private Integer projectId;
    @JsonProperty("session_id")
    private Integer sessionId;
    @JsonProperty("start_time")
    private Timestamp startTime;
    @JsonProperty("end_time")
    private Timestamp endTime;
    private String env;
    private String browser;
    @JsonProperty("author_id")
    private Integer authorId;
}

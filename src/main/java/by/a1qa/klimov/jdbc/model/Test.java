package by.a1qa.klimov.jdbc.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

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
    Long id;
    String name;
    @JsonProperty("status_id")
    Integer statusId;
    @JsonProperty("method_name")
    String methodName;
    @JsonProperty("project_id")
    Integer projectId;
    @JsonProperty("session_id")
    Integer sessionId;
    @JsonProperty("start_time")
    Timestamp startTime;
    @JsonProperty("end_time")
    Timestamp endTime;
    String env;
    String browser;
    @JsonProperty("author_id")
    Integer authorId;
}

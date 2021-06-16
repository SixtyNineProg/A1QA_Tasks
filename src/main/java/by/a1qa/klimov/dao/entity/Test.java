package by.a1qa.klimov.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "test")
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "status_id")
    private Long statusId;
    @Column(name = "method_name")
    private String methodName;
    @Column(name = "project_id")
    private Long projectId;
    @Column(name = "session_id")
    private Long sessionId;
    @Column(name = "start_time")
    private Timestamp startTime;
    @Column(name = "end_time")
    private Timestamp endTime;
    private String env;
    private String browser;
    @Column(name = "author_id")
    private Long authorId;
}

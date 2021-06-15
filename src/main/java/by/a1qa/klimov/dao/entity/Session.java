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
@Table(name = "session")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "session_key")
    private String sessionKey;
    @Column(name = "created_time")
    private Timestamp createdTime;
    @Column(name = "build_number")
    private Long buildNumber;
}

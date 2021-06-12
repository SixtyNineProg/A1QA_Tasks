package by.a1qa.klimov.jdbc.dao.impl;

import by.a1qa.klimov.jdbc.connection.ConnectionDb;
import by.a1qa.klimov.jdbc.connection.exception.ConnectException;
import by.a1qa.klimov.jdbc.dao.interfaces.ICrud;
import by.a1qa.klimov.jdbc.model.Test;

import java.sql.*;

public class TestDao implements ICrud<Test> {
    @Override
    public long create(Test test) {
        final String sqlRequest =
                "INSERT test(" +
                        "name," +
                        " status_id," +
                        " method_name," +
                        " project_id," +
                        " session_id," +
                        " start_time," +
                        " end_time," +
                        " env," +
                        " browser," +
                        " author_id)"
                        + "VALUES(?,?,?,?,?,?,?,?,?,?)";

        long id = 0;

        try (Connection connection = ConnectionDb.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest,
                     Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, test.getName());
            preparedStatement.setInt(2, test.getStatusId());
            preparedStatement.setString(3, test.getMethodName());
            preparedStatement.setInt(4, test.getProjectId());
            preparedStatement.setInt(5, test.getSessionId());
            preparedStatement.setTimestamp(6, test.getStartTime());
            preparedStatement.setTimestamp(7, test.getEndTime());
            preparedStatement.setString(8, test.getEnv());
            preparedStatement.setString(9, test.getBrowser());
            preparedStatement.setInt(10, test.getAuthorId());
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    id = rs.getLong(1);
                }
            }
        } catch (SQLException e) {
            throw new ConnectException("SQL exception.");
        }
        return id;
    }

    @Override
    public Test read(long id) {
        return null;
    }

    @Override
    public long update(Test object) {
        return 0;
    }

    @Override
    public long delete(long id) {
        return 0;
    }
}


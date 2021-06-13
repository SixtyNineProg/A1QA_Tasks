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
             PreparedStatement statement = connection.prepareStatement(sqlRequest,
                     Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, test.getName());
            statement.setInt(2, test.getStatusId());
            statement.setString(3, test.getMethodName());
            statement.setInt(4, test.getProjectId());
            statement.setInt(5, test.getSessionId());
            statement.setTimestamp(6, test.getStartTime());
            statement.setTimestamp(7, test.getEndTime());
            statement.setString(8, test.getEnv());
            statement.setString(9, test.getBrowser());
            statement.setInt(10, test.getAuthorId());
            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                ResultSet rs = statement.getGeneratedKeys();
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
        final String sqlRequest = "SELECT * FROM test WHERE id = ?";

        try (Connection connection = ConnectionDb.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlRequest)) {

            statement.setLong(1, id);
            statement.execute();

            ResultSet queryResult = statement.getResultSet();
            queryResult.next();
            return new Test(
                    queryResult.getLong("id"),
                    queryResult.getString("name"),
                    queryResult.getInt("status_id"),
                    queryResult.getString("method_name"),
                    queryResult.getInt("project_id"),
                    queryResult.getInt("session_id"),
                    queryResult.getTimestamp("start_time"),
                    queryResult.getTimestamp("end_time"),
                    queryResult.getString("env"),
                    queryResult.getString("browser"),
                    queryResult.getInt("author_id")
            );
        } catch (SQLException e) {
            throw new ConnectException("SQL exception.");
        }
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


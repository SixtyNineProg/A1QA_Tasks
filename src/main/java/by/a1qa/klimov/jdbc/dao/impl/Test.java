package by.a1qa.klimov.jdbc.dao.impl;

import by.a1qa.klimov.jdbc.connection.ConnectionDb;
import by.a1qa.klimov.jdbc.connection.exception.ConnectException;
import by.a1qa.klimov.jdbc.dao.interfaces.ICrud;
import by.a1qa.klimov.jdbc.model.Test;

import java.sql.*;

class TestDao implements ICrud<Test> {
    @Override
    public long create(Test test) {
        final String sqlRequest = "INSERT INTO test(first_name,last_name) "
                + "VALUES(?,?)";

        long id = 0;

        try (Connection connection = ConnectionDb.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest,
                     Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(2, test.getName());
            preparedStatement.setInt(3, test.getStatusId());
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

package by.a1qa.klimov.jdbc.connection;

import aquality.selenium.core.utilities.JsonSettingsFile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class ConnectionDb {
    private static Connection connection;
    private static final Map<String, Object> dataBaseInfo =
            new JsonSettingsFile("settings.json").getMap("/dataBase");

    public static Connection getConnection() throws SQLException {
        connection = DriverManager.getConnection(
                dataBaseInfo.get("connectionUrl").toString(),
                dataBaseInfo.get("userName").toString(),
                dataBaseInfo.get("password").toString());
        return connection;
    }
}

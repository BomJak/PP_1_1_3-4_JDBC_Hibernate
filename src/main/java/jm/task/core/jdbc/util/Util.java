package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String HOST_NAME = "localhost";
    private static final String DATABASE = "mydb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "testuser";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String connectionURL = "jdbc:mysql://" + HOST_NAME + ":3306/" + DATABASE;
            conn = DriverManager.getConnection(connectionURL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}

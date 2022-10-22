package peaksoft.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static String user = "postgres";
    private static String password = "1234";
    private static String url = "jdbc:postgresql://localhost:5432/postgres";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }


}

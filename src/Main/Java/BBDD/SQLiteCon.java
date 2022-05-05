package Main.Java.BBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Main.Java.Tools.Logger;

public class SQLiteCon {
    private static Connection conn = null;

    // Datos de acceso a la BD
    private final String driver = "org.sqlite.JDBC";
    private final String dsn = "jdbc:sqlite:data.db";

    private SQLiteCon() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(dsn);
        } catch (ClassNotFoundException | SQLException e) {
            Logger.log(e.getMessage());
        }
    }

    public static Connection getConnection() {
        if (conn == null) {
            new SQLiteCon();
        }
        return conn;
    }

    public static void close() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                Logger.log(e.getMessage());
            }
        }
    }
}

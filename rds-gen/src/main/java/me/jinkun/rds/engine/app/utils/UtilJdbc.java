package me.jinkun.rds.engine.app.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Description: HelloWorld！ <br/>
 * Autor: Created by jinkun on 2016/11/30.
 */
public class UtilJdbc {
    private static Connection conn;

    public static synchronized Connection getConnect(String driver, String url, String username, String password) {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static synchronized void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                conn = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

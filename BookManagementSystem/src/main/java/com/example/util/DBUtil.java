package com.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.io.InputStream;

public class DBUtil {
    private static Connection connection = null;
    public static Connection getConnection() {
        if (connection != null) return connection;
        try (InputStream input = DBUtil.class.getClassLoader().getResourceAsStream("db.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            String url = prop.getProperty("db.url");
            String user = prop.getProperty("db.username");
            String password = prop.getProperty("db.password");
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}

package org.example.blackjack.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//para los jdbc, faltaria adaptar la url y credenciales
public class JdbcConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/blackjack";
    private static final String USER = "user";
    private static final String PASSWORD = "1234";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // o el driver que toque
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("No se ha podido cargar el driver JDBC", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
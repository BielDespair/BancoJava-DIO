package com.leonel.banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    public static Connection getConnection() {
        String path = "database.db";
        String url = "jdbc:sqlite:" + path;
        Connection conn = null;

        try {

            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {

            throw new RuntimeException("Erro ao conectar ao banco de dados: " + e.getMessage(), e);
        }

        return conn;
    }
}

package com.leonel.banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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

    public static void createDatabase() {
        String createTableConta =
                "CREATE TABLE IF NOT EXISTS conta (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "numeroConta INTEGER NOT NULL UNIQUE," +
                "nome TEXT NOT NULL," +
                "cpf TEXT NOT NULL UNIQUE," +
                "saldo REAL NOT NULL," +
                "hashSenha TEXT" +
                ");";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(createTableConta);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar o banco de dados: " + e.getMessage(), e);
        }
    }
}

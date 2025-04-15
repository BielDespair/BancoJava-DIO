package com.leonel.banco.conta;


import java.sql.Connection;

public class ContaDAO {
    private Connection dbConnection;

    public ContaDAO(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void save(Conta conta) {

    }
}

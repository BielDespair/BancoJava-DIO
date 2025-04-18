package com.leonel.banco;

import com.leonel.banco.conta.ContaDAO;
import com.leonel.banco.conta.ContaService;
import com.leonel.banco.controller.BancoController;
import com.leonel.banco.controller.ContaController;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        Database.createDatabase();
        Connection dbConn = Database.getConnection();
        ContaDAO contaDAO = new ContaDAO(dbConn);

        ContaService contaService = new ContaService(contaDAO);
        ContaController contaController = new ContaController(contaService);


        BancoController bancoController = new BancoController(contaController);

        bancoController.exibirMenu();
    }
}
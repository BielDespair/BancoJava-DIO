package com.leonel.banco;

import com.leonel.banco.conta.ContaService;
import com.leonel.banco.controller.BancoController;
import com.leonel.banco.controller.ContaController;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        ContaService contaService = new ContaService();
        ContaController contaController = new ContaController();


        BancoController bancoController = new BancoController(contaController);
    }
}
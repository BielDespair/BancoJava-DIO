package com.leonel.banco.controller;

import com.leonel.banco.conta.ContaDAO;
import com.leonel.banco.conta.ContaService;

public class ContaController {
    private ContaService contaService = new ContaService();

    public ContaController(ContaService contaService) {
        this.contaService = contaService;

    }

    public void abrirConta() {
        String nome;
        String cpf;

        System.out.println("=== Abertura de Conta ===");


        if (contaService.abrirConta(nome, cpf)) {
            System.out.println("Conta aberta com sucesso!");
        }
    }
}

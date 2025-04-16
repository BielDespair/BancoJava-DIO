package com.leonel.banco.controller;

import com.leonel.banco.conta.Conta;
import com.leonel.banco.conta.ContaDAO;
import com.leonel.banco.conta.ContaService;
import com.leonel.banco.util.ValidadorCpf;

import java.util.Scanner;

public class ContaController {
    private final ContaService contaService;
    private final Scanner scanner;

    private Conta contaAtual;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
        this.scanner = new Scanner(System.in);

    }

    public void abrirConta() {
        String cpf;
        String nome;
        String senha;

        System.out.println("=== Abertura de Conta ===");
        System.out.println("Digite o CPF da conta: ");
        cpf = scanner.nextLine();
        if (!ValidadorCpf.validarCpf(cpf)) {
            System.out.println("CPF inválido. Por favor, tente novamente.");
            return;
        }

        if (contaService.contaExiste((cpf))) {
            System.out.println("Já existe uma conta com esse CPF");
            return;
        }

        System.out.println("Digite o nome completo do titular: ");
        nome = scanner.nextLine();
        System.out.println("Escolha uma senha de acesso: ");
        senha = scanner.nextLine();

        try {
            contaService.abrirConta(cpf, nome, senha);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}

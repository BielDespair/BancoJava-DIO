package com.leonel.banco.controller;

import com.leonel.banco.conta.Conta;
import com.leonel.banco.conta.ContaService;
import com.leonel.banco.conta.exception.ContaNaoExisteException;
import com.leonel.banco.conta.exception.SaldoInsuficienteException;
import com.leonel.banco.conta.exception.SenhaIncorretaException;
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

    public void acessarConta() {
        System.out.println("=== Acessar Conta ===");
        System.out.println("Digite o número da conta: ");
        int numeroConta = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Digite a senha de acesso: ");
        String senha = scanner.nextLine();

        try {
            contaAtual = contaService.acessarConta(numeroConta, senha);
        } catch (ContaNaoExisteException | SenhaIncorretaException e) {
            System.out.println(e.getMessage());
            encerrarSessao();
            return;
        }

        menuSessao();
        encerrarSessao(); // Logout da conta após encerrar operações.


    }

    private void atualizarContaSessao() {
        this.contaAtual = contaService.getConta(this.contaAtual);
    }

    private void encerrarSessao() {
        this.contaAtual = null;
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

        if (contaService.contaExisteCpf((cpf))) {
            System.out.println("Já existe uma conta com este CPF");
            return;
        }

        System.out.println("Digite o nome completo do titular: ");
        nome = scanner.nextLine();
        System.out.println("Escolha uma senha de acesso: ");
        senha = scanner.nextLine();

        try {
            Conta novaConta = contaService.abrirConta(cpf, nome, senha);
            System.out.println("Conta aberta com sucesso!");
            System.out.println("Anote o número da conta para consultá-la posteriormente: ");
            System.out.println("Número da conta: " + novaConta.numeroConta());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }


    }

    private void consultarSaldo() {
        System.out.println("Saldo atual: " + contaAtual.saldo());
    }

    private void sacar() {
        System.out.println("Digite a quantidade que deseja sacar: ");
        double quantidade = scanner.nextDouble();
        try {
            contaService.sacar(contaAtual, quantidade);
            atualizarContaSessao();
            System.out.println("Saque efetuado com sucesso!");
            System.out.println("Novo saldo: " + contaAtual.saldo());
        } catch (SaldoInsuficienteException e) {
            System.out.println(e.getMessage());
        }
    }
    private void menuSessao() {
        int opcao;

        do {
            System.out.println("=== Bem-vindo, " + contaAtual.nome() + "===");
            System.out.println("1 - Consultar Saldo");
            System.out.println("2 - Sacar");
            System.out.println("3 - Depositar");
            System.out.println("4 - Transferir");
            System.out.println("5 - Mudar senha de acesso");
            System.out.println("0 - Sair da conta");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    consultarSaldo();
                    break;
                case 2:
                    sacar();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }

            System.out.println("Pressione Enter para continuar...");
            scanner.nextLine();
            System.out.println("\n\n\n");
        } while (true);

    }


}

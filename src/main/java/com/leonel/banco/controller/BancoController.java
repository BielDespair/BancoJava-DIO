package com.leonel.banco.controller;

import com.leonel.banco.conta.Conta;

import java.util.Scanner;

public class BancoController {
    private final Scanner scanner;
    private final ContaController contaController;

    public BancoController(ContaController contaController) {
        this.contaController = contaController;
        this.scanner = new Scanner(System.in);
    }


    public void exibirMenu() {
        int opcao;

        do {

            System.out.println("=== Menu Banco ===");
            System.out.println("1 - Acessar Conta");
            System.out.println("2 - Abrir Conta");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    contaController.acessarConta();
                    break;
                case 2:
                    contaController.abrirConta();
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
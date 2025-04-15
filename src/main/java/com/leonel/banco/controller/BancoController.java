package com.leonel.banco.controller;

import java.util.Scanner;

public class BancoController {
    private final Scanner scanner = new Scanner(System.in);
    private final ContaController contaController;

    public BancoController(ContaController contaController) {
        this.contaController = contaController;
    }


    public void exibirMenu() {

        System.out.println("=== Menu Banco ===");
        System.out.println("1 - Abrir Conta");
        System.out.println("2 - Consultar Saldo");
        System.out.println("3 - Sacar");
        System.out.println("4 - Depositar");
        System.out.println("5 - Sair");
        System.out.print("Escolha uma opção: ");

        int opcao;
        do {
            switch (opcao = scanner.nextInt()) {
                case 1:
                    contaController.criarConta();
                    break;
                case 2:
                    contaController.consultarSaldo();
                    break;
                case 3:
                    contaController.sacar();
                    break;
                case 4:
                    contaController.depositar();
            }
        } while (opcao != 5);

    }


    public void limparTela() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
}
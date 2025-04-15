package com.leonel.banco.conta;

public record Conta(int id, int numeroConta, String nome, String cpf, double saldo, String hashSenha) {}

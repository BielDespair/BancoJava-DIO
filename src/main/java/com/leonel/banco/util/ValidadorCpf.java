package com.leonel.banco.util;

public class ValidadorCpf {
    public static boolean validarCpf(String cpf) {
        if (cpf.length() != 11) {
            return false;
        }

        char[] digitos = cpf.toCharArray();

        for (char digito : digitos) {
            if (!Character.isDigit(digito)) {
                return false;
            }
        }

        // TODO: Implementar algoritmo de verificação de CPF

        return true; // Passou por toda validação.
    }
}

package com.leonel.banco.util;

public class ValidadorCpf {
    public static boolean validarCpf(String cpf) {
        if (cpf.length() != 11) {
            return false;
        }

        char[] digitos = cpf.toCharArray();
        // Implementar



        return true;
    }
}

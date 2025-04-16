package com.leonel.banco.conta.exception;

public class CpfInvalidoException extends RuntimeException {
    public CpfInvalidoException() { super("CPF inválido. Por favor, tente novamente."); }

    public CpfInvalidoException(String message) {
        super(message);
    }
}

package com.leonel.banco.conta.exception;

public class SenhaIncorretaException extends RuntimeException {
    public SenhaIncorretaException() {
        super("Senha incorreta.");
    }
    public SenhaIncorretaException(String message) {
        super(message);
    }
}

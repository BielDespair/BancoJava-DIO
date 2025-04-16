package com.leonel.banco.conta.exception;

public class SaldoInsuficienteException extends RuntimeException {
    public SaldoInsuficienteException() {
        super("Saldo insuficiente. Por favor, consulte seu saldo.");
    }
    public SaldoInsuficienteException(String message) {
        super(message);
    }
}

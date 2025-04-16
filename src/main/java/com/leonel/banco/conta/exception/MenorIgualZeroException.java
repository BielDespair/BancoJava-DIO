package com.leonel.banco.conta.exception;

public class MenorIgualZeroException extends IllegalArgumentException {
    public MenorIgualZeroException() {
        super("O valor deve ser maior que zero.");
    }
    public MenorIgualZeroException(String message) {
        super(message);
    }
}

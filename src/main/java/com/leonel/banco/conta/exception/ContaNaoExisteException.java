package com.leonel.banco.conta.exception;

public class ContaNaoExisteException extends RuntimeException {
  public ContaNaoExisteException() {
    super("Conta não encontrada");
  }
    public ContaNaoExisteException(String message) {
        super(message);
    }
}

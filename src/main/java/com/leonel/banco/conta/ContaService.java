package com.leonel.banco.conta;

import com.leonel.banco.conta.exception.*;
import com.leonel.banco.security.PasswordHasher;



public class ContaService {
    private final ContaDAO contaDAO;

    public ContaService(ContaDAO contaDAO) {
        this.contaDAO = contaDAO;
    }

    public Conta acessarConta(int numero, String senha) throws ContaNaoExisteException, SenhaIncorretaException {
        Conta conta = contaDAO.findByNumeroConta(numero);

        if (PasswordHasher.validatePassword(senha, conta.hashSenha())) {
            return conta;
        } else {
            throw new SenhaIncorretaException();
        }

    }

    public Conta getConta(Conta conta) {
        return contaDAO.findByNumeroConta(conta.numeroConta());
    }

    public String getTitular(int numeroConta) {
        Conta conta = contaDAO.findByNumeroConta(numeroConta);
        return conta.nome();
    }

    public Conta abrirConta(String cpf, String nome, String senha) {
        contaDAO.save(new Conta(0, 0, nome, cpf, 0.00, PasswordHasher.hashPassword(senha)));
        gerarNumeroConta(cpf);
        return contaDAO.findByCpf(cpf);
    }

    private void gerarNumeroConta(String cpf) {

        Conta conta = contaDAO.findByCpf(cpf);

        String ultimosDigitos = conta.cpf().substring(8);
        int numeroConta = Integer.parseInt(ultimosDigitos + conta.id());
        contaDAO.updateNumeroConta(conta.id(), numeroConta);
    }


    public void sacar(Conta conta, double quantidade) {
        if (quantidade <= 0) { throw new MenorIgualZeroException(); }
        if (conta.saldo() < quantidade) { throw new SaldoInsuficienteException(); }

        double novoSaldo = conta.saldo() - quantidade;
        contaDAO.updateSaldo(conta.id(), novoSaldo);
    }

    public void depositar(Conta conta, double quantidade) {
        if (quantidade <= 0) {throw new MenorIgualZeroException();}
        contaDAO.updateSaldo(conta.id(), conta.saldo() + quantidade);
    }

    public void transferir(Conta conta, int numeroContaDestino, double quantidade) {
        if (quantidade <= 0) {throw new MenorIgualZeroException();}
        if (conta.saldo() < quantidade) {throw new SaldoInsuficienteException();}
        if (conta.numeroConta() == numeroContaDestino) {throw new TransferenciaInvalidaException("Não pode transferir para a própria conta");}

        Conta contaDestino = contaDAO.findByNumeroConta(numeroContaDestino);

        contaDAO.updateSaldo(contaDestino.id(), contaDestino.saldo() + quantidade);
        contaDAO.updateSaldo(conta.id(), conta.saldo() - quantidade);
    }


    public boolean contaExisteCpf(String cpf) {
        return contaDAO.existsByCpf(cpf);
    }

    public boolean contaExiste(int numeroConta) {
        return contaDAO.existsByNumeroConta(numeroConta);
    }


    private void mudarSenha(String senhaAtual, String senhaNova) {

    }
}
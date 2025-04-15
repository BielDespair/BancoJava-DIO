package com.leonel.banco.conta;

public class ContaService {
    private ContaDAO contaDAO;

    public ContaService(ContaDAO contaDAO) {
        this.contaDAO = contaDAO;
    }

    public void abrirConta(String nome, String cpf) {

        contaDAO.save(new Conta(0, 0, nome, cpf, 0, null));
    }

    private void mudarSenha(String senhaAtual, String senhaNova) {

    }
}

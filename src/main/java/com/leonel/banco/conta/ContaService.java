package com.leonel.banco.conta;

public class ContaService {
    private final ContaDAO contaDAO;

    public ContaService(ContaDAO contaDAO) {
        this.contaDAO = contaDAO;
    }

    public void abrirConta(String cpf, String nome, String senha) {

        contaDAO.save(new Conta(0, 0, nome, cpf, 0, null));
    }

    public boolean contaExiste(String cpf) {
        return true;
    }

    private void mudarSenha(String senhaAtual, String senhaNova) {

    }
}

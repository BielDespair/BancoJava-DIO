package com.leonel.banco.conta;


import com.leonel.banco.Database;
import com.leonel.banco.conta.exception.ContaNaoExisteException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContaDAO {
    private final Connection dbConnection;

    public ContaDAO(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void save(Conta conta) {
        String query = "INSERT INTO conta (numeroConta, nome, cpf, saldo, hashSenha) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {

            stmt.setInt(1, conta.numeroConta());
            stmt.setString(2, conta.nome());
            stmt.setString(3, conta.cpf());
            stmt.setDouble(4, conta.saldo());
            stmt.setString(5, conta.hashSenha());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateNumeroConta(int contaId, int numeroConta) {
        String query = "UPDATE conta SET numeroConta = ? WHERE id = ?";
        try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
            stmt.setInt(1, numeroConta);
            stmt.setInt(2, contaId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void updateSaldo(int contaId, double saldo) {
        String query = "UPDATE conta SET saldo = ? WHERE id = ?";
        try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
            stmt.setDouble(1, saldo);
            stmt.setInt(2, contaId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Conta findByCpf(String cpf) throws ContaNaoExisteException {
        String query = "SELECT * FROM conta WHERE cpf = ?";

        try (PreparedStatement stmt = dbConnection.prepareStatement(query))  {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return (new Conta(
                        rs.getInt("id"),
                        rs.getInt("numeroConta"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getDouble("saldo"),
                        rs.getString("hashSenha")
                ));
            }
            throw new ContaNaoExisteException();


        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar conta", e);
        }
    }

    public Conta findByNumeroConta(int numeroConta) throws ContaNaoExisteException {
        String query = "SELECT * FROM conta WHERE numeroConta = ?";
        Conta conta = null;

        try (PreparedStatement stmt = dbConnection.prepareStatement(query))  {
            stmt.setInt(1, numeroConta);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return (new Conta(
                        rs.getInt("id"),
                        rs.getInt("numeroConta"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getDouble("saldo"),
                        rs.getString("hashSenha")
                ));
            }
            throw new ContaNaoExisteException();


        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar conta", e);
        }
    }

    public boolean existsByCpf(String cpf) {
        String sql = "SELECT 1 FROM conta WHERE cpf = ? LIMIT 1";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar existência da conta por CPF", e);
        }
    }

    public boolean existsByNumeroConta(int numeroConta) {
        String sql = "SELECT 1 FROM conta WHERE numeroConta = ? LIMIT 1";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, numeroConta);
            ResultSet rs = stmt.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar existência da conta.", e);
        }
    }
}

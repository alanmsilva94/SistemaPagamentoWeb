package br.com.senac.sistemapagamento.dao;

import br.com.senac.sistemapagamento.models.Login;
import br.com.senac.sistemapagamento.repositories.LoginRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO implements LoginRepository {

    private final Connection connection;

    public LoginDAO(Connection connection) {
        this.connection = connection;
    }
    
    @Override
    public Login validarUsuario(String username, String senha) {
        String sql = "SELECT id, nome, senha FROM logins WHERE nome = ? AND senha = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, username);
            st.setString(2, senha);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return new Login(rs.getInt("id"), rs.getString("nome"), rs.getString("senha"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao validar usuário", e);
        }
        return null;
    }
    
    @Override
    public boolean verificarUsuarioPorNome(String nomeUsuario) {
        String sql = "SELECT 1 FROM logins WHERE nome = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, nomeUsuario);
            try (ResultSet rs = st.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar usuário", e);
        }
    }

    @Override
    public boolean atualizarSenha(String nomeUsuario, String novaSenha) {
        String sql = "UPDATE logins SET senha = ? WHERE nome = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, novaSenha);
            st.setString(2, nomeUsuario);
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar senha", e);
        }
    }
}

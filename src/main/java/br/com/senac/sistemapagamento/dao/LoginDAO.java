package br.com.senac.sistemapagamento.dao;

import br.com.senac.sistemapagamento.models.Login;
import br.com.senac.sistemapagamento.repositories.LoginRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implementação da interface {@link LoginRepository} que realiza operações
 * de autenticação e gerenciamento de credenciais no banco de dados usando JDBC.
 * 
 * Essa classe é responsável por validar usuários, verificar existência e atualizar senhas.
 * 
 * @author alanm
 */
public class LoginDAO implements LoginRepository {

    private final Connection connection;

    /**
     * Construtor da classe {@code LoginDAO}.
     * 
     * @param connection a conexão JDBC que será utilizada para executar os comandos no banco de dados.
     */
    public LoginDAO(Connection connection) {
        this.connection = connection;
    }
    
    /**
     * Valida um usuário com base no nome e senha fornecidos.
     * 
     * @param username o nome de usuário.
     * @param senha a senha do usuário.
     * @return um objeto {@link Login} se as credenciais forem válidas, ou {@code null} se inválidas.
     * @throws RuntimeException se ocorrer um erro na consulta.
     */
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
    /**
     * Verifica se existe um usuário com o nome informado.
     * 
     * @param nomeUsuario o nome do usuário a ser verificado.
     * @return {@code true} se o usuário existir, {@code false} caso contrário.
     * @throws RuntimeException se ocorrer um erro na consulta.
     */
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

    /**
     * Atualiza a senha de um usuário com base no nome informado.
     * 
     * @param nomeUsuario o nome do usuário cuja senha será atualizada.
     * @param novaSenha a nova senha a ser definida.
     * @return {@code true} se a senha foi atualizada com sucesso, {@code false} caso contrário.
     * @throws RuntimeException se ocorrer um erro na atualização.
     */
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

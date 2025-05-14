package br.com.senac.sistemapagamento.repositories;

import br.com.senac.sistemapagamento.models.Login;

/**
 *
 * @author alanm
 */
public interface LoginRepository {
    Login validarUsuario(String username, String senha);
    boolean verificarUsuarioPorNome(String nomeUsuario);
    boolean atualizarSenha(String nomeUsuario, String novaSenha);
}


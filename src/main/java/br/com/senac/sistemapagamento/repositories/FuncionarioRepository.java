package br.com.senac.sistemapagamento.repositories;

import br.com.senac.sistemapagamento.models.Funcionario;
import java.util.List;

public interface FuncionarioRepository {
    void excluirFuncionario(int id);
    Funcionario buscarPorId(int id);
    Funcionario buscarPorNome(String nome);
}
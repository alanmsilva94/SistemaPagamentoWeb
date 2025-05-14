package br.com.senac.sistemapagamento.repositories;

/**
 * Interface que define as operações de persistência para a entidade DadosBancario.
 */
public interface DadosBancarioRepository {

    /**
     * Método para excluir um registro de DadosBancario pelo ID.
     *
     * @param id O ID do registro a ser excluído.
     */
    void excluirDadosBancarios(int id);
}
package br.com.senac.sistemapagamento.repositories;

import br.com.senac.sistemapagamento.models.Empresa;
import br.com.senac.sistemapagamento.models.Pagamento;
import java.util.List;

/**
 * Interface responsável por definir as operações CRUD para a entidade
 * Pagamento.
 */
public interface PagamentoRepository {

    /**
     * Lista todos os pagamentos cadastrados.
     *
     * @return Lista de pagamentos.
     */
    List<Pagamento> listar();

    /**
     * Exclui um pagamento com base no ID informado.
     *
     * @param id Identificador do pagamento.
     */
    void excluirPagamento(int id);

    /**
     * Lista os pagamentos filtrados por uma ou mais empresas.
     *
     * @param empresas Lista de empresas para filtrar os pagamentos.
     * @return Lista de pagamentos vinculados às empresas fornecidas.
     */
    List<Pagamento> listarPagamentosPorEmpresa(List<Empresa> empresas);
}

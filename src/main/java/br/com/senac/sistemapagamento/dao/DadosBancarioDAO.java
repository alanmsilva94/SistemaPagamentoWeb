package br.com.senac.sistemapagamento.dao;

import br.com.senac.sistemapagamento.models.DadosBancario;
import br.com.senac.sistemapagamento.repositories.DadosBancarioRepository;
import jakarta.persistence.EntityManager;

/**
 * Implementação da interface {@link DadosBancarioRepository} responsável pelas operações de persistência
 * da entidade {@link DadosBancario}.
 * 
 * Esta classe estende {@link GenericDAO}, seguindo os princípios SOLID, especialmente o de reutilização
 * e separação de responsabilidades.
 * 
 * @author alanm
 */
public class DadosBancarioDAO extends GenericDAO<DadosBancario> implements DadosBancarioRepository {

    /**
     * Construtor da classe {@code DadosBancarioDAO}.
     * 
     * @param entityManager o {@link EntityManager} utilizado para realizar operações no banco de dados.
     */
    public DadosBancarioDAO(EntityManager entityManager) {
        super(entityManager, DadosBancario.class);
    }

    /**
     * Exclui um registro de dados bancários com base no seu identificador.
     * Esta operação é realizada dentro de uma transação. Em caso de falha, a transação é revertida.
     * 
     * @param id o ID do objeto {@link DadosBancario} a ser excluído.
     * @throws RuntimeException se ocorrer algum erro durante a operação.
     */
    @Override
    public void excluirDadosBancarios(int id) {
        try {
            entityManager.getTransaction().begin();

            DadosBancario dadosBancarios = entityManager.find(DadosBancario.class, id);
            if (dadosBancarios != null) {
                entityManager.remove(dadosBancarios);
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Erro ao excluir dados bancários", e);
        }
    }
}
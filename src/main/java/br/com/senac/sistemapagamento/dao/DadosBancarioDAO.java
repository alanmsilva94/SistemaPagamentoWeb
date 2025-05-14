package br.com.senac.sistemapagamento.dao;

import br.com.senac.sistemapagamento.models.DadosBancario;
import br.com.senac.sistemapagamento.repositories.DadosBancarioRepository;
import jakarta.persistence.EntityManager;

/**
 * Classe DAO responsável pelas operações de persistência para a entidade DadosBancario.
 * Segue os princípios SOLID e mantém a herança de GenericDAO.
 */
public class DadosBancarioDAO extends GenericDAO<DadosBancario> implements DadosBancarioRepository {

    public DadosBancarioDAO(EntityManager entityManager) {
        super(entityManager, DadosBancario.class);
    }

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
package br.com.senac.sistemapagamento.dao;

import br.com.senac.sistemapagamento.models.DocumentoContabil;
import jakarta.persistence.EntityManager;

/**
 * Classe DAO responsável pelas operações de persistência para a entidade DocumentoContabil.
 * Essa classe segue os princípios SOLID para garantir melhor estrutura e manutenibilidade do código.
 *
 * @author alanm
 */
public class DocumentoContabilDAO {

    private final GenericDAO<DocumentoContabil> genericDAO;

    public DocumentoContabilDAO(EntityManager entityManager) {
        this.genericDAO = new GenericDAO<>(entityManager, DocumentoContabil.class);
    }

    public void cadastrar(DocumentoContabil documento) {
        genericDAO.cadastrar(documento);
    }
}
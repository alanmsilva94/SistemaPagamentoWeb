package br.com.senac.sistemapagamento.dao;

import br.com.senac.sistemapagamento.models.DocumentoContabil;
import jakarta.persistence.EntityManager;

/**
 * Classe DAO responsável pelas operações de persistência da entidade {@link DocumentoContabil}.
 * Essa classe segue os princípios SOLID para garantir melhor estrutura e manutenibilidade do código.
 *
 * Esta implementação utiliza um {@link GenericDAO} interno para realizar as operações,
 * seguindo os princípios SOLID para garantir separação de responsabilidades e reutilização de código.
 * 
 * @author alanm
 */
public class DocumentoContabilDAO {

    private final GenericDAO<DocumentoContabil> genericDAO;

    /**
     * Construtor da classe {@code DocumentoContabilDAO}.
     * @param entityManager o {@link EntityManager} utilizado para realizar operações com o banco de dados.
     */
    public DocumentoContabilDAO(EntityManager entityManager) {
        this.genericDAO = new GenericDAO<>(entityManager, DocumentoContabil.class);
    }

    /**
     * Cadastra um novo documento contábil no banco de dados.
     * 
     * @param documento o objeto {@link DocumentoContabil} a ser persistido.
     */
    public void cadastrar(DocumentoContabil documento) {
        genericDAO.cadastrar(documento);
    }
}
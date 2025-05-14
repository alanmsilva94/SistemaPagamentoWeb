package br.com.senac.sistemapagamento.dao;

import br.com.senac.sistemapagamento.repositories.GenericRepository;
import jakarta.persistence.EntityManager;
/**
 * Implementação genérica da interface {@link GenericRepository}, responsável
 * por fornecer operações básicas de persistência para qualquer entidade.
 * 
 * @param <T> o tipo da entidade que será manipulada.
 * 
 * @author alanm
 */
public class GenericDAO<T> implements GenericRepository<T> {

    /** Gerenciador de entidades utilizado para operações de persistência. */
    final EntityManager entityManager;

    /** Classe da entidade que será manipulada. */
    private final Class<T> entityClass;

    /**
     * Construtor da classe {@code GenericDAO}.
     * 
     * @param entityManager o {@link EntityManager} a ser utilizado nas transações.
     * @param entityClass a classe da entidade que será manipulada.
     */
    public GenericDAO(EntityManager entityManager, Class<T> entityClass) {
        this.entityManager = entityManager;
        this.entityClass = entityClass;
    }

    /**
     * Persiste uma nova entidade no banco de dados.
     * 
     * @param entity a entidade a ser cadastrada.
     * @throws RuntimeException se ocorrer erro durante a transação.
     */
    @Override
    public void cadastrar(T entity) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }
}

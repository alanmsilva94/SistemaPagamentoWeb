package br.com.senac.sistemapagamento.dao;

import br.com.senac.sistemapagamento.repositories.GenericRepository;
import jakarta.persistence.EntityManager;

public class GenericDAO<T> implements GenericRepository<T> {

    final EntityManager entityManager;

    private final Class<T> entityClass;

    public GenericDAO(EntityManager entityManager, Class<T> entityClass) {
        this.entityManager = entityManager;
        this.entityClass = entityClass;
    }

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

package br.com.senac.sistemapagamento.dao;

import br.com.senac.sistemapagamento.models.Departamento;
import br.com.senac.sistemapagamento.repositories.DepartamentoRepository;
import jakarta.persistence.EntityManager;

public class DepartamentoDAO extends GenericDAO<Departamento> implements DepartamentoRepository {

    public DepartamentoDAO(EntityManager entityManager) {
        super(entityManager, Departamento.class);
    }

    @Override
    public void excluirDepartamento(int id) {
        try {
            entityManager.getTransaction().begin();
            Departamento departamento = entityManager.find(Departamento.class, id);
            if (departamento != null) {
                entityManager.remove(departamento);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }
}
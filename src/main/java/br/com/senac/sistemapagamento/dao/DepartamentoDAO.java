package br.com.senac.sistemapagamento.dao;

import br.com.senac.sistemapagamento.models.Departamento;
import br.com.senac.sistemapagamento.repositories.DepartamentoRepository;
import jakarta.persistence.EntityManager;

/**
 * Implementação do repositório {@link DepartamentoRepository} para operações de persistência da entidade {@link Departamento}.
 * 
 * Esta classe herda comportamentos básicos de persistência da classe {@link GenericDAO}
 * e fornece operações específicas para manipular objetos do tipo Departamento.
 * 
 * @author alanm
 */
public class DepartamentoDAO extends GenericDAO<Departamento> implements DepartamentoRepository {

    /**
     * Construtor da classe {@code DepartamentoDAO}.
     * 
     * @param entityManager o {@link EntityManager} utilizado para realizar operações com o banco de dados.
     */
    public DepartamentoDAO(EntityManager entityManager) {
        super(entityManager, Departamento.class);
    }

    /**
     * Exclui um departamento com base no seu identificador.
     * A operação é realizada dentro de uma transação. Em caso de erro, a transação é revertida.
     * 
     * @param id o ID do departamento a ser excluído.
     * @throws RuntimeException se ocorrer um erro durante a exclusão.
     */
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
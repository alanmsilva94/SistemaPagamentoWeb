package br.com.senac.sistemapagamento.dao;

import br.com.senac.sistemapagamento.models.Funcionario;
import br.com.senac.sistemapagamento.repositories.FuncionarioRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class FuncionarioDAO extends GenericDAO<Funcionario> implements FuncionarioRepository {

    private final EntityManager entityManager;

    public FuncionarioDAO(EntityManager entityManager) {
        super(entityManager, Funcionario.class);
        this.entityManager = entityManager;
    }

    @Override
    public void excluirFuncionario(int id) {
        try {
            entityManager.getTransaction().begin();
            Funcionario funcionario = buscarPorId(id);
            if (funcionario != null) {
                entityManager.remove(funcionario);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public Funcionario buscarPorId(int id) {
        return entityManager.find(Funcionario.class, id);
    }

    @Override
    public Funcionario buscarPorNome(String nome) {
        String textoQuery = "SELECT f FROM Funcionario f WHERE f.nome_fun = :nome";
        TypedQuery<Funcionario> consulta = entityManager.createQuery(textoQuery, Funcionario.class);
        consulta.setParameter("nome", nome);

        List<Funcionario> resultado = consulta.getResultList();
        return resultado.isEmpty() ? null : resultado.get(0);
    }
}

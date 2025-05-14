package br.com.senac.sistemapagamento.dao;

import static br.com.senac.sistemapagamento.dao.JPAUtil.getEntityManager;
import br.com.senac.sistemapagamento.models.Empresa;
import br.com.senac.sistemapagamento.models.Pagamento;
import br.com.senac.sistemapagamento.repositories.PagamentoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 * Implementação do repositório para a entidade Pagamento.
 */
public class PagamentoDAO extends GenericDAO<Pagamento> implements PagamentoRepository {

    public PagamentoDAO(EntityManager entityManager) {
        super(entityManager, Pagamento.class);
    }

    @Override
    public List<Pagamento> listar() {
        try {
            TypedQuery<Pagamento> consulta = getEntityManager().createQuery(
                    "SELECT p FROM Pagamento p " +
                    "LEFT JOIN FETCH p.empresa " +
                    "LEFT JOIN FETCH p.funcionario f " +
                    "LEFT JOIN FETCH f.departamento", 
                    Pagamento.class);
            return consulta.getResultList();
        } finally {
            getEntityManager().close();
        }
    }

    @Override
    public void excluirPagamento(int id) {
        try {
            getEntityManager().getTransaction().begin();
            Pagamento pagamento = getEntityManager().find(Pagamento.class, id);
            if (pagamento != null) {
                getEntityManager().remove(pagamento);
            }
            getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            getEntityManager().getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public List<Pagamento> listarPagamentosPorEmpresa(List<Empresa> empresas) {
        try {
            TypedQuery<Pagamento> consulta = getEntityManager().createQuery(
                    "SELECT p FROM Pagamento p " +
                    "LEFT JOIN FETCH p.empresa e " +
                    "LEFT JOIN FETCH p.funcionario f " +
                    "LEFT JOIN FETCH f.departamento " +
                    "WHERE e IN :empresas", 
                    Pagamento.class);
            consulta.setParameter("empresas", empresas);
            return consulta.getResultList();
        } finally {
            getEntityManager().close();
        }
    }
}
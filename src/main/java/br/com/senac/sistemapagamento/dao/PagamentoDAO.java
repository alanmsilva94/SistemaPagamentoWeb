package br.com.senac.sistemapagamento.dao;

import static br.com.senac.sistemapagamento.dao.JPAUtil.getEntityManager;
import br.com.senac.sistemapagamento.models.Empresa;
import br.com.senac.sistemapagamento.models.Pagamento;
import br.com.senac.sistemapagamento.repositories.PagamentoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 * Classe de acesso a dados (DAO) responsável pelas operações relacionadas à entidade {@link Pagamento}.
 * Estende a classe {@link GenericDAO} e implementa a interface {@link PagamentoRepository}.
 * Utiliza JPA para realizar consultas e manipulação de dados no banco.
 * 
 * @author alanm
 */
public class PagamentoDAO extends GenericDAO<Pagamento> implements PagamentoRepository {

    /**
     * Construtor da classe PagamentoDAO.
     * 
     * @param entityManager o {@link EntityManager} a ser utilizado nas operações de persistência.
     */
    public PagamentoDAO(EntityManager entityManager) {
        super(entityManager, Pagamento.class);
    }

    /**
     * Lista todos os pagamentos cadastrados no banco, incluindo os relacionamentos
     * com empresa, funcionário e departamento (usando fetch join para evitar lazy loading).
     * 
     * @return uma lista de objetos {@link Pagamento}.
     */
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

    /**
     * Exclui um pagamento do banco de dados com base no ID informado.
     * 
     * @param id o identificador do pagamento a ser excluído.
     * @throws RuntimeException caso ocorra algum erro durante a exclusão
     */
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

    /**
     * Lista todos os pagamentos vinculados a uma ou mais empresas especificadas.
     * Também carrega os relacionamentos com funcionário e departamento.
     * 
     * 
     * @param empresas uma lista de objetos {@link Empresa} cujos pagamentos serão retornados.
     * @return uma lista de objetos {@link Pagamento} associados às empresas informadas.
     */
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
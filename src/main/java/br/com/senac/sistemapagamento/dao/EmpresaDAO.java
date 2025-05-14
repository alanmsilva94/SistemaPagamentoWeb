package br.com.senac.sistemapagamento.dao;

import br.com.senac.sistemapagamento.models.DadosBancario;
import br.com.senac.sistemapagamento.models.Empresa;
import br.com.senac.sistemapagamento.repositories.EmpresaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 * Implementação do repositório para a entidade Empresa seguindo os princípios
 * SOLID.
 */
public class EmpresaDAO extends GenericDAO<Empresa> implements EmpresaRepository {

    public EmpresaDAO(EntityManager entityManager) {
        super(entityManager, Empresa.class);
    }

    @Override
    public Empresa buscarPorCnpj(String cnpj) {
        TypedQuery<Empresa> consulta = entityManager.createQuery(
                "SELECT e FROM Empresa e WHERE e.cnpj = :cnpj", Empresa.class);
        consulta.setParameter("cnpj", cnpj);

        List<Empresa> resultado = consulta.getResultList();
        return resultado.isEmpty() ? null : resultado.get(0);
    }

    @Override
    public List<Empresa> listarEmpresasComDadosBancarios() {
        TypedQuery<Empresa> consulta = entityManager.createQuery(
                "SELECT e FROM Empresa e LEFT JOIN FETCH e.dadosBancarios", Empresa.class);
        return consulta.getResultList();
    }

    @Override
    public void excluirEmpresa(int id) {
        try {
            entityManager.getTransaction().begin();
            Empresa empresa = entityManager.find(Empresa.class, id);
            if (empresa != null) {
                entityManager.remove(empresa);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Erro ao excluir empresa", e);
        }
    }

    @Override
    public void cadastrarEmpresa(Empresa empresa, List<DadosBancario> dadosBancarios) {
        for (DadosBancario dado : dadosBancarios) {
            dado.setEmpresa(empresa);
            empresa.getDadosBancarios().add(dado);
        }
        cadastrar(empresa);
    }

    @Override
    public List<Empresa> listarEmpresasPorNome(String filtroNome) {
        TypedQuery<Empresa> consulta = entityManager.createQuery(
                "SELECT e FROM Empresa e LEFT JOIN FETCH e.dadosBancarios WHERE (:nome IS NULL OR e.nomeEmpresa LIKE :nome)",
                Empresa.class);
        consulta.setParameter("nome", filtroNome.isEmpty() ? null : "%" + filtroNome + "%");

        return consulta.getResultList();
    }
}

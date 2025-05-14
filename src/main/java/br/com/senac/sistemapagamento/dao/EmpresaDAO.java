package br.com.senac.sistemapagamento.dao;

import br.com.senac.sistemapagamento.models.DadosBancario;
import br.com.senac.sistemapagamento.models.Empresa;
import br.com.senac.sistemapagamento.repositories.EmpresaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 * Implementação da interface {@link EmpresaRepository} para realizar operações
 * de persistência com a entidade {@link Empresa}, utilizando JPA.
 * 
 * Essa classe também segue os princípios SOLID, especialmente o Princípio da Responsabilidade Única,
 * centralizando todas as responsabilidades relacionadas ao acesso a dados da entidade Empresa.
 * 
 * Estende a classe {@link GenericDAO} para reutilizar comportamentos genéricos de persistência.
 * 
 * @author alanm
 */
public class EmpresaDAO extends GenericDAO<Empresa> implements EmpresaRepository {

    /**
     * Construtor da classe {@code EmpresaDAO}.
     * 
     * @param entityManager o {@link EntityManager} utilizado para interações com o banco de dados.
     */
    public EmpresaDAO(EntityManager entityManager) {
        super(entityManager, Empresa.class);
    }

    /**
     * Busca uma empresa com base no CNPJ.
     * 
     * @param cnpj o CNPJ da empresa a ser buscada.o CNPJ da empresa a ser buscada.
     * @return a empresa correspondente ao CNPJ informado ou {@code null} se não for encontrada.
     */
    @Override
    public Empresa buscarPorCnpj(String cnpj) {
        TypedQuery<Empresa> consulta = entityManager.createQuery(
                "SELECT e FROM Empresa e WHERE e.cnpj = :cnpj", Empresa.class);
        consulta.setParameter("cnpj", cnpj);

        List<Empresa> resultado = consulta.getResultList();
        return resultado.isEmpty() ? null : resultado.get(0);
    }

    /**
     * Lista todas as empresas com seus respectivos dados bancários carregados.
     * 
     * @return uma lista de empresas com seus dados bancários associados.
     */
    @Override
    public List<Empresa> listarEmpresasComDadosBancarios() {
        TypedQuery<Empresa> consulta = entityManager.createQuery(
                "SELECT e FROM Empresa e LEFT JOIN FETCH e.dadosBancarios", Empresa.class);
        return consulta.getResultList();
    }

    /**
     * Exclui uma empresa com base no ID fornecido.
     * 
     * @param id id o identificador da empresa a ser excluída.
     * @throws RuntimeException se ocorrer um erro durante a transação.
     */
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

    /**
     * Cadastra uma empresa junto com seus dados bancários.
     * Os dados bancários são vinculados à empresa antes da persistência.
     * 
     * @param empresa a empresa a ser cadastrada.
     * @param dadosBancarios a lista de dados bancários que serão associados à empresa.
     */
    @Override
    public void cadastrarEmpresa(Empresa empresa, List<DadosBancario> dadosBancarios) {
        for (DadosBancario dado : dadosBancarios) {
            dado.setEmpresa(empresa);
            empresa.getDadosBancarios().add(dado);
        }
        cadastrar(empresa);
    }

    /**
     * Lista empresas cujo nome corresponde ao filtro fornecido.
     * Se o filtro estiver vazio, todas as empresas são retornadas.
     * 
     * @param filtroNome parte do nome da empresa a ser filtrada.
     * @return uma lista de empresas cujo nome corresponde ao filtro.
     */
    @Override
    public List<Empresa> listarEmpresasPorNome(String filtroNome) {
        TypedQuery<Empresa> consulta = entityManager.createQuery(
                "SELECT e FROM Empresa e LEFT JOIN FETCH e.dadosBancarios WHERE (:nome IS NULL OR e.nomeEmpresa LIKE :nome)",
                Empresa.class);
        consulta.setParameter("nome", filtroNome.isEmpty() ? null : "%" + filtroNome + "%");

        return consulta.getResultList();
    }
}

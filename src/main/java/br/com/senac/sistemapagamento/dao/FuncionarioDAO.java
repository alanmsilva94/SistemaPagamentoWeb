package br.com.senac.sistemapagamento.dao;

import br.com.senac.sistemapagamento.models.Funcionario;
import br.com.senac.sistemapagamento.repositories.FuncionarioRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 * Classe responsável pelo acesso a dados da entidade {@link Funcionario},
 * implementando a interface {@link FuncionarioRepository}.
 * 
 * Estende {@link GenericDAO} para herdar a funcionalidade de persistência genérica
 * e adiciona métodos específicos para manipulação de funcionários.
 * 
 * @author alanm
 */
public class FuncionarioDAO extends GenericDAO<Funcionario> implements FuncionarioRepository {

    private final EntityManager entityManager;

    /**
     * Construtor da classe {@code FuncionarioDAO}.
     * 
     * @param entityManager o {@link EntityManager} a ser utilizado nas operações de banco de dados.
     */
    public FuncionarioDAO(EntityManager entityManager) {
        super(entityManager, Funcionario.class);
        this.entityManager = entityManager;
    }

    /**
     * Exclui um funcionário com base no seu ID.
     * @param id o identificador do funcionário a ser excluído.
     * 
     * @throws RuntimeException se ocorrer um erro durante a exclusão.
     */
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

    /**
     * Busca um funcionário com base no seu ID.
     * 
     * @param id o identificador do funcionário.
     * @return o objeto {@link Funcionario} correspondente, ou {@code null} se não encontrado.
     */
    @Override
    public Funcionario buscarPorId(int id) {
        return entityManager.find(Funcionario.class, id);
    }

    /**
     * Busca um funcionário com base no seu nome.
     * 
     * @param nome o nome do funcionário.
     * @return o objeto {@link Funcionario} correspondente, ou {@code null} se não encontrado.
     */
    @Override
    public Funcionario buscarPorNome(String nome) {
        String textoQuery = "SELECT f FROM Funcionario f WHERE f.nome_fun = :nome";
        TypedQuery<Funcionario> consulta = entityManager.createQuery(textoQuery, Funcionario.class);
        consulta.setParameter("nome", nome);

        List<Funcionario> resultado = consulta.getResultList();
        return resultado.isEmpty() ? null : resultado.get(0);
    }
}

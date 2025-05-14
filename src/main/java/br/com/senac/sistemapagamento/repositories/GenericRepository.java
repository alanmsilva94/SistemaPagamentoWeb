package br.com.senac.sistemapagamento.repositories;
/**
 *
 * @author alanm
 */
public interface GenericRepository<T> {
    void cadastrar(T entity);
}



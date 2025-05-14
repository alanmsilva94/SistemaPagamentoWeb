package br.com.senac.sistemapagamento;

import br.com.senac.sistemapagamento.dao.DepartamentoDAO;
import br.com.senac.sistemapagamento.models.Departamento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class SistemaPagamentoWeb {

    public static void main(String[] args) {

        EntityManagerFactory fabricaEntidade = Persistence.createEntityManagerFactory("Sistema-PU");
        EntityManager manager = fabricaEntidade.createEntityManager();
        
        manager.close();
        fabricaEntidade.close();

    }
}
